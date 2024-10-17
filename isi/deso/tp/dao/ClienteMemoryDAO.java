package isi.deso.tp.dao;

import isi.deso.tp.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteMemoryDAO implements ClienteDAO {

    private List<Cliente> listaClientes;
    private static ClienteMemoryDAO clienteMemoryDAO;

    private ClienteMemoryDAO() {
    }

    public static ClienteMemoryDAO getInstance() {
        if (ClienteMemoryDAO.clienteMemoryDAO == null) {
            ClienteMemoryDAO.clienteMemoryDAO = new ClienteMemoryDAO();
            ClienteMemoryDAO.clienteMemoryDAO.listaClientes = new ArrayList<>();

        }

        return ClienteMemoryDAO.clienteMemoryDAO;
    }

    @Override
    public List<Cliente> buscarPorIdCliente(int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
