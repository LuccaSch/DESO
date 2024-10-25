package isi.deso.tp.service;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.dao.VendedorDAO;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {

    /*

        buscar(int id) es filterVendedoresPorId()


     */
    private VendedorDAO vendedorDAO;

    public VendedorController(VendedorDAO vendedorDAO) {
        this.vendedorDAO = vendedorDAO;
    }

    public VendedorController() {
    }

    public Vendedor crearNuevoVendedor(String nombre, String direccion, Coordenada coordenada) {
        Vendedor vendedor = new Vendedor(nombre, direccion, coordenada);
        // VERIFICAR QUE NO EXISTE OTRO CON MISMO ID, USAR UUID?
        vendedor.setId(0);
        vendedor.setMenu(new ArrayList<>());
        return vendedor;
    }

    public Vendedor crearVendedor() {
        return new Vendedor();
    }

    public Vendedor crearVendedor(int id, String nombre, String direccion, Coordenada coordenada) {
        return new Vendedor(id, nombre, direccion, coordenada);
    }

    // VER
    public List<Vendedor> mostrarListaVendedor() {
        return vendedorDAO.listarVendedor();
    }

    public List<Vendedor> filterVendedoresPorId(List<Vendedor> vendedores, int filtroId) {
        List<Vendedor> vendedoresAux = new ArrayList<>();

        for (Vendedor i : vendedores) {
            if (i.getId() == filtroId) {
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

    public void modificarVendedor(int id, String nombre, String direccion, Coordenada coordenadas) {
        Vendedor vendedorAModificar = this.vendedorDAO.buscarVendedor(id).getFirst();
        vendedorAModificar.setNombre(nombre);
        vendedorAModificar.setDireccion(direccion);
        vendedorAModificar.setCoordenada(coordenadas);
    }

    public void deleteVendedoresPorId(List<Vendedor> vendedores, int filtroId) {
        vendedores.removeIf(vendedor -> vendedor.getId() == filtroId);
    }

    public void deleteVendedoresPorNombre(List<Vendedor> vendedores, String filtroString) {
        vendedores.removeIf(vendedor -> vendedor.getNombre().equals(filtroString));
    }

    public void deleteVendedoresPorPosicion(List<Vendedor> vendedores, int posicion) {
        vendedores.remove(posicion);
    }

    public List<Pedido> buscarPedidosPorEstado(int idVendedor, EstadoPedidoEnum estadoPedido) {
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
