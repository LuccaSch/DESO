package isi.deso.tp.dao;

import isi.deso.tp.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteMemoryDAO implements ClienteDAO {

    // Atributos que simulan la base de datos
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
    public void agregarClienteALista(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    @Override
    public Cliente buscarPorIdCliente(int idCliente) {
        Cliente cliente = null;
        for (Cliente c : this.listaClientes) {
            if (c.getId() == idCliente) {
                cliente = c;
            }
        }
        return cliente;
    }

    @Override
    public void eliminarClienteALista(Cliente cliente) {
        this.listaClientes.remove(cliente);
    }

    @Override
    public void eliminarListaClientesALista(List<Cliente> listaClientes) {
        this.listaClientes.removeAll(listaClientes);
    }

    @Override
    public List<Cliente> listarCliente() {
        return this.listaClientes;
    }

    @Override
    public void crearCliente(Cliente cliente) {
        this.agregarClienteALista(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        this.listaClientes.remove(cliente.getId());
        this.listaClientes.add(cliente);

    }

}
