package isi.deso.tp.dao;

import isi.deso.tp.model.ItemMenu;
import java.util.ArrayList;
import java.util.List;

public class ItemMenuMemoryDAO implements ItemMenuDAO {

    private List<ItemMenu> listaItemsMenu;
    private static ItemMenuMemoryDAO itemMenuMemoryDAO;

    private ItemMenuMemoryDAO() {
    }

    public static ItemMenuMemoryDAO getInstance() {
        if (ItemMenuMemoryDAO.itemMenuMemoryDAO == null) {
            ItemMenuMemoryDAO.itemMenuMemoryDAO = new ItemMenuMemoryDAO();
            ItemMenuMemoryDAO.itemMenuMemoryDAO.listaItemsMenu = new ArrayList<>();

        }

        return ItemMenuMemoryDAO.itemMenuMemoryDAO;
    }

    @Override
    public List<ItemMenu> listarItemMenu() {
        return this.listaItemsMenu;
    }

    @Override
    public void crearItemMenu(ItemMenu itemMenu) {
        this.listaItemsMenu.add(itemMenu);
    }

    @Override
    public void actualizarItemMenu(ItemMenu itemMenu) {
        this.listaItemsMenu.remove(itemMenu.getId());
        this.listaItemsMenu.add(itemMenu);
    }

    @Override
    public void eliminarItemMenu(int idItemMenu) {
        this.listaItemsMenu.remove(idItemMenu);
    }

    @Override
    public List<ItemMenu> buscarItemMenu(int idItemMenu) {
        return this.listaItemsMenu.stream().filter(itemMenu -> itemMenu.getId() == idItemMenu).toList();
    }

    public ItemMenu buscarItemPorNombre(String nombre) {
        ItemMenu item = null;

        for (ItemMenu v : this.listaItemsMenu) {
            if (v.getNombre().equals(nombre)) {
                item = v;
            }
        }

        return item;
    }

}
