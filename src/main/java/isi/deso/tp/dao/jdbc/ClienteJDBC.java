package isi.deso.tp.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import isi.deso.tp.dao.ClienteDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;

public class ClienteJDBC implements ClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT c.id, c.nombre, c.cuit, c.email, c.direccion, co.lat, co.lgn " +
                       "FROM Cliente c " +
                       "LEFT JOIN Coordenada co ON c.coordenada_id = co.id;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cuit = rs.getString("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                Double lat = rs.getDouble("lat");
                Double lgn = rs.getDouble("lgn");

                //hablar con fran que pasa si la coordenada es nulla
                Coordenada coordenada = (lat != null && lgn != null) ? new Coordenada(lat, lgn) : new Coordenada(0,0);

                Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);
                lista.add(cliente);
            }
            return lista;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, "Error al listar clientes: "+ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public void agregarClienteALista(Cliente cliente) {
        String query = "INSERT INTO Cliente (nombre, cuit, email, direccion, coordenada_id) VALUES (?, ?, ?, ?, ?);";
    
        try (Connection conn = DBConnector.getInstance();
            PreparedStatement ps = conn.prepareStatement(query)) {
    
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCuit());
            ps.setObject(3, cliente.getEmail());
            ps.setString(4, cliente.getDireccion());
    
            Integer coordenadaId = null;
            if (cliente.getCoordenada() != null) {
                coordenadaId = CoordenadaJDBC.guardarCoordenada(cliente.getCoordenada());
            }
            ps.setObject(5, coordenadaId);

            ps.executeUpdate();
    
            Logger.getLogger(VendedorJDBC.class.getName())
                  .log(Level.INFO, "Cliente creado: ");
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName())
                  .log(Level.SEVERE, "Error al crear cliente", ex);
        }
    }

    @Override
    public void eliminarListaClientesALista(List<Cliente> listaClientes) {
        String deleteClienteQuery = "DELETE FROM Cliente WHERE id = ?";
        String deleteCoordenadaQuery = "DELETE FROM Coordenada WHERE id = ?";
    
        try (Connection conn = DBConnector.getInstance()) {
            // Deshabilitamos autocommit para manejar las operaciones como transaccion
            conn.setAutoCommit(false);
    
            try (PreparedStatement deleteClienteStmt = conn.prepareStatement(deleteClienteQuery);
                 PreparedStatement deleteCoordenadaStmt = conn.prepareStatement(deleteCoordenadaQuery)) {
                for (Cliente cliente : listaClientes) {
                    deleteClienteStmt.setInt(1, cliente.getId());
                    deleteClienteStmt.executeUpdate();
    
                    if (cliente.getCoordenada() != null) {
                        deleteCoordenadaStmt.setInt(1, cliente.getCoordenada().getId());
                        deleteCoordenadaStmt.executeUpdate();
                    }
                }
    
                // Confirmar la transacción
                conn.commit();
                Logger.getLogger(VendedorJDBC.class.getName()).log(Level.INFO, "Clientes y sus coordenadas eliminados correctamente.");
            } catch (SQLException ex) {
               // Revertir cambios si ocurre un error
                conn.rollback(); 
                Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error eliminando clientes y coordenadas", ex);
            } finally {
                // Restaurar el autocommit
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendedorJDBC.class.getName()).log(Level.SEVERE, "Error en la conexión a la base de datos", ex);
        }
    }

    @Override
    public Cliente buscarPorIdCliente(Integer idCliente){
        String query = "SELECT c.id, c.nombre, c.cuit, c.email, c.direccion, co.lat, co.lgn " +
                    "FROM Cliente c " +
                    "LEFT JOIN Coordenada co ON c.coordenada_id = co.id " +
                    "WHERE c.id = ?;";
        try (Connection conn = DBConnector.getInstance();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String cuit = rs.getString("cuit");
                    String email = rs.getString("email");
                    String direccion = rs.getString("direccion");
                    Double lat = rs.getDouble("lat");
                    Double lgn = rs.getDouble("lgn");
                    Coordenada coordenada = lat != null && lgn != null ? new Coordenada(lat, lgn) : null;

                    return new Cliente(id, nombre, cuit, email, direccion, coordenada);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, "Error al buscar cliente por ID", ex);
        }
        return null;
    }

    @Override
    public void eliminarClienteALista(Cliente cliente) {
        String query = "DELETE FROM Cliente WHERE id = ?;";
        try (Connection conn = DBConnector.getInstance();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cliente.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                Logger.getLogger(ClienteJDBC.class.getName())
                    .log(Level.WARNING, "No se encontró cliente con ID: ");
            } else {
                Logger.getLogger(ClienteJDBC.class.getName())
                    .log(Level.INFO, "Cliente eliminado correctamente. ID: ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, "Error al eliminar cliente", ex);
        }
    }

}
