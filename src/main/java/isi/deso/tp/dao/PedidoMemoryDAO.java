package isi.deso.tp.dao;

import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoMemoryDAO implements PedidoDAO {

    // Atributos que simulan la base de datos
    private List<Pedido> listaPedidos;
    private static PedidoMemoryDAO pedidoMemoryDAO;

    private PedidoMemoryDAO() {
    }

    public static PedidoMemoryDAO getInstance() {
        if (PedidoMemoryDAO.pedidoMemoryDAO == null) {
            PedidoMemoryDAO.pedidoMemoryDAO = new PedidoMemoryDAO();
            PedidoMemoryDAO.pedidoMemoryDAO.listaPedidos = new ArrayList<>();

        }
        return PedidoMemoryDAO.pedidoMemoryDAO;
    }

    @Override
    public void agregarPedidoALista(Pedido pedido) {
        this.listaPedidos.add(pedido);
    }

    @Override
    public List<Pedido> getListaPedidos() {
        return this.listaPedidos;
    }

    @Override
    public List<Pedido> buscarPorIdVendedor(int idVendedor) {
        List<Pedido> listaBusqueda = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            for (ItemPedido itemPedido : pedido.getPedidoDetalle()) {
                if (itemPedido.getVendedor().getId() == idVendedor && !listaBusqueda.contains(pedido)) {
                    listaBusqueda.add(pedido);
                }
            }

        }

        return listaBusqueda;
    }

    @Override
    public List<Pedido> buscarPorIdPedido(int idPedido) {
        List<Pedido> listaBusqueda = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {

            if (pedido.getId() == idPedido && !listaBusqueda.contains(pedido)) {
                listaBusqueda.add(pedido);

            }

        }

        return listaBusqueda;
    }

}
