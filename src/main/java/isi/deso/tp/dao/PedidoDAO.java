package isi.deso.tp.dao;

import isi.deso.tp.model.Pedido;
import java.util.List;

public interface PedidoDAO {

    public List<Pedido> listarPedidos();

    public void agregarPedidoALista(Pedido pedido);

    public List<Pedido> buscarPedidosPorIdVendedor(Integer idVendedor);

    public List<Pedido> buscarPedidosPorId(Integer idPedido);
}
