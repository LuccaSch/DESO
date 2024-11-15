/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package isi.deso.tp.service;

import isi.deso.tp.dao.PedidoDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.DTO.PedidoDTO;
import isi.deso.tp.model.EstadoPedidoEnum;
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
public class PedidoControllerTest {
    
    public PedidoControllerTest() {
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
     * Test of getPedidoDAO method, of class PedidoController.
     */
    @Test
    public void testGetPedidoDAO() {
        System.out.println("getPedidoDAO");
        PedidoController instance = null;
        PedidoDAO expResult = null;
        PedidoDAO result = instance.getPedidoDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPedidoDAO method, of class PedidoController.
     */
    @Test
    public void testSetPedidoDAO() {
        System.out.println("setPedidoDAO");
        PedidoDAO pedidoDAO = null;
        PedidoController instance = null;
        instance.setPedidoDAO(pedidoDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearPedido method, of class PedidoController.
     */
    @Test
    public void testCrearPedido_3args() {
        System.out.println("crearPedido");
        int id = 0;
        Cliente cliente = null;
        List<ItemPedido> pedidoDetalle = null;
        PedidoController instance = null;
        Pedido expResult = null;
        Pedido result = instance.crearPedido(id, cliente, pedidoDetalle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearPedido method, of class PedidoController.
     */
    @Test
    public void testCrearPedido_5args() {
        System.out.println("crearPedido");
        int id = 0;
        Cliente cliente = null;
        EstadoPedidoEnum estadoPedido = null;
        List<ItemPedido> pedidoDetalle = null;
        double precioTotal = 0.0;
        PedidoController instance = null;
        Pedido expResult = null;
        Pedido result = instance.crearPedido(id, cliente, estadoPedido, pedidoDetalle, precioTotal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vendedorEsUnico method, of class PedidoController.
     */
    @Test
    public void testVendedorEsUnico() throws Exception {
        System.out.println("vendedorEsUnico");
        List<ItemPedido> pedidoDetalle = null;
        PedidoController instance = null;
        instance.vendedorEsUnico(pedidoDetalle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarItemPedido method, of class PedidoController.
     */
    @Test
    public void testAgregarItemPedido() {
        System.out.println("agregarItemPedido");
        Pedido pedido = null;
        ItemPedido itemPedido = null;
        PedidoController instance = null;
        instance.agregarItemPedido(pedido, itemPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcularParcial method, of class PedidoController.
     */
    @Test
    public void testCalcularParcial() {
        System.out.println("calcularParcial");
        Pedido pedido = null;
        PedidoController instance = null;
        double expResult = 0.0;
        double result = instance.calcularParcial(pedido);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirPedidoDesdeDTO method, of class PedidoController.
     */
    @Test
    public void testConvertirPedidoDesdeDTO() {
        System.out.println("convertirPedidoDesdeDTO");
        PedidoDTO pedidoDTO = null;
        PedidoController instance = null;
        Pedido expResult = null;
        Pedido result = instance.convertirPedidoDesdeDTO(pedidoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarEstadoPedido method, of class PedidoController.
     */
    @Test
    public void testActualizarEstadoPedido() {
        System.out.println("actualizarEstadoPedido");
        Pedido pedido = null;
        EstadoPedidoEnum estadoNuevo = null;
        PedidoController instance = null;
        instance.actualizarEstadoPedido(pedido, estadoNuevo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aplicarRecargo method, of class PedidoController.
     */
    @Test
    public void testAplicarRecargo() {
        System.out.println("aplicarRecargo");
        Pedido pedido = null;
        PedidoController instance = null;
        double expResult = 0.0;
        double result = instance.aplicarRecargo(pedido);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aplicarRecargoPorDTO method, of class PedidoController.
     */
    @Test
    public void testAplicarRecargoPorDTO() {
        System.out.println("aplicarRecargoPorDTO");
        PedidoDTO pedidoDTO = null;
        PedidoController instance = null;
        double expResult = 0.0;
        double result = instance.aplicarRecargoPorDTO(pedidoDTO);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of metodoPagoNoEncontrado method, of class PedidoController.
     */
    @Test
    public void testMetodoPagoNoEncontrado() throws Exception {
        System.out.println("metodoPagoNoEncontrado");
        PedidoController instance = null;
        instance.metodoPagoNoEncontrado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarPorEstado method, of class PedidoController.
     */
    @Test
    public void testFiltrarPorEstado() {
        System.out.println("filtrarPorEstado");
        List<Pedido> listaPedidos = null;
        EstadoPedidoEnum estadoPedido = null;
        PedidoController instance = null;
        List<Pedido> expResult = null;
        List<Pedido> result = instance.filtrarPorEstado(listaPedidos, estadoPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorRestaurante method, of class PedidoController.
     */
    @Test
    public void testBuscarPorRestaurante() {
        System.out.println("buscarPorRestaurante");
        int idVendedor = 0;
        PedidoController instance = null;
        List<Pedido> expResult = null;
        List<Pedido> result = instance.buscarPorRestaurante(idVendedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorIdPedido method, of class PedidoController.
     */
    @Test
    public void testBuscarPorIdPedido() {
        System.out.println("buscarPorIdPedido");
        int idPedido = 0;
        PedidoController instance = null;
        List<Pedido> expResult = null;
        List<Pedido> result = instance.buscarPorIdPedido(idPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarEstado method, of class PedidoController.
     */
    @Test
    public void testActualizarEstado() {
        System.out.println("actualizarEstado");
        EstadoPedidoEnum estadoPedidoNuevo = null;
        Pedido pedido = null;
        PedidoController instance = null;
        instance.actualizarEstado(estadoPedidoNuevo, pedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generarPagoPara method, of class PedidoController.
     */
    @Test
    public void testGenerarPagoPara() {
        System.out.println("generarPagoPara");
        Pedido pedido = null;
        PedidoController instance = null;
        instance.generarPagoPara(pedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPedidoALista method, of class PedidoController.
     */
    @Test
    public void testAgregarPedidoALista() {
        System.out.println("agregarPedidoALista");
        Pedido pedido = null;
        PedidoController instance = null;
        instance.agregarPedidoALista(pedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaPedidos method, of class PedidoController.
     */
    @Test
    public void testGetListaPedidos() {
        System.out.println("getListaPedidos");
        PedidoController instance = null;
        List<Pedido> expResult = null;
        List<Pedido> result = instance.getListaPedidos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorNombreCliente method, of class PedidoController.
     */
    @Test
    public void testBuscarPorNombreCliente() {
        System.out.println("buscarPorNombreCliente");
        String cliente = "";
        PedidoController instance = null;
        Pedido expResult = null;
        Pedido result = instance.buscarPorNombreCliente(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
