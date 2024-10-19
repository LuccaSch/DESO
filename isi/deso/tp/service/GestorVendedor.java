package isi.deso.tp.service;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Vendedor;
import java.util.ArrayList;
import java.util.List;

public class GestorVendedor {

    public GestorVendedor() {
    }

    public Vendedor crearVendedor() {
        return new Vendedor();
    }

    public Vendedor crearVendedor(int id, String nombre, String direccion, Coordenada c) {
        return new Vendedor(id, nombre, direccion, c);
    }

    public List<Vendedor> filterVendedoresId(List<Vendedor> vendedores, int filtroId) {
        List<Vendedor> vendedoresAux = new ArrayList<>();

        for (Vendedor i : vendedores) {
            if (i.getId() == filtroId) {
                vendedoresAux.add(i);
            }
        }

        return vendedoresAux;
    }

    public List<Vendedor> filterVendedoresNombre(List<Vendedor> vendedores, String filtroNombre) {
        List<Vendedor> vendedoresAux = new ArrayList<>();

        for (Vendedor i : vendedores) {
            if (i.getNombre().equals(filtroNombre)) {
                vendedoresAux.add(i);
            }
        }

        return vendedoresAux;
    }

    public void deleteVendedoresId(List<Vendedor> vendedores, int filtroId) {
        vendedores.removeIf(vendedor -> vendedor.getId() == filtroId);
    }

    public void deleteVendedoresNombre(List<Vendedor> vendedores, String filtroString) {
        vendedores.removeIf(vendedor -> vendedor.getNombre().equals(filtroString));
    }

    public void deleteVendedoresPosicion(List<Vendedor> vendedores, int posicion) {
        vendedores.remove(posicion);
    }

    public List<Pedido> buscarPedidosPorEstado(int idVendedor, EstadoPedidoEnum estadoPedido) {
        GestorPedido gestorPedido = new GestorPedido(PedidoMemoryDAO.getInstance());
        return gestorPedido.filtrarPorEstado(gestorPedido.buscarPorRestaurante(idVendedor), estadoPedido);
    }

    public void actualizarEstado(EstadoPedidoEnum estadoNuevo, List<Pedido> pedidosPorEstado) {
        GestorPedido gestorPedido =  new GestorPedido(PedidoMemoryDAO.getInstance());
        for (Pedido pedidos : pedidosPorEstado) {
            gestorPedido.actualizarEstado(estadoNuevo, pedidos);
        }
    }
}
