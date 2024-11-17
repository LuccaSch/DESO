package isi.deso.tp.dao.jdbc;

import isi.deso.tp.dao.ItemsPedidoDAO;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsPedidoJDBC implements ItemsPedidoDAO {

    private final ItemMenuJDBC itemMenuJDBC;

    public ItemsPedidoJDBC() {
        this.itemMenuJDBC = new ItemMenuJDBC();
    }

    @Override
    public void agregarItemPedidoALista(ItemPedido itemPedido, Integer idPedido) {
        String query = "INSERT INTO ItemPedido (id, item_menu_id, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, itemPedido.getId());
            ps.setInt(3, itemPedido.getItemMenu().getId());
            ps.setInt(4, itemPedido.getCantidad());
            ps.setDouble(5, itemPedido.getPrecio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al agregar el item al pedido", ex);
        }
    }

    @Override
    public List<ItemPedido> filtrarPorVendedor(Integer idVendedor) throws ItemNoEncontradoException {
        String query = "SELECT * FROM ItemPedido WHERE vendedor_id = ?";
        List<ItemPedido> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idVendedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setId(rs.getInt("id"));
                    itemPedido.setCantidad(rs.getInt("cantidad"));
                    itemPedido.setPrecio(rs.getDouble("precio"));
                    int itemMenuId = rs.getInt("item_menu_id");
                    ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).get(0);
                    itemPedido.setItemMenu(itemMenu);
                    items.add(itemPedido);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al filtrar items por vendedor", ex);
        }

        if (items.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items para el vendedor con ID: " + idVendedor);
        }

        return items;
    }

    @Override
    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        String query = "SELECT * FROM ItemPedido ORDER BY precio";
        List<ItemPedido> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setCantidad(rs.getInt("cantidad"));
                itemPedido.setPrecio(rs.getDouble("precio"));
                int itemMenuId = rs.getInt("item_menu_id");
                ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).get(0);
                itemPedido.setItemMenu(itemMenu);
                items.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al ordenar items por precio", ex);
        }

        if (items.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items en la base de datos");
        }

        return items;
    }

    @Override
    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException {
        String query = "SELECT * FROM ItemPedido ORDER BY cantidad";
        List<ItemPedido> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setId(rs.getInt("id"));
                itemPedido.setCantidad(rs.getInt("cantidad"));
                itemPedido.setPrecio(rs.getDouble("precio"));
                int itemMenuId = rs.getInt("item_menu_id");
                ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).get(0);
                itemPedido.setItemMenu(itemMenu);
                items.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al ordenar items por cantidad", ex);
        }

        if (items.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items en la base de datos");
        }

        return items;
    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(Integer idVendedor) throws ItemNoEncontradoException {
        String query = "SELECT * FROM ItemPedido WHERE restaurante_id = ?";
        List<ItemPedido> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idVendedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setId(rs.getInt("id"));
                    itemPedido.setCantidad(rs.getInt("cantidad"));
                    itemPedido.setPrecio(rs.getDouble("precio"));
                    int itemMenuId = rs.getInt("item_menu_id");
                    ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).get(0);
                    itemPedido.setItemMenu(itemMenu);
                    items.add(itemPedido);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al buscar items por restaurante", ex);
        }

        if (items.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items para el restaurante con ID: " + idVendedor);
        }

        return items;
    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecio(Double precioMin, Double precioMax) throws ItemNoEncontradoException {
        String query = "SELECT * FROM ItemPedido WHERE precio BETWEEN ? AND ?";
        List<ItemPedido> items = new ArrayList<>();
        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setId(rs.getInt("id"));
                    itemPedido.setCantidad(rs.getInt("cantidad"));
                    itemPedido.setPrecio(rs.getDouble("precio"));
                    int itemMenuId = rs.getInt("item_menu_id");
                    ItemMenu itemMenu = itemMenuJDBC.buscarItemsMenuPorId(itemMenuId).get(0);
                    itemPedido.setItemMenu(itemMenu);
                    items.add(itemPedido);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al buscar items por rango de precio", ex);
        }

        if (items.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items dentro del rango de precio indicado");
        }

        return items;

    }

    @Override
    public List<ItemPedido> buscarItemsPedidoPorId(Integer idItemPedido) throws ItemNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarItemPedidoPorId(Integer idItemPedido) throws ItemNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
