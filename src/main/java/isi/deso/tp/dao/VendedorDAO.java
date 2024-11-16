package isi.deso.tp.dao;

import isi.deso.tp.exception.VendedorNoEncontradoException;
import isi.deso.tp.model.Vendedor;
import java.util.List;

public interface VendedorDAO {

    public List<Vendedor> listarVendedor();

    public void crearVendedor(Vendedor vendedor);

    public void actualizarVendedor(Vendedor vendedor);

    public void eliminarVendedor(Integer idVendedor);

    public Vendedor buscarVendedor(Integer idVendedor);

    public Vendedor buscarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException;

}
