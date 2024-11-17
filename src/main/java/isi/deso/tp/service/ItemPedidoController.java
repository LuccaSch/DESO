package isi.deso.tp.service;

import isi.deso.tp.dao.ItemsPedidoDAO;
import isi.deso.tp.dao.ItemsPedidoFactoryDAO;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.DTO.ItemPedidoDTO;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoController {

    private ItemsPedidoFactoryDAO itemsPedidoJDBCFactory;

    private ItemsPedidoDAO itemsPedidoDAO;

    public ItemPedidoController() {
        this.itemsPedidoJDBCFactory = ItemsPedidoFactoryDAO.getFactory(ItemsPedidoFactoryDAO.JDBC_FACTORY);
        this.itemsPedidoDAO = this.itemsPedidoJDBCFactory.getUsuarioDAO();
    }

    public ItemsPedidoFactoryDAO getItemsPedidoJDBCFactory() {
        return itemsPedidoJDBCFactory;
    }

    public void setItemsPedidoJDBCFactory(ItemsPedidoFactoryDAO itemsPedidoJDBCFactory) {
        this.itemsPedidoJDBCFactory = itemsPedidoJDBCFactory;
    }

    public ItemsPedidoDAO getItemsPedidoDAO() {
        return this.itemsPedidoDAO;
    }

    public void setItemsPedidoDAO(ItemsPedidoDAO itemsPedidoDAO) {
        this.itemsPedidoDAO = itemsPedidoDAO;
    }

    public ItemPedido crearItemPedido(Integer idPedido, Integer id, ItemMenu itemMenu, Integer cantidad) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad);
        itemsPedidoDAO.agregarItemPedidoALista(itemPedido, idPedido);
        return itemPedido;
    }

    public ItemPedido crearItemPedido(Integer idPedido, Integer id, ItemMenu itemMenu, Integer cantidad, Double precio) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad, precio);
        itemsPedidoDAO.agregarItemPedidoALista(itemPedido, idPedido);
        return itemPedido;
    }

    public List<ItemPedido> filtrarPorVendedor(Integer idVendedor) {
        List<ItemPedido> listaFiltrada = null;
        try {
            listaFiltrada = this.itemsPedidoDAO.filtrarPorVendedor(idVendedor);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }

        return listaFiltrada;
    }

    public List<ItemPedido> ordenarPorPrecio() {
        List<ItemPedido> listaOrdenada = null;
        try {
            listaOrdenada = this.itemsPedidoDAO.ordenarPorPrecio();
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());
        }
        return listaOrdenada;
    }

    public List<ItemPedido> ordenarPorCantidad() {
        List<ItemPedido> listaOrdenada = null;
        try {
            listaOrdenada = this.itemsPedidoDAO.ordenarPorCantidad();
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }
        return listaOrdenada;
    }

    public List<ItemPedido> buscarPorRestaurante(Integer idRestaurante) {
        List<ItemPedido> listaBusqueda = null;
        try {
            listaBusqueda = this.itemsPedidoDAO.buscarPorRestaurante(idRestaurante);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }

        return listaBusqueda;
    }

    public List<ItemPedido> buscarPorRangoDePrecio(Double precioMin, Double precioMax) {
        List<ItemPedido> listaFiltrada = null;
        try {
            listaFiltrada = this.itemsPedidoDAO.buscarPorRangoDePrecio(precioMin, precioMax);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }
        return listaFiltrada;
    }

    public void deletePedidoPorId(Integer idPedido) {
        try {
            itemsPedidoDAO.eliminarItemPedidoPorId(idPedido);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }
    }

    public ItemPedido convertirDesdeDTO(ItemPedidoDTO itemPedidoDTO) {
        return new ItemPedido(itemPedidoDTO.getId(), itemPedidoDTO.getCantidad());
    }

    public ItemPedidoDTO convertirADTO(ItemPedido itemPedido) {
        return new ItemPedidoDTO(itemPedido.getId(), itemPedido.getItemMenu().getId(), itemPedido.getCantidad(), itemPedido.getPrecio());
    }

    public List<ItemPedidoDTO> convertirAListaDTO(List<ItemPedido> itemsPedido) {
        List<ItemPedidoDTO> itemsPedidoDTO = new ArrayList<>();
        itemsPedido.forEach(itemPedido -> itemsPedidoDTO.add(convertirADTO(itemPedido)));
        return itemsPedidoDTO;
    }

    public List<ItemPedido> convertirDesdeListaDTO(List<ItemPedidoDTO> itemsPedidoDTO) {
        List<ItemPedido> itemsPedido = new ArrayList<>();
        itemsPedidoDTO.forEach(itemPedidoDTO -> itemsPedido.add(convertirDesdeDTO(itemPedidoDTO)));
        return itemsPedido;
    }
}
