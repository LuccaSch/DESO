package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.DTO.ClienteDTO;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class GestorCliente {

    private ClienteDAO clienteDAO;

    public GestorCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente crearCliente() {
        return new Cliente();
    }

    public Cliente crearCliente(int id, String nombre, String cuit, String email, String direccion, Coordenada coordenada) {
        return new Cliente(id, nombre, cuit, email, direccion, coordenada);
    }

    public void agregarPedido(Cliente cliente, Pedido pedidoNuevo) {
        cliente.getListaPedidos().add(pedidoNuevo);
    }

    public Cliente convertirClienteDesdeDTO(ClienteDTO clienteDTO) {

        Cliente clienteNuevo = new Cliente(clienteDTO.getNombre(), clienteDTO.getCuit(), clienteDTO.getEmail(), clienteDTO.getDireccion());

        return clienteNuevo;
    }

    /*
    public Cliente obtenerClienteDesdeId(int idCliente) {

    }
     */
    public List<Cliente> filterClienteId(List<Cliente> clientes, int filtroId) {
        List<Cliente> clientesAux = new ArrayList<>();

        for (Cliente i : clientes) {
            if (i.getId() == filtroId) {
                clientesAux.add(i);
            }
        }

        return clientesAux;
    }

    public List<Cliente> filterClienteNombre(List<Cliente> clientes, String filtroNombre) {
        List<Cliente> clientesAux = new ArrayList<>();

        for (Cliente i : clientes) {
            if (i.getNombre().equals(filtroNombre)) {
                clientesAux.add(i);
            }
        }

        return clientesAux;
    }

    public void deleteClienteId(List<Cliente> clientes, int filtroId) {
        clientes.removeIf(cliente -> cliente.getId() == filtroId);
    }

    public void deleteClienteNombre(List<Cliente> clientes, String filtroNombre) {
        clientes.removeIf(cliente -> cliente.getNombre().equals(filtroNombre));
    }

    public void deleteClientePorPosicion(List<Cliente> clientes, int posicion) {
        clientes.remove(posicion);

    }
}
