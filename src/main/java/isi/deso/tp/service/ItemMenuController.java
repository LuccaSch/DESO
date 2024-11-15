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
        return itemMenuDAO.listarItemMenu();
    }

    
    public void crearItemMenu(ItemMenu itemMenu) {
        itemMenuDAO.listarItemMenu().add(itemMenu);
    }

   
    public void actualizarItemMenu(ItemMenu itemMenu) {
        itemMenuDAO.listarItemMenu().remove(itemMenu.getId());
        itemMenuDAO.listarItemMenu().add(itemMenu);
    }

    
    public void eliminarItemMenu(int idItemMenu) {
        itemMenuDAO.listarItemMenu().remove(idItemMenu);
    }

    
    public List<ItemMenu> buscarItemMenu(int idItemMenu) {
        return itemMenuDAO.listarItemMenu().stream().filter(itemMenu -> itemMenu.getId() == idItemMenu).toList();
    }

    public ItemMenu buscarItemPorNombre(String nombre) {
        ItemMenu item = null;

        for (ItemMenu v : itemMenuDAO.listarItemMenu()) {
            if (v.getNombre().equals(nombre)) {
                item = v;
            }
        }

        return item;
    }
    
}
