package isi.deso.tp.dao;

import isi.deso.tp.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    public List<Cliente> listarClientes();

    public void agregarClienteALista(Cliente cliente);

    public Cliente buscarClientePorId(Integer idCliente);

    public void eliminarClienteALista(Cliente cliente);

    public void eliminarListaClientesALista(List<Cliente> listaClientes);

}
