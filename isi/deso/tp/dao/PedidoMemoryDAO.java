/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.dao;

import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franciscokuchen
 */
public class PedidoMemoryDAO implements PedidoDAO {

    private List<Pedido> listaPedidos;

    @Override
    public List<Pedido> buscarPorRestaurante(int idVendedor) {
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

}
