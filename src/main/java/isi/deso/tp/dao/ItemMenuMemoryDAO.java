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
    public List<ItemMenu> listarItemsMenu() {
        return this.listaItemsMenu;
    }

    @Override
    public void crearItemMenu(ItemMenu itemMenu) {
        this.listaItemsMenu.add(itemMenu);
    }

    @Override
    public void actualizarItemMenu(ItemMenu itemMenu) {
        eliminarItemMenu(itemMenu.getId());
        this.listaItemsMenu.add(itemMenu);
    }

    @Override
    public List<ItemMenu> buscarItemsMenuPorId(Integer idItemMenu) {
        return this.listaItemsMenu.stream().filter(itemMenu -> itemMenu.getId() == idItemMenu.intValue()).toList();
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

    @Override
    public void eliminarItemMenu(Integer idItemMenu) {
        ItemMenu itemMenuEncontrado = buscarItemsMenuPorId(idItemMenu).getFirst();
        this.listaItemsMenu.remove(itemMenuEncontrado);
    }

}
