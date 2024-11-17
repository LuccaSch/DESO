package isi.deso.tp.dao;

import isi.deso.tp.model.ItemMenu;
import java.util.List;

public interface ItemMenuDAO {

    public List<ItemMenu> listarItemsMenu();

    public void crearItemMenu(ItemMenu itemMenu);

    public void actualizarItemMenu(ItemMenu itemMenu);

    public List<ItemMenu> buscarItemsMenuPorId(Integer idItemMenu);

    public void eliminarItemMenu(Integer idItemMenu);

}
