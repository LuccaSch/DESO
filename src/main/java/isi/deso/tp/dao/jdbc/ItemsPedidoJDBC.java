package isi.deso.tp.dao.jdbc;

import isi.deso.tp.dao.ItemsPedidoDAO;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsPedidoJDBC implements ItemsPedidoDAO {

    private List<ItemPedido> listaItemPedidos = new ArrayList<>();

    @Override
    public List<ItemPedido> getLista() {
        return listaItemPedidos;
    }

    @Override
    public void setLista(List<ItemPedido> listaItemPedidos) {
        this.listaItemPedidos = listaItemPedidos;
    }

    @Override
    public void agregarItemPedidoALista(ItemPedido itemPedido) {
        listaItemPedidos.add(itemPedido);
    }

    @Override
    public List<ItemPedido> filtrarPorVendedor(Integer idVendedor) throws ItemNoEncontradoException {
        List<ItemPedido> resultado = new ArrayList<>();
        String query = """
            SELECT ip.id, ip.item_menu_id, ip.cantidad, ip.precio
            FROM ItemPedido ip
            JOIN ItemMenu im ON ip.item_menu_id = im.id
            WHERE im.vendedor_id = ?
        """;

        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idVendedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido item = construirItemPedido(rs);
                    resultado.add(item);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al filtrar por vendedor", e);
        }

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items para el vendedor con ID " + idVendedor);
        }

        return resultado;
    }

    @Override
    public List<ItemPedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        if (listaItemPedidos.isEmpty()) {
            throw new ItemNoEncontradoException("La lista de items está vacía.");
        }

        listaItemPedidos.sort(Comparator.comparingDouble(ItemPedido::getPrecio));
        return listaItemPedidos;
    }

    @Override
    public List<ItemPedido> ordenarPorCantidad() throws ItemNoEncontradoException {
        if (listaItemPedidos.isEmpty()) {
            throw new ItemNoEncontradoException("La lista de items está vacía.");
        }

        listaItemPedidos.sort(Comparator.comparingInt(ItemPedido::getCantidad));
        return listaItemPedidos;
    }

    @Override
    public List<ItemPedido> buscarPorRestaurante(Integer idVendedor) throws ItemNoEncontradoException {
        return filtrarPorVendedor(idVendedor);
    }

    @Override
    public List<ItemPedido> buscarPorRangoDePrecio(Double precioMin, Double precioMax) throws ItemNoEncontradoException {
        List<ItemPedido> resultado = new ArrayList<>();

        String query = "SELECT id, item_menu_id, cantidad, precio FROM ItemPedido WHERE precio BETWEEN ? AND ?";

        try (Connection conn = DBConnector.getInstance(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ItemPedido item = construirItemPedido(rs);
                    resultado.add(item);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(ItemsPedidoJDBC.class.getName()).log(Level.SEVERE, "Error al buscar por rango de precio", e);
        }

        if (resultado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items en el rango de precio especificado.");
        }

        return resultado;
    }

    private ItemPedido construirItemPedido(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();
        item.setId(rs.getInt("id"));
        item.setCantidad(rs.getInt("cantidad"));
        item.setPrecio(rs.getDouble("precio"));

        int itemMenuId = rs.getInt("item_menu_id");
        ItemMenu itemMenu = new ItemMenuJDBC().buscarItemsMenuPorId(itemMenuId).getFirst();
        item.setItemMenu(itemMenu);

        return item;
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
