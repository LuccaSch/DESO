package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteDAO;
import isi.deso.tp.dao.ClienteJDBC;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.DTO.ClienteDTO;
import isi.deso.tp.model.MercadoPagoStrategy;
import isi.deso.tp.model.PagoStrategy;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> index() {
        ClienteJDBC clienteJDBC = new ClienteJDBC();
        return clienteJDBC.listarClientes();
    }
    
    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }
    
    public Cliente crearCliente() {
        Cliente cliente = new Cliente();
        clienteDAO.agregarClienteALista(cliente);
        return cliente;
    }

    public Cliente crearCliente(int id, String nombre, String cuit, String email, String direccion, Coordenada coordenada) {
        Cliente cliente = new Cliente(id, nombre, cuit, email, direccion, coordenada);
        clienteDAO.agregarClienteALista(cliente);
        return cliente;
    }
    
    public void agregarClienteALista(Cliente cliente) {
        clienteDAO.listarClientes().add(cliente);
    }
    
    public void agregarPedido(Cliente cliente, Pedido pedidoNuevo) {
        cliente.getListaPedidos().add(pedidoNuevo);
    }
    
    public Cliente buscarPorNombreCliente(String nombreCliente) {
        Cliente cliente = null;
        for (Cliente c : clienteDAO.listarClientes()) {
            if (c.getNombre().equals(nombreCliente)) {
                cliente = c;
            }
        }
        return cliente;
    }
    
    public Cliente convertirClienteDesdeDTO(ClienteDTO clienteDTO) {
        Cliente clienteNuevo = new Cliente(clienteDTO.getNombre(), clienteDTO.getCuit(), clienteDTO.getEmail(), clienteDTO.getDireccion());
        clienteDAO.agregarClienteALista(clienteNuevo);

        return clienteNuevo;
    }

    public List<Cliente> filterClientePorId(List<Cliente> clientes, int filtroId) {
        List<Cliente> clientesAux = new ArrayList<>();

        for (Cliente i : clientes) {
            if (i.getId() == filtroId) {
                clientesAux.add(i);
            }
        }

        return clientesAux;
    }

    public List<Cliente> filterClientePorNombre(List<Cliente> clientes, String filtroNombre) {
        List<Cliente> clientesAux = new ArrayList<>();

        for (Cliente i : clientes) {
            if (i.getNombre().equals(filtroNombre)) {
                clientesAux.add(i);
            }
        }

        return clientesAux;
    }

    public void deleteClientePorId(List<Cliente> clientes, int filtroId) {
        clientes.removeIf(cliente -> cliente.getId() == filtroId);
        clienteDAO.eliminarListaClientesALista(clientes);
    }

    public void deleteClientePorNombre(List<Cliente> clientes, String filtroNombre) {
        clientes.removeIf(cliente -> cliente.getNombre().equals(filtroNombre));
        clienteDAO.eliminarListaClientesALista(clientes);
    }

    public void deleteClientePorPosicion(List<Cliente> clientes, int posicion) {
        clientes.remove(posicion);
        clienteDAO.eliminarListaClientesALista(clientes);
    }

    public PagoStrategy elegirMetodoPago(Cliente cliente) {
        PagoStrategy mercadoPagoStrategy = new MercadoPagoStrategy(cliente.getNombre().toLowerCase().replaceAll("\\s+", "") + ".mp"
        );

        return mercadoPagoStrategy;
    }

    public void generarPagoPara(int idCliente, int idPedido) {
        Cliente cliente = clienteDAO.buscarPorIdCliente(idCliente);
        for (Pedido pedido : cliente.getListaPedidos()) {
            if (pedido.getId() == idPedido) {

                pedido.setPagoStrategy(this.elegirMetodoPago(cliente));
                pedido.getContextoPago().getPagoStrategy().generarPago(pedido);
            }
        }
    }
}
