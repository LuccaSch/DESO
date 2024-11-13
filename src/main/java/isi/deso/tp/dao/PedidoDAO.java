package isi.deso.tp.dao;

import isi.deso.tp.model.Pedido;
import java.util.List;

public interface PedidoDAO {

    public List<Pedido> getListaPedidos();

    public void agregarPedidoALista(Pedido pedido);

    public List<Pedido> buscarPorIdVendedor(int idVendedor);

    public List<Pedido> buscarPorIdPedido(int idPedido);
}
