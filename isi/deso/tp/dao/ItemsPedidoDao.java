package isi.deso.tp.dao;

import java.util.List;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemPedido;

public interface ItemsPedidoDao {

    public List<ItemPedido> filtrarPorVendedor(int idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException;

    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRestaurante(int idVendedor) throws ItemNoEncontradoException;

    public List<ItemPedido> buscarPorRangoDePrecio(double precioMin, double precioMax) throws ItemNoEncontradoException;

}
