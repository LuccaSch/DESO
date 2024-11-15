package isi.deso.tp.dao;

import isi.deso.tp.dao.jdbc.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;

public class ClienteJDBC implements ClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM Cliente;";
        try (Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cuit = rs.getString("cuit");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                Integer coordenadaId = rs.getInt("coordenada_id");
                Coordenada coordenada = CoordenadaJDBC.obtenerCoordenadaById(coordenadaId);
                Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void agregarClienteALista(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente buscarPorIdCliente(Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarClienteALista(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarListaClientesALista(List<Cliente> listaClientes) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
