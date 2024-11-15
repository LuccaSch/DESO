/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.DTO.ClienteDTO;
import isi.deso.tp.model.PagoStrategy;
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
public class ClienteControllerTest {
    
    public ClienteControllerTest() {
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
     * Test of getClienteDAO method, of class ClienteController.
     */
    @Test
    public void testGetClienteDAO() {
        System.out.println("getClienteDAO");
        ClienteController instance = null;
        ClienteDAO expResult = null;
        ClienteDAO result = instance.getClienteDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClienteDAO method, of class ClienteController.
     */
    @Test
    public void testSetClienteDAO() {
        System.out.println("setClienteDAO");
        ClienteDAO clienteDAO = null;
        ClienteController instance = null;
        instance.setClienteDAO(clienteDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of index method, of class ClienteController.
     */
    @Test
    public void testIndex() {
        System.out.println("index");
        ClienteController instance = null;
        List<Cliente> expResult = null;
        List<Cliente> result = instance.index();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarClientes method, of class ClienteController.
     */
    @Test
    public void testListarClientes() {
        System.out.println("listarClientes");
        ClienteController instance = null;
        List<Cliente> expResult = null;
        List<Cliente> result = instance.listarClientes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearCliente method, of class ClienteController.
     */
    @Test
    public void testCrearCliente_0args() {
        System.out.println("crearCliente");
        ClienteController instance = null;
        Cliente expResult = null;
        Cliente result = instance.crearCliente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearCliente method, of class ClienteController.
     */
    @Test
    public void testCrearCliente_6args() {
        System.out.println("crearCliente");
        Integer id = 0;
        String nombre = "";
        String cuit = "";
        String email = "";
        String direccion = "";
        Coordenada coordenada = null;
        ClienteController instance = null;
        Cliente expResult = null;
        Cliente result = instance.crearCliente(id, nombre, cuit, email, direccion, coordenada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarClienteALista method, of class ClienteController.
     */
    @Test
    public void testAgregarClienteALista() {
        System.out.println("agregarClienteALista");
        Cliente cliente = null;
        ClienteController instance = null;
        instance.agregarClienteALista(cliente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPedido method, of class ClienteController.
     */
    @Test
    public void testAgregarPedido() {
        System.out.println("agregarPedido");
        Cliente cliente = null;
        Pedido pedidoNuevo = null;
        ClienteController instance = null;
        instance.agregarPedido(cliente, pedidoNuevo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorNombreCliente method, of class ClienteController.
     */
    @Test
    public void testBuscarPorNombreCliente() {
        System.out.println("buscarPorNombreCliente");
        String nombreCliente = "";
        ClienteController instance = null;
        Cliente expResult = null;
        Cliente result = instance.buscarPorNombreCliente(nombreCliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirClienteDesdeDTO method, of class ClienteController.
     */
    @Test
    public void testConvertirClienteDesdeDTO() {
        System.out.println("convertirClienteDesdeDTO");
        ClienteDTO clienteDTO = null;
        ClienteController instance = null;
        Cliente expResult = null;
        Cliente result = instance.convertirClienteDesdeDTO(clienteDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterClientePorId method, of class ClienteController.
     */
    @Test
    public void testFilterClientePorId() {
        System.out.println("filterClientePorId");
        List<Cliente> clientes = null;
        Integer filtroId = 0;
        ClienteController instance = null;
        List<Cliente> expResult = null;
        List<Cliente> result = instance.filterClientePorId(clientes, filtroId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterClientePorNombre method, of class ClienteController.
     */
    @Test
    public void testFilterClientePorNombre() {
        System.out.println("filterClientePorNombre");
        List<Cliente> clientes = null;
        String filtroNombre = "";
        ClienteController instance = null;
        List<Cliente> expResult = null;
        List<Cliente> result = instance.filterClientePorNombre(clientes, filtroNombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteClientePorId method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorId() {
        System.out.println("deleteClientePorId");
        List<Cliente> clientes = null;
        Integer filtroId = 0;
        ClienteController instance = null;
        instance.deleteClientePorId(clientes, filtroId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteClientePorNombre method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorNombre() {
        System.out.println("deleteClientePorNombre");
        List<Cliente> clientes = null;
        String filtroNombre = "";
        ClienteController instance = null;
        instance.deleteClientePorNombre(clientes, filtroNombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteClientePorPosicion method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorPosicion() {
        System.out.println("deleteClientePorPosicion");
        List<Cliente> clientes = null;
        Integer posicion = 0;
        ClienteController instance = null;
        instance.deleteClientePorPosicion(clientes, posicion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of elegirMetodoPago method, of class ClienteController.
     */
    @Test
    public void testElegirMetodoPago() {
        System.out.println("elegirMetodoPago");
        Cliente cliente = null;
        ClienteController instance = null;
        PagoStrategy expResult = null;
        PagoStrategy result = instance.elegirMetodoPago(cliente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generarPagoPara method, of class ClienteController.
     */
    @Test
    public void testGenerarPagoPara() {
        System.out.println("generarPagoPara");
        Integer idCliente = 0;
        Integer idPedido = 0;
        ClienteController instance = null;
        instance.generarPagoPara(idCliente, idPedido);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
