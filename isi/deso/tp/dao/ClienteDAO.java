package isi.deso.tp.dao;

import isi.deso.tp.model.Cliente;
import java.util.List;

public interface ClienteDAO {

    public List<Cliente> buscarPorIdCliente(int idCliente);

}
