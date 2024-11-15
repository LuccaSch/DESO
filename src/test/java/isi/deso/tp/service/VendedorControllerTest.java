/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package isi.deso.tp.service;

import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Vendedor;
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
public class VendedorControllerTest {
    
    public VendedorControllerTest() {
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
     * Test of crearNuevoVendedor method, of class VendedorController.
     */
    @Test
    public void testCrearNuevoVendedor() {
        System.out.println("crearNuevoVendedor");
        String nombre = "";
        String direccion = "";
        Coordenada coordenada = null;
        VendedorController instance = new VendedorController();
        Vendedor expResult = null;
        Vendedor result = instance.crearNuevoVendedor(nombre, direccion, coordenada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearVendedor method, of class VendedorController.
     */
    @Test
    public void testCrearVendedor_0args() {
        System.out.println("crearVendedor");
        VendedorController instance = new VendedorController();
        Vendedor expResult = null;
        Vendedor result = instance.crearVendedor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearVendedor method, of class VendedorController.
     */
    @Test
    public void testCrearVendedor_Vendedor() {
        System.out.println("crearVendedor");
        Vendedor v = null;
        VendedorController instance = new VendedorController();
        Vendedor expResult = null;
        Vendedor result = instance.crearVendedor(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearVendedor method, of class VendedorController.
     */
    @Test
    public void testCrearVendedor_4args() {
        System.out.println("crearVendedor");
        int id = 0;
        String nombre = "";
        String direccion = "";
        Coordenada coordenada = null;
        VendedorController instance = new VendedorController();
        Vendedor expResult = null;
        Vendedor result = instance.crearVendedor(id, nombre, direccion, coordenada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarVendedor method, of class VendedorController.
     */
    @Test
    public void testListarVendedor() {
        System.out.println("listarVendedor");
        VendedorController instance = new VendedorController();
        List<Vendedor> expResult = null;
        List<Vendedor> result = instance.listarVendedor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterVendedoresPorId method, of class VendedorController.
     */
    @Test
    public void testFilterVendedoresPorId() {
        System.out.println("filterVendedoresPorId");
        List<Vendedor> vendedores = null;
        int filtroId = 0;
        VendedorController instance = new VendedorController();
        List<Vendedor> expResult = null;
        List<Vendedor> result = instance.filterVendedoresPorId(vendedores, filtroId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterVendedoresPorNombre method, of class VendedorController.
     */
    @Test
    public void testFilterVendedoresPorNombre() {
        System.out.println("filterVendedoresPorNombre");
        List<Vendedor> vendedores = null;
        String filtroNombre = "";
        VendedorController instance = new VendedorController();
        List<Vendedor> expResult = null;
        List<Vendedor> result = instance.filterVendedoresPorNombre(vendedores, filtroNombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of VendedorNoEncontrado method, of class VendedorController.
     */
    @Test
    public void testVendedorNoEncontrado() throws Exception {
        System.out.println("VendedorNoEncontrado");
        boolean condicion = false;
        VendedorController instance = new VendedorController();
        instance.VendedorNoEncontrado(condicion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarVendedorPorNombre method, of class VendedorController.
     */
    @Test
    public void testBuscarVendedorPorNombre() throws Exception {
        System.out.println("buscarVendedorPorNombre");
        String nombre = "";
        VendedorController instance = new VendedorController();
        Vendedor expResult = null;
        Vendedor result = instance.buscarVendedorPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarVendedor method, of class VendedorController.
     */
    @Test
    public void testModificarVendedor() {
        System.out.println("modificarVendedor");
        int id = 0;
        String nombre = "";
        String direccion = "";
        Coordenada coordenadas = null;
        VendedorController instance = new VendedorController();
        instance.modificarVendedor(id, nombre, direccion, coordenadas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVendedoresPorId method, of class VendedorController.
     */
    @Test
    public void testDeleteVendedoresPorId() {
        System.out.println("deleteVendedoresPorId");
        List<Vendedor> vendedores = null;
        int filtroId = 0;
        VendedorController instance = new VendedorController();
        instance.deleteVendedoresPorId(vendedores, filtroId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVendedoresPorNombre method, of class VendedorController.
     */
    @Test
    public void testDeleteVendedoresPorNombre() {
        System.out.println("deleteVendedoresPorNombre");
        List<Vendedor> vendedores = null;
        String filtroString = "";
        VendedorController instance = new VendedorController();
        instance.deleteVendedoresPorNombre(vendedores, filtroString);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVendedoresPorPosicion method, of class VendedorController.
     */
    @Test
    public void testDeleteVendedoresPorPosicion() {
        System.out.println("deleteVendedoresPorPosicion");
        List<Vendedor> vendedores = null;
        int posicion = 0;
        VendedorController instance = new VendedorController();
        instance.deleteVendedoresPorPosicion(vendedores, posicion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPedidosPorEstado method, of class VendedorController.
     */
    @Test
    public void testBuscarPedidosPorEstado() {
        System.out.println("buscarPedidosPorEstado");
        int idVendedor = 0;
        EstadoPedidoEnum estadoPedido = null;
        VendedorController instance = new VendedorController();
        List<Pedido> expResult = null;
        List<Pedido> result = instance.buscarPedidosPorEstado(idVendedor, estadoPedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarEstado method, of class VendedorController.
     */
    @Test
    public void testActualizarEstado() {
        System.out.println("actualizarEstado");
        EstadoPedidoEnum estadoNuevo = null;
        List<Pedido> pedidosPorEstado = null;
        VendedorController instance = new VendedorController();
        instance.actualizarEstado(estadoNuevo, pedidosPorEstado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
