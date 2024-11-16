package isi.deso.tp.dao.jdbc;

import isi.deso.tp.dao.VendedorDAO;
import isi.deso.tp.exception.VendedorNoEncontradoException;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorJDBC implements VendedorDAO {

    @Override
    public List<Vendedor> listarVendedor() {
        List<Vendedor> lista = new ArrayList<>();
        String query = "SELECT v.id, v.nombre, v.direccion, c.lat, c.lgn " +
                       "FROM Vendedor v " +
                       "LEFT JOIN Coordenada c ON v.coordenada_id = c.id;";
        try (Connection conn = DBConnector.getInstance();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                Double lat = rs.getDouble("lat");
                Double lgn = rs.getDouble("lgn");
                Coordenada coordenada = lat != 0 && lgn != 0 ? new Coordenada(lat, lgn) : null;

                Vendedor vendedor = new Vendedor(id, nombre, direccion, coordenada);
                lista.add(vendedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error al listar vendedores ", ex);
        }
        return lista;
    }

    @Override
    public void crearVendedor(Vendedor vendedor) {
        String insertCoordenadaQuery = "INSERT INTO Coordenada (lat, lgn) VALUES (?, ?);";
        String insertVendedorQuery = "INSERT INTO Vendedor (nombre, direccion, coordenada_id) VALUES (?, ?, ?);";
    
        try (Connection conn = DBConnector.getInstance()) {
            // Inicia la transacción desactivamos el commit
            conn.setAutoCommit(false); 
    
            Integer coordenadaId = null;
    
            if (vendedor.getCoordenada() != null) {
                try (PreparedStatement ps = conn.prepareStatement(insertCoordenadaQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    ps.setDouble(1, vendedor.getCoordenada().getLat());
                    ps.setDouble(2, vendedor.getCoordenada().getLgn());
                    ps.executeUpdate();
    
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            coordenadaId = rs.getInt(1);
                        }
                    }
                }
            }
    
            try (PreparedStatement ps = conn.prepareStatement(insertVendedorQuery)) {
                ps.setString(1, vendedor.getNombre());
                ps.setString(2, vendedor.getDireccion());
                ps.setObject(3, coordenadaId);
                ps.executeUpdate();
            }
    
            conn.commit(); // Confirma la transacción
            Logger.getLogger(VendedorJDBC.class.getName())
                  .log(Level.INFO, "Vendedor creado");
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName())
                  .log(Level.SEVERE, "Error al crear vendedor", ex);
            try {
                DBConnector.getInstance().rollback(); // Revertir cambios en caso de error
            } catch (SQLException rollbackEx) {
                Logger.getLogger(VendedorJDBC.class.getName())
                      .log(Level.SEVERE, "Error al realizar rollback", rollbackEx);
            }
        }
    }
    

    @Override
    public void actualizarVendedor(Vendedor vendedor) {
        String query = "UPDATE Vendedor SET nombre = ?, direccion = ?, coordenada_id = ? WHERE id = ?;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getDireccion());
            ps.setObject(3, vendedor.getCoordenada() != null ? vendedor.getCoordenada().getId() : null);
            ps.setInt(4, vendedor.getId());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                Logger.getLogger(VendedorJDBC.class.getName())
                      .log(Level.WARNING, "No se encontró vendedor con ID seleccionado");
            } else {
                Logger.getLogger(VendedorJDBC.class.getName()).log(Level.INFO, "Vendedor actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error al actualizar vendedor", ex);
        }
    }

    @Override
    public void eliminarVendedor(Integer idVendedor) {
        String query = "DELETE FROM Vendedor WHERE id = ?;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idVendedor);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                Logger.getLogger(VendedorJDBC.class.getName())
                      .log(Level.WARNING, "No se encontró vendedor con ID");
            } else {
                Logger.getLogger(VendedorJDBC.class.getName()).log(Level.INFO, "Vendedor eliminado. ID:" );
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error al eliminar vendedor", ex);
        }
    }

    @Override
    public Vendedor buscarVendedor(Integer idVendedor) {
        String query = "SELECT v.id, v.nombre, v.direccion, c.lat, c.lgn " +
                       "FROM Vendedor v " +
                       "LEFT JOIN Coordenada c ON v.coordenada_id = c.id " +
                       "WHERE v.id = ?;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idVendedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String direccion = rs.getString("direccion");
                    Double lat = rs.getDouble("lat");
                    Double lgn = rs.getDouble("lgn");
                    Coordenada coordenada = lat != 0 && lgn != 0 ? new Coordenada(lat, lgn) : null;

                    Vendedor vendedor = new Vendedor(id, nombre, direccion, coordenada);
                    return vendedor;
                }
                else{
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error al buscar vendedor", ex);
            return null;
        }
    }

    @Override
    public Vendedor buscarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {
        String query = "SELECT v.id, v.nombre, v.direccion, c.lat, c.lgn " +
                       "FROM Vendedor v " +
                       "LEFT JOIN Coordenada c ON v.coordenada_id = c.id " +
                       "WHERE v.nombre = ?;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String direccion = rs.getString("direccion");
                    Double lat = rs.getDouble("lat");
                    Double lgn = rs.getDouble("lgn");
                    Coordenada coordenada = lat != 0 && lgn != 0 ? new Coordenada(lat, lgn) : null;

                    return new Vendedor(id, nombre, direccion, coordenada);
                } else {
                    throw new VendedorNoEncontradoException("Vendedor con nombre " + nombre + " no encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error al buscar vendedor por nombre", ex);
            return null;
        }
    }
}
