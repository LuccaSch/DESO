package isi.deso.tp.dao;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemPedido;
import java.util.List;

public class ItemsPedidoMemoryDAO implements ItemsPedidoDAO {

    // Atributos que simulan la base de datos
    private List<ItemPedido> listaItemPedidos;

    @Override
    public List<ItemPedido> getLista() {
        return this.listaItemPedidos;
    }

    @Override
    public void setLista(List<ItemPedido> listaItemPedidos) {
        this.listaItemPedidos = listaItemPedidos;
    }

    @Override
    public List<ItemPedido> filtrarPorVendedor(int idVendedor) throws ItemNoEncontradoException {

        List<ItemPedido> listaFiltrada = this.getLista().stream()
                .filter(i -> i.getVendedor().getId() == idVendedor).distinct()
                .toList();

        if (listaFiltrada.isEmpty()) {
            throw new ItemNoEncontradoException("ItemsPedido vacio para filtrar con vendedor con id: " + idVendedor);
        }

        return listaFiltrada;

    }

    @Override
    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        List<ItemPedido> listaOrdenada = this.getLista().stream()
                .sorted((item1, item2) -> Double.compare(item1.getItemMenu().getPrecio(), item2.getItemMenu().getPrecio()))
                .toList();

        if (listaOrdenada.isEmpty()) {
            throw new ItemNoEncontradoException("Lista vacia despues de ordenar por precio.");
        }

        return listaOrdenada;

    }

    @Override
    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException {
        List<ItemPedido> listaOrdenada = this.getLista().stream()
                .sorted((item1, item2) -> Double.compare(item1.getCantidad(), item2.getCantidad()))
                .toList();

        if (listaOrdenada.isEmpty()) {
            throw new ItemNoEncontradoException("Lista vacia despues de ordenar por precio.");
        }

        return listaOrdenada;

    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(int idVendedor) throws ItemNoEncontradoException {
        List<ItemPedido> listaBusqueda = this.getLista().stream()
                .filter(item -> item.getVendedor().getId() == idVendedor)
                .toList();

        if (listaBusqueda.isEmpty()) {
            throw new ItemNoEncontradoException("Lista vacia despues de buscar por Restaurante.");
        }

        return listaBusqueda;

    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecio(double precioMin, double precioMax) throws ItemNoEncontradoException {
        List<ItemPedido> listaBusqueda = this.getLista().stream()
                .filter(item -> (precioMin <= item.getPrecio() && item.getPrecio() <= precioMax))
                .toList();

        if (listaBusqueda.isEmpty()) {
            throw new ItemNoEncontradoException("Lista vacia despues de buscar por rango de precio (" + precioMin + "," + precioMax);
        }

        return listaBusqueda;

    }

}
