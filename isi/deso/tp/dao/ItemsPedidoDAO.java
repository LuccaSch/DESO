package isi.deso.tp.dao;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemPedido;
import java.util.List;

public interface ItemsPedidoDAO {

    public List<ItemPedido> filtrarPorVendedor(int idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRestaurante(int idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRangoDePrecio(double precioMin, double precioMax) throws ItemNoEncontradoException;

    public void setLista(List<ItemPedido> listaItemPedidos);

    public List<ItemPedido> getLista();

}
