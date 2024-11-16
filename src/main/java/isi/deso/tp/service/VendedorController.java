package isi.deso.tp.service;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.dao.VendedorDAO;
import isi.deso.tp.exception.VendedorNoEncontradoException;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {

    private VendedorDAO vendedorDAO;

    public VendedorController(VendedorDAO vendedorDAO) {
        this.vendedorDAO = vendedorDAO;
    }

    public VendedorController() {
    }

    public Vendedor crearNuevoVendedor(String nombre, String direccion, Coordenada coordenada) {
        Vendedor vendedor = new Vendedor(nombre, direccion, coordenada);
        vendedor.setId(0);
        vendedor.setMenu(new ArrayList<>());
        return vendedor;
    }

    public Vendedor crearVendedor() {
        return new Vendedor();
    }

    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedor;
    }

    public Vendedor crearVendedor(Integer id, String nombre, String direccion, Coordenada coordenada) {
        return new Vendedor(id, nombre, direccion, coordenada);
    }

    public List<Vendedor> listarVendedor() {
        return vendedorDAO.listarVendedores();
    }

    public List<Vendedor> filterVendedoresPorId(List<Vendedor> vendedores, Integer filtroId) {
        List<Vendedor> vendedoresAux = new ArrayList<>();

        for (Vendedor i : vendedores) {
            if (i.getId() == filtroId.intValue()) {
                vendedoresAux.add(i);
            }
        }

        return vendedoresAux;
    }

    public List<Vendedor> filterVendedoresPorNombre(List<Vendedor> vendedores, String filtroNombre) {
        List<Vendedor> vendedoresAux = new ArrayList<>();

        for (Vendedor i : vendedores) {
            if (i.getNombre().equals(filtroNombre)) {
                vendedoresAux.add(i);
            }
        }

        return vendedoresAux;
    }

    public void VendedorNoEncontrado(Boolean condicion) throws VendedorNoEncontradoException {
        if (!condicion) {
            throw new VendedorNoEncontradoException();
        }
    }

    public Vendedor buscarVendedorPorNombre(String nombre) throws VendedorNoEncontradoException {
        Vendedor vendedor = null;
        for (Vendedor v : vendedorDAO.listarVendedores()) {
            if (v.getNombre().equals(nombre)) {
                vendedor = v;
            }
        }
        if (vendedor == null) {
            VendedorNoEncontrado(true);
        }
        return vendedor;
    }

    public void modificarVendedor(Integer id, String nombre, String direccion, Coordenada coordenadas) {
        Vendedor vendedorAModificar = this.vendedorDAO.buscarVendedor(id);
        vendedorAModificar.setNombre(nombre);
        vendedorAModificar.setDireccion(direccion);
        vendedorAModificar.setCoordenada(coordenadas);
    }

    public void deleteVendedoresPorId(List<Vendedor> vendedores, Integer filtroId) {
        vendedorDAO.listarVendedores().removeIf(vendedor -> vendedor.getId() == filtroId.intValue());
    }

    public void deleteVendedoresPorNombre(List<Vendedor> vendedores, String filtroString) {
        vendedorDAO.listarVendedores().removeIf(vendedor -> vendedor.getNombre().equals(filtroString));
    }

    public void deleteVendedoresPorPosicion(List<Vendedor> vendedores, Integer posicion) {
        vendedorDAO.listarVendedores().remove(posicion.intValue());
    }

    public List<Pedido> buscarPedidosPorEstado(Integer idVendedor, EstadoPedidoEnum estadoPedido) {
        PedidoController pedidoController = new PedidoController(PedidoMemoryDAO.getInstance());
        return pedidoController.filtrarPorEstado(pedidoController.buscarPorRestaurante(idVendedor), estadoPedido);
    }

    public void actualizarEstado(EstadoPedidoEnum estadoNuevo, List<Pedido> pedidosPorEstado) {
        PedidoController pedidoController = new PedidoController(PedidoMemoryDAO.getInstance());
        for (Pedido pedidos : pedidosPorEstado) {
            pedidoController.actualizarEstado(estadoNuevo, pedidos);
        }
    }
}
