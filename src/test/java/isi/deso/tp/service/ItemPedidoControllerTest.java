/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package isi.deso.tp.service;

import isi.deso.tp.dao.ItemsPedidoDAO;
import isi.deso.tp.dao.ItemsPedidoFactoryDAO;
import isi.deso.tp.model.DTO.ItemPedidoDTO;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nacho
 */
public class ItemPedidoControllerTest {
    
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
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getItemsPedidoMemoryFactoryDAO method, of class ItemPedidoController.
     */
    @Test
    public void testGetItemsPedidoMemoryFactoryDAO() {
        System.out.println("getItemsPedidoMemoryFactoryDAO");
        ItemPedidoController instance = new ItemPedidoController();
        ItemsPedidoFactoryDAO expResult = null;
        ItemsPedidoFactoryDAO result = instance.getItemsPedidoMemoryFactoryDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setItemsPedidoMemoryFactoryDAO method, of class ItemPedidoController.
     */
    @Test
    public void testSetItemsPedidoMemoryFactoryDAO() {
        System.out.println("setItemsPedidoMemoryFactoryDAO");
        ItemsPedidoFactoryDAO itemsPedidoMemoryFactoryDAO = null;
        ItemPedidoController instance = new ItemPedidoController();
        instance.setItemsPedidoMemoryFactoryDAO(itemsPedidoMemoryFactoryDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsPedidoDAO method, of class ItemPedidoController.
     */
    @Test
    public void testGetItemsPedidoDAO() {
        System.out.println("getItemsPedidoDAO");
        ItemPedidoController instance = new ItemPedidoController();
        ItemsPedidoDAO expResult = null;
        ItemsPedidoDAO result = instance.getItemsPedidoDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setItemsPedidoDAO method, of class ItemPedidoController.
     */
    @Test
    public void testSetItemsPedidoDAO() {
        System.out.println("setItemsPedidoDAO");
        ItemsPedidoDAO itemsPedidoDAO = null;
        ItemPedidoController instance = new ItemPedidoController();
        instance.setItemsPedidoDAO(itemsPedidoDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearItemPedido method, of class ItemPedidoController.
     */
    @Test
    public void testCrearItemPedido_3args() {
        System.out.println("crearItemPedido");
        int id = 0;
        ItemMenu itemMenu = null;
        int cantidad = 0;
        ItemPedidoController instance = new ItemPedidoController();
        ItemPedido expResult = null;
        ItemPedido result = instance.crearItemPedido(id, itemMenu, cantidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearItemPedido method, of class ItemPedidoController.
     */
    @Test
    public void testCrearItemPedido_4args() {
        System.out.println("crearItemPedido");
        int id = 0;
        ItemMenu itemMenu = null;
        int cantidad = 0;
        double precio = 0.0;
        ItemPedidoController instance = new ItemPedidoController();
        ItemPedido expResult = null;
        ItemPedido result = instance.crearItemPedido(id, itemMenu, cantidad, precio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLista method, of class ItemPedidoController.
     */
    @Test
    public void testSetLista() {
        System.out.println("setLista");
        List<ItemPedido> listaItemPedidos = null;
        ItemPedidoController instance = new ItemPedidoController();
        instance.setLista(listaItemPedidos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLista method, of class ItemPedidoController.
     */
    @Test
    public void testGetLista() {
        System.out.println("getLista");
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.getLista();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarPorVendedor method, of class ItemPedidoController.
     */
    @Test
    public void testFiltrarPorVendedor() {
        System.out.println("filtrarPorVendedor");
        int idVendedor = 0;
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.filtrarPorVendedor(idVendedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordenarPorPrecio method, of class ItemPedidoController.
     */
    @Test
    public void testOrdenarPorPrecio() {
        System.out.println("ordenarPorPrecio");
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.ordenarPorPrecio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordenarPorCantidad method, of class ItemPedidoController.
     */
    @Test
    public void testOrdenarPorCantidad() {
        System.out.println("ordenarPorCantidad");
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.ordenarPorCantidad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorRestaurante method, of class ItemPedidoController.
     */
    @Test
    public void testBuscarPorRestaurante() {
        System.out.println("buscarPorRestaurante");
        int idRestaurante = 0;
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscarPorRestaurante(idRestaurante);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorRangoDePrecio method, of class ItemPedidoController.
     */
    @Test
    public void testBuscarPorRangoDePrecio() {
        System.out.println("buscarPorRangoDePrecio");
        double precioMin = 0.0;
        double precioMax = 0.0;
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscarPorRangoDePrecio(precioMin, precioMax);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePedidoPorId method, of class ItemPedidoController.
     */
    @Test
    public void testDeletePedidoPorId() {
        System.out.println("deletePedidoPorId");
        List<Pedido> pedidos = null;
        int idPedido = 0;
        ItemPedidoController instance = new ItemPedidoController();
        instance.deletePedidoPorId(pedidos, idPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirDesdeDTO method, of class ItemPedidoController.
     */
    @Test
    public void testConvertirDesdeDTO() {
        System.out.println("convertirDesdeDTO");
        ItemPedidoDTO itemPedidoDTO = null;
        ItemPedidoController instance = new ItemPedidoController();
        ItemPedido expResult = null;
        ItemPedido result = instance.convertirDesdeDTO(itemPedidoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirADTO method, of class ItemPedidoController.
     */
    @Test
    public void testConvertirADTO() {
        System.out.println("convertirADTO");
        ItemPedido itemPedido = null;
        ItemPedidoController instance = new ItemPedidoController();
        ItemPedidoDTO expResult = null;
        ItemPedidoDTO result = instance.convertirADTO(itemPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirAListaDTO method, of class ItemPedidoController.
     */
    @Test
    public void testConvertirAListaDTO() {
        System.out.println("convertirAListaDTO");
        List<ItemPedido> itemsPedido = null;
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedidoDTO> expResult = null;
        List<ItemPedidoDTO> result = instance.convertirAListaDTO(itemsPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirDesdeListaDTO method, of class ItemPedidoController.
     */
    @Test
    public void testConvertirDesdeListaDTO() {
        System.out.println("convertirDesdeListaDTO");
        List<ItemPedidoDTO> itemsPedidoDTO = null;
        ItemPedidoController instance = new ItemPedidoController();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.convertirDesdeListaDTO(itemsPedidoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
