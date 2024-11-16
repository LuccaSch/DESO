package isi.deso.tp.dao;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemPedido;
import java.util.List;

public interface ItemsPedidoDAO {

    public List<ItemPedido> getLista();

    public void setLista(List<ItemPedido> listaItemPedidos);

    public void agregarItemPedidoALista(ItemPedido itemPedido);

    public List<ItemPedido> filtrarPorVendedor(Integer idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException;

    public List<ItemPedido> buscarItemsPedidoPorId(Integer idItemPedido) throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRestaurante(Integer idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRangoDePrecio(Double precioMin, Double precioMax) throws ItemNoEncontradoException;

    public void eliminarItemPedidoPorId(Integer idItemPedido) throws ItemNoEncontradoException;

}
