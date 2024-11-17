package isi.deso.tp.service;

import isi.deso.tp.dao.ItemsPedidoMemoryDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Plato;
import isi.deso.tp.model.Vendedor;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemPedidoControllerTest {

    private ItemPedidoController itemPedidoController;
    private ItemsPedidoMemoryDAO itemPedidoMemory;

    public ItemPedidoControllerTest() {

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        itemPedidoMemory = new ItemsPedidoMemoryDAO();
        itemPedidoController = new ItemPedidoController();
        itemPedidoController.setItemsPedidoDAO(itemPedidoMemory);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCrearItemPedidoSinPrecio() {
        ItemMenu itemMenu = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, null);
        ItemPedido itemPedido = itemPedidoController.crearItemPedido(1, 1, itemMenu, 2);

        assertNotNull(itemPedido);
        assertEquals(1, itemPedido.getId());
        assertEquals(itemMenu, itemPedido.getItemMenu());
        assertEquals(2, itemPedido.getCantidad());
    }

    @Test
    public void testCrearItemPedidoConPrecio() {
        ItemMenu itemMenu = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, null);
        ItemPedido itemPedido = itemPedidoController.crearItemPedido(1, 2, itemMenu, 3, 45.0);

        assertNotNull(itemPedido);
        assertEquals(2, itemPedido.getId());
        assertEquals(itemMenu, itemPedido.getItemMenu());
        assertEquals(3, itemPedido.getCantidad());
        assertEquals(45.0, itemPedido.getPrecio());
    }

    /**
     * Test of filtrarPorVendedor method, of class ItemPedidoController.
     */
    @Test
    public void testFiltrarPorVendedor() {

        List<ItemPedido> lista = new ArrayList<>();
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);
        itemPedidoController.crearItemPedido(1, 1, i1, 5);
        itemPedidoController.crearItemPedido(1, 2, i2, 3);

        List<ItemPedido> filtrados = itemPedidoController.filtrarPorVendedor(1);
        assertNotNull(filtrados);

    }

    /**
     * Test of ordenarPorPrecio method, of class ItemPedidoController.
     */
    @Test
    public void testOrdenarPorPrecio() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);
        List<ItemPedido> lista = new ArrayList<>();
        itemPedidoController.crearItemPedido(1, 1, i1, 5);
        itemPedidoController.crearItemPedido(1, 2, i2, 3);

        List<ItemPedido> ordenados = itemPedidoController.ordenarPorPrecio();
        assertNotNull(ordenados);
        assertTrue(ordenados.get(0).getItemMenu().getPrecio() <= ordenados.get(1).getItemMenu().getPrecio());
    }

    /**
     * Test of ordenarPorCantidad method, of class ItemPedidoController.
     */
    @Test
    public void testOrdenarPorCantidad() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);
        List<ItemPedido> lista = new ArrayList<>();
        itemPedidoController.crearItemPedido(1, 1, i1, 5);
        itemPedidoController.crearItemPedido(1, 2, i2, 3);

        List<ItemPedido> ordenados = itemPedidoController.ordenarPorCantidad();
        assertNotNull(ordenados);
        assertTrue(ordenados.get(0).getCantidad() <= ordenados.get(1).getCantidad());
    }

    /**
     * Test of deletePedidoPorId method, of class ItemPedidoController.
     */
    @Test
    public void testDeletePedidoPorId() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);
        Cliente cliente = new Cliente(1, "Lucca Perez", "2043350012", "lsch@gmail.com", "San Luis 6612", new Coordenada(1.0, 1.0));
        Cliente cliente2 = new Cliente(2, "Juan", "204335002", "lsh@gmail.com", "San Luis 612", new Coordenada(1.0, 1.0));
        ItemPedido itemPedido1 = itemPedidoController.crearItemPedido(1, 3, i1, 1);
        ItemPedido itemPedido2 = itemPedidoController.crearItemPedido(1, 4, i2, 4);

        List<ItemPedido> listaItemPedido1 = new ArrayList<>();

        listaItemPedido1.add(itemPedido1);
        listaItemPedido1.add(itemPedido2);

        Pedido pedido1 = new Pedido(1, cliente, listaItemPedido1);
        Pedido pedido2 = new Pedido(2, cliente2, listaItemPedido1);
        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(pedido1);
        pedidos.add(pedido2);

        itemPedidoController.deletePedidoPorId(1);

        assertEquals(1, pedidos.size());
        assertFalse(pedidos.contains(pedido1));
    }

}
