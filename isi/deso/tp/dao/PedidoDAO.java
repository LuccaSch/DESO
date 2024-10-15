package isi.deso.tp.dao;

import isi.deso.tp.model.Pedido;
import java.util.List;

public interface PedidoDAO {

    public List<Pedido> buscarPorRestaurante(int idVendedor);

}
