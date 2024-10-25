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

public class ItemPedidoController {

    /*
        FALTAN METODOS:
        mostrarLista() es getLista() ??
        crearNuevo() ??

        modificar()
        buscar(int id)

        VER EJEMPLO EN VendedorController
     */
    private ItemsPedidoFactoryDAO itemsPedidoMemoryFactoryDAO;

    private ItemsPedidoDAO itemsPedidoDAO;

    public ItemPedidoController() {
        this.itemsPedidoMemoryFactoryDAO = ItemsPedidoFactoryDAO.getFactory(ItemsPedidoFactoryDAO.MEMORY_FACTORY);
        this.itemsPedidoDAO = this.itemsPedidoMemoryFactoryDAO.getUsuarioDAO();
    }

    public ItemsPedidoFactoryDAO getItemsPedidoMemoryFactoryDAO() {
        return itemsPedidoMemoryFactoryDAO;
    }

    public void setItemsPedidoMemoryFactoryDAO(ItemsPedidoFactoryDAO itemsPedidoMemoryFactoryDAO) {
        this.itemsPedidoMemoryFactoryDAO = itemsPedidoMemoryFactoryDAO;
    }

    public ItemsPedidoDAO getItemsPedidoDAO() {
        return this.itemsPedidoDAO;
    }

    public void setItemsPedidoDAO(ItemsPedidoDAO itemsPedidoDAO) {
        this.itemsPedidoDAO = itemsPedidoDAO;
    }

    public ItemPedido crearItemPedido(int id, ItemMenu itemMenu, int cantidad) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad);

        return itemPedido;
    }

    public ItemPedido crearItemPedido(int id, ItemMenu itemMenu, int cantidad, double precio) {
        ItemPedido itemPedido = new ItemPedido(id, itemMenu, cantidad, precio);

        return itemPedido;
    }

    public void setLista(List<ItemPedido> listaItemPedidos) {
        this.itemsPedidoDAO.setLista(listaItemPedidos);
    }

    public List<ItemPedido> getLista() {
        return this.itemsPedidoDAO.getLista();
    }

    public List<ItemPedido> filtrarPorVendedor(int idVendedor) {
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

    public List<ItemPedido> buscarPorRestaurante(int idRestaurante) {
        List<ItemPedido> listaBusqueda = null;
        try {
            listaBusqueda = this.itemsPedidoDAO.buscarPorRestaurante(idRestaurante);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

        }

        return listaBusqueda;
    }

    public List<ItemPedido> buscarPorRangoDePrecio(double precioMin, double precioMax) {
        List<ItemPedido> listaFiltrada = null;
        try {
            listaFiltrada = this.itemsPedidoDAO.buscarPorRangoDePrecio(precioMin, precioMax);
        } catch (ItemNoEncontradoException excep) {
            System.err.println("Desde ItemPedidoController: " + excep.getMessage());

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
