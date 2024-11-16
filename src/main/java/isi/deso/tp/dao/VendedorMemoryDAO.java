package isi.deso.tp.dao;

import isi.deso.tp.exception.VendedorNoEncontradoException;
import isi.deso.tp.model.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class VendedorMemoryDAO implements VendedorDAO {

    private List<Vendedor> listaVendedores;
    private static VendedorMemoryDAO vendedorMemoryDAO;

    private VendedorMemoryDAO() {
    }

    public static VendedorMemoryDAO getInstance() {
        if (VendedorMemoryDAO.vendedorMemoryDAO == null) {
            VendedorMemoryDAO.vendedorMemoryDAO = new VendedorMemoryDAO();
            VendedorMemoryDAO.vendedorMemoryDAO.listaVendedores = new ArrayList<>();

        }

        return VendedorMemoryDAO.vendedorMemoryDAO;
    }

    @Override
    public List<Vendedor> listarVendedor() {
        return this.listaVendedores;
    }

    @Override
    public void crearVendedor(Vendedor Vendedor) {
        this.listaVendedores.add(Vendedor);
    }

    @Override
    public void actualizarVendedor(Vendedor Vendedor) {
        this.listaVendedores.remove(Vendedor.getId());
        this.listaVendedores.add(Vendedor);
    }

    @Override
    public void eliminarVendedor(Integer idVendedor) {
        this.listaVendedores.remove(idVendedor);
    }

    @Override
    public Vendedor buscarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {
        Vendedor vendedor = null;
        VendedorNoEncontradoException excep;
        for (Vendedor v : this.listaVendedores) {
            if (v.getNombre().equals(nombre)) {
                vendedor = v;
            }
        }
        return vendedor;
    }

    @Override
    public Vendedor buscarVendedor(Integer idVendedor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarVendedor'");
    }

}
