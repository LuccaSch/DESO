package isi.deso.tp.dao;

import isi.deso.tp.model.ItemMenu;
import java.util.List;

public interface ItemMenuDAO {

    public List<ItemMenu> listarItemMenu();

    public void crearItemMenu(ItemMenu itemMenu);

    public void actualizarItemMenu(ItemMenu itemMenu);

    public void eliminarItemMenu(int idItemMenu);

    public List<ItemMenu> buscarItemMenu(int idItemMenu);

}
