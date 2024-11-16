package isi.deso.tp.service;

import isi.deso.tp.dao.ItemMenuDAO;
import isi.deso.tp.model.ItemMenu;
import java.util.List;

public class ItemMenuController {

    private ItemMenuDAO itemMenuDAO;

    public ItemMenuController() {
    }

    public ItemMenuController(ItemMenuDAO itemMenuDAO) {
        this.itemMenuDAO = itemMenuDAO;
    }

    public List<ItemMenu> listarItemMenu() {
        return itemMenuDAO.listarItemsMenu();
    }

    public void crearItemMenu(ItemMenu itemMenu) {
        itemMenuDAO.crearItemMenu(itemMenu);
    }

    public void actualizarItemMenu(ItemMenu itemMenu) {
        itemMenuDAO.actualizarItemMenu(itemMenu);
    }

    public void eliminarItemMenu(Integer idItemMenu) {
        itemMenuDAO.eliminarItemMenu(idItemMenu);
    }

    public List<ItemMenu> buscarItemsMenuPorId(Integer idItemMenu) {
        return itemMenuDAO.buscarItemsMenuPorId(idItemMenu);
    }

    public ItemMenu buscarItemPorNombre(String nombre) {
        ItemMenu item = null;

        for (ItemMenu v : itemMenuDAO.listarItemsMenu()) {
            if (v.getNombre().equals(nombre)) {
                item = v;
            }
        }

        return item;

    }

}
