package isi.deso.tp.service;

import isi.deso.tp.dao.ItemMenuMemoryDAO;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.Plato;
import isi.deso.tp.model.Vendedor;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemMenuControllerTest {

    private ItemMenuController itemMenuController;
    private ItemMenuMemoryDAO itemMenuMemoryDAO;

    public ItemMenuControllerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        itemMenuMemoryDAO = ItemMenuMemoryDAO.getInstance();
        itemMenuController = new ItemMenuController(itemMenuMemoryDAO);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of listarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testListarItemMenu() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);

        itemMenuController.crearItemMenu(i1);
        itemMenuController.crearItemMenu(i2);

        List<ItemMenu> items = itemMenuController.listarItemMenu();

        assertEquals(2, items.size());
        assertTrue(items.contains(i1));
        assertTrue(items.contains(i2));
    }

    /**
     * Test of crearItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testCrearItemMenu() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);

        itemMenuController.crearItemMenu(i1);

        List<ItemMenu> items = itemMenuController.listarItemMenu();

        assertEquals(1, items.size());
        assertEquals(i1, items.get(0));
    }

    /**
     * Test of actualizarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testActualizarItemMenu() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);

        itemMenuController.crearItemMenu(i1);

        ItemMenu updatedItem = new Plato(300, true, false, 3, "Flan vip", "Flan casero con crema", 1000.0, null, 0.5, v1);
        itemMenuController.actualizarItemMenu(updatedItem);

        List<ItemMenu> items = itemMenuController.listarItemMenu();

        assertEquals(1, items.size());
        assertEquals(updatedItem, items.get(0));
    }

    /**
     * Test of eliminarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testEliminarItemMenu() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);

        itemMenuController.crearItemMenu(i1);

        itemMenuController.eliminarItemMenu(i1.getId());

        List<ItemMenu> items = itemMenuController.listarItemMenu();

        assertTrue(items.isEmpty());
    }

    /**
     * Test of buscarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testBuscarItemMenuPorId() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 1, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);

        itemMenuController.crearItemMenu(i1);

        List<ItemMenu> foundItems = itemMenuController.buscarItemsMenuPorId(1);

        assertEquals(1, foundItems.size());
        assertEquals(i1, foundItems.get(0));
    }

}
