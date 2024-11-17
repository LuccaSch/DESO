package isi.deso.tp.dao.jdbc;

import isi.deso.tp.dao.ItemMenuDAO;
import isi.deso.tp.model.Bebida;
import isi.deso.tp.model.BebidaAlcoholica;
import isi.deso.tp.model.BebidaSinAlcohol;
import isi.deso.tp.model.Categoria;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.Plato;
import isi.deso.tp.model.Tamano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemMenuJDBC implements ItemMenuDAO {

    //SOLUCIONAR LA CATEGORIA
    @Override
    public List<ItemMenu> listarItemsMenu() {
        String query = """
            SELECT im.*,
                b.tamano, b.volumen, b.graduacion_alcoholica, b.tipoBebida,
                p.calorias, p.apto_celiaco, p.apto_vegano, c.id, c.descripcion
            FROM ItemMenu im
            LEFT JOIN Categoria c ON im.categoria_id = c.id
            LEFT JOIN Bebida b ON im.id = b.id
            LEFT JOIN Plato p ON im.id = p.id;
        """;

        List<ItemMenu> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            VendedorJDBC vendedorJDBC = new VendedorJDBC();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Double precio = rs.getDouble("precio");
                Double peso = rs.getDouble("peso");
                Integer categoriaId = rs.getInt("categoria_id");
                String descripcionCategoria = rs.getString("descripcion");
                Integer vendedorId = rs.getInt("vendedor_id");

                // Determinar si es Bebida o Plato
                if (rs.getString("tamano") != null) {
                    // Bebida
                    Tamano tamano = Tamano.valueOf(rs.getString("tamano"));
                    Double volumen = rs.getDouble("volumen");
                    Integer graduacionAlcoholica = rs.getObject("graduacion_alcoholica", Integer.class);
                    Integer tipoBebida = rs.getObject("tipoBebida", Integer.class);

                    Bebida bebida;
                    if (tipoBebida != null && tipoBebida == 1) {
                        bebida = new BebidaAlcoholica(graduacionAlcoholica, tamano, volumen, id, nombre, descripcion, precio, new Categoria(categoriaId, descripcionCategoria), vendedorJDBC.buscarVendedor(vendedorId));
                    } else {
                        bebida = new BebidaSinAlcohol(tamano, volumen, id, nombre, descripcion, precio, new Categoria(categoriaId, descripcionCategoria), vendedorJDBC.buscarVendedor(vendedorId));
                    }
                    items.add(bebida);

                } else {
                    // Plato
                    Integer calorias = rs.getInt("calorias");
                    Boolean aptoCeliaco = rs.getBoolean("apto_celiaco");
                    Boolean aptoVegano = rs.getBoolean("apto_vegano");
                    items.add(new Plato(calorias, aptoCeliaco, aptoVegano, id, nombre, descripcion, precio, new Categoria(categoriaId, descripcionCategoria), peso, vendedorJDBC.buscarVendedor(vendedorId)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al listar items", ex);
        }
        return items;
    }

    @Override
    public void crearItemMenu(ItemMenu itemMenu) {
        String itemMenuQuery = "INSERT INTO ItemMenu (nombre, descripcion, precio, categoria_id, peso, vendedor_id) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(itemMenuQuery, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, itemMenu.getNombre());
            ps.setString(2, itemMenu.getDescripcion());
            ps.setDouble(3, itemMenu.getPrecio());
            ps.setObject(4, itemMenu.getCategoria() != null ? itemMenu.getCategoria().getId() : null);
            ps.setDouble(5, itemMenu.getPeso());
            ps.setObject(6, itemMenu.getVendedor() != null ? itemMenu.getVendedor().getId() : null);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int itemId = rs.getInt(1);
                if (itemMenu instanceof Bebida bebida) {
                    crearBebida(bebida, conn, itemId);
                } else if (itemMenu instanceof Plato plato) {
                    crearPlato(plato, conn, itemId);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al crear item", ex);
        }
    }

    private void crearBebida(Bebida bebida, Connection conn, int itemId) throws SQLException {
        String query = "INSERT INTO Bebida (id, tamano, volumen, graduacion_alcoholica, tipoBebida) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, itemId);
            ps.setString(2, bebida.getTamano().name());
            ps.setDouble(3, bebida.getVolumen());
            if (bebida instanceof BebidaAlcoholica alcoholica) {
                // tipoBebida = 1 para BebidaAlcoholica tipo 2 sin alcohol
                ps.setInt(4, alcoholica.getGraduacionAlcoholica());
                ps.setInt(5, 1);
            } else {
                ps.setNull(4, Types.INTEGER);
                ps.setInt(5, 2);
            }
            ps.executeUpdate();
        }
    }

    private void crearPlato(Plato plato, Connection conn, int itemId) throws SQLException {
        String query = "INSERT INTO Plato (id, calorias, apto_celiaco, apto_vegano) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, itemId);
            ps.setInt(2, plato.getCalorias());
            ps.setBoolean(3, plato.getAptoCeliaco());
            ps.setBoolean(4, plato.getAptoVegano());
            ps.executeUpdate();
        }
    }

    @Override
    public void actualizarItemMenu(ItemMenu itemMenu) {
        String itemMenuQuery = """
            UPDATE ItemMenu
            SET nombre = ?, descripcion = ?, precio = ?, categoria_id = ?, peso = ?, vendedor_id = ?
            WHERE id = ?;
        """;
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(itemMenuQuery)) {

            ps.setString(1, itemMenu.getNombre());
            ps.setString(2, itemMenu.getDescripcion());
            ps.setDouble(3, itemMenu.getPrecio());
            ps.setObject(4, itemMenu.getCategoria() != null ? itemMenu.getCategoria().getId() : null);
            ps.setDouble(5, itemMenu.getPeso());
            ps.setObject(6, itemMenu.getVendedor() != null ? itemMenu.getVendedor().getId() : null);
            ps.setInt(7, itemMenu.getId());

            ps.executeUpdate();

            switch (itemMenu) {
                case Bebida bebida ->
                    actualizarBebida(bebida, conn);
                case Plato plato ->
                    actualizarPlato(plato, conn);
                default -> {
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al actualizar item", ex);
        }
    }

    private void actualizarBebida(Bebida bebida, Connection conn) throws SQLException {
        String query = """
            UPDATE Bebida
            SET tamano = ?, volumen = ?, graduacion_alcoholica = ?, tipoBebida = ?
            WHERE id = ?;
        """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, bebida.getTamano().name());
            ps.setDouble(2, bebida.getVolumen());
            if (bebida instanceof BebidaAlcoholica alcoholica) {
                ps.setInt(3, alcoholica.getGraduacionAlcoholica());
                ps.setInt(4, 1);
            } else {
                ps.setNull(3, Types.INTEGER);
                ps.setInt(4, 2);
            }
            ps.setInt(5, bebida.getId());
            ps.executeUpdate();
        }
    }

    private void actualizarPlato(Plato plato, Connection conn) throws SQLException {
        String query = """
            UPDATE Plato
            SET calorias = ?, apto_celiaco = ?, apto_vegano = ?
            WHERE id = ?;
        """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, plato.getCalorias());
            ps.setBoolean(2, plato.getAptoCeliaco());
            ps.setBoolean(3, plato.getAptoVegano());
            ps.setInt(4, plato.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<ItemMenu> buscarItemsMenuPorId(Integer idItemMenu) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarItemMenu'");
    }

    @Override
    public void eliminarItemMenu(Integer idItemMenu) {
        String bebidaQuery = "DELETE FROM Bebida WHERE id = ?";
        String platoQuery = "DELETE FROM Plato WHERE id = ?";
        String itemMenuQuery = "DELETE FROM ItemMenu WHERE id = ?";

        try (Connection conn = DBConnector.getInstance(); PreparedStatement bebidaPs = conn.prepareStatement(bebidaQuery); PreparedStatement platoPs = conn.prepareStatement(platoQuery); PreparedStatement itemMenuPs = conn.prepareStatement(itemMenuQuery)) {

            // Eliminar de la tabla Bebida si existe (si lo hace en cascada sacamos los metodos)
            bebidaPs.setInt(1, idItemMenu);
            bebidaPs.executeUpdate();

            platoPs.setInt(1, idItemMenu);
            platoPs.executeUpdate();

            itemMenuPs.setInt(1, idItemMenu);
            itemMenuPs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ItemMenuJDBC.class.getName()).log(Level.SEVERE, "Error al eliminar item", ex);
        }
    }

}
