package isi.deso.tp.dao;

import isi.deso.tp.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    public void agregarClienteALista(Cliente cliente);

    public Cliente buscarPorIdCliente(int idCliente);

    public void eliminarClienteALista(Cliente cliente);

    public void eliminarListaClientesALista(List<Cliente> listaClientes);

}
