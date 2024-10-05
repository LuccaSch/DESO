package isi.deso.tp.service;

import isi.deso.tp.dao.ItemsPedidoDAO;
import isi.deso.tp.dao.ItemsPedidoFactoryDAO;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.DTO.ItemPedidoDTO;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class GestorItemPedido {

    private ItemsPedidoFactoryDAO itemsPedidoMemoryFactoryDAO = ItemsPedidoFactoryDAO.getFactory(ItemsPedidoFactoryDAO.MEMORY_FACTORY);

    private ItemsPedidoDAO itemsPedidoDAO = itemsPedidoMemoryFactoryDAO.getUsuarioDao();

    public ItemPedido crearItemPedido(int id, ItemMenu itemMenu, int cantidad) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad);

        return itemPedido;
    }

    public ItemPedido crearItemPedido(int id, ItemMenu itemMenu, int cantidad, double precio) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad, precio);

        return itemPedido;
    }

    public void setLista(List<ItemPedido> listaItemPedidos) {
        itemsPedidoDAO.setLista(listaItemPedidos);
    }

    public List<ItemPedido> getLista() {
        return itemsPedidoDAO.getLista();
    }

    public List<ItemPedido> filtrarPorVendedor(int idVendedor) {
        List<ItemPedido> listaFiltrada = null;
        try {
            listaFiltrada = itemsPedidoDAO.filtrarPorVendedor(idVendedor);
        } catch (ItemNoEncontradoException excep) {
            System.err.println(excep.getMessage());

        }

        return listaFiltrada;
    }

    public List<ItemPedido> ordenarPorPrecio() {
        List<ItemPedido> listaOrdenada = null;
        try {
            listaOrdenada = itemsPedidoDAO.ordenarPorPrecio();
        } catch (ItemNoEncontradoException excep) {
            System.err.println(excep.getMessage());
        }
        return listaOrdenada;
    }

    public List<ItemPedido> ordenarPorCantidad() {
        List<ItemPedido> listaOrdenada = null;
        try {
            listaOrdenada = itemsPedidoDAO.ordenarPorCantidad();
        } catch (ItemNoEncontradoException excep) {
            System.err.println(excep.getMessage());

        }
        return listaOrdenada;
    }

    public List<ItemPedido> buscarPorRestaurante(int idRestaurante) {
        List<ItemPedido> listaBusqueda = null;
        try {
            listaBusqueda = itemsPedidoDAO.buscarPorRestaurante(idRestaurante);
        } catch (ItemNoEncontradoException excep) {
            System.err.println(excep.getMessage());

        }

        return listaBusqueda;
    }

    public List<ItemPedido> buscarPorRangoDePrecio(double precioMin, double precioMax) {
        List<ItemPedido> listaFiltrada = null;
        try {
            listaFiltrada = itemsPedidoDAO.buscarPorRangoDePrecio(precioMin, precioMax);
        } catch (ItemNoEncontradoException excep) {
            System.err.println(excep.getMessage());

        }
        return listaFiltrada;
    }

    public void deletePedidoPorId(List<Pedido> pedidos, int idPedido) {
        pedidos.remove(idPedido);
        pedidos.removeIf(pedido -> pedido.getId() == idPedido);
    }

    public ItemPedido convertirDesdeDTO(ItemPedidoDTO itemPedidoDTO) {
        return new ItemPedido(itemPedidoDTO.getId(), itemPedidoDTO.getCantidad());
    }
    
    public ItemPedidoDTO convertirADTO(ItemPedido itemPedido){
        return new ItemPedidoDTO(itemPedido.getId(), itemPedido.getItemMenu().getId(), itemPedido.getCantidad(), itemPedido.getPrecio());
    }
    
    public List<ItemPedidoDTO> convertirAListaDTO(List<ItemPedido> itemsPedido){
        List<ItemPedidoDTO> itemsPedidoDTO = new ArrayList<>();
        for (ItemPedido item : itemsPedido) {
            ItemPedidoDTO itemPedidoDTO = convertirADTO(item);
            itemsPedidoDTO.add(itemPedidoDTO);
        }
        return itemsPedidoDTO;
    }
}
