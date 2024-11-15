package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteDAO;
import isi.deso.tp.dao.ClienteMemoryDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.DTO.ClienteDTO;
import isi.deso.tp.model.DTO.PedidoDTO;
import isi.deso.tp.model.MercadoPagoStrategy;
import isi.deso.tp.model.PagoStrategy;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class ClienteControllerTest {

    private ClienteMemoryDAO clienteMemoryDAO;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Pedido pedido1;
    private ClienteDTO clienteDTO;
    private List<PedidoDTO> listaPedidoDTO;
    private List<Cliente> clientes;
    private PagoStrategy estrategiaDePago;

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
        //crear las instancias que necesito antes de las pruebas
        clienteMemoryDAO = mock(ClienteMemoryDAO.getInstance());
        cliente1 = new Cliente(1, "Juan", "20-44427691-7", "juan@email.com", "Lavaisse 2323", null);
        cliente2 = new Cliente(2, "Marcos", "20-45527691-7", "marcos@email.com", "Lavaisse 2323", null);
        cliente3 = new Cliente(3, "Emilio", "20-46627691-7", "Emilio@email.com", "Lavaisse 2323", null);
        pedido1 = new Pedido();
        listaPedidoDTO = new ArrayList<PedidoDTO>();
        clienteDTO = new ClienteDTO("Juan", "20-47727691-7", "juan@email.com", "Lavaisse 2323", listaPedidoDTO);
        clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        estrategiaDePago = new MercadoPagoStrategy(cliente1.getNombre().toLowerCase().replaceAll("\\s+", "") + ".mp");
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
        ClienteMemoryDAO clienteMemoryDAO = mock(ClienteMemoryDAO.class);
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        ClienteDAO expResult = clienteMemoryDAO.getInstance();
        ClienteDAO result = instance.getClienteDAO();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClienteDAO method, of class ClienteController.
     */
    @Test
    public void testSetClienteDAO() {
        System.out.println("setClienteDAO");
        ClienteDAO clienteDAO = mock(ClienteMemoryDAO.class);
        ClienteController instance = new ClienteController();
        instance.setClienteDAO(clienteDAO);
        assertFalse((instance.getClienteDAO() == null));
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
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        List<Cliente> expResult = new ArrayList<Cliente>();
        Cliente cliente1 = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        Cliente cliente3 = mock(Cliente.class);
        expResult.add(cliente1);
        expResult.add(cliente2);
        expResult.add(cliente3);
        instance.agregarClienteALista(cliente1);
        instance.agregarClienteALista(cliente2);
        instance.agregarClienteALista(cliente3);
        List<Cliente> result = instance.listarClientes();
        assertEquals(expResult, result);
    }

    /**
     * Test of crearCliente method, of class ClienteController.
     */
    @Test
    public void testCrearCliente_0args() {
        System.out.println("crearCliente");
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        Cliente expResult = new Cliente();
        Cliente result = instance.crearCliente();
        assertEquals(expResult, result);
    }

    /**
     * Test of crearCliente method, of class ClienteController.
     */
    @Test
    public void testCrearCliente_6args() {
        System.out.println("crearCliente");
        int id = 1;
        String nombre = "Juan";
        String cuit = "20-44427691-7";
        String email = "juan@email.com";
        String direccion = "Lavaisse2131";
        Coordenada coordenada = null;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        Cliente expResult = new Cliente(1, "Juan", "20-44427691-7", "juan@email.com", "Lavaisse2131", null);
        Cliente result = instance.crearCliente(id, nombre, cuit, email, direccion, coordenada);
        assertEquals(expResult, result);
    }

    /**
     * Test of agregarClienteALista method, of class ClienteController.
     */
    @Test
    public void testAgregarClienteALista() {
        System.out.println("agregarClienteALista");
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.agregarClienteALista(cliente1);
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        assertEquals(expResult, instance.listarClientes());
    }

    /**
     * Test of agregarPedido method, of class ClienteController.
     */
    @Test
    public void testAgregarPedido() {
        System.out.println("agregarPedido");
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.agregarPedido(cliente1, pedido1);
        assertEquals(pedido1, instance.buscarPorNombreCliente("Juan").getListaPedidos());
        //ya probaria tambien buscarPorNombre cliente
    }

    /**
     * Test of buscarPorNombreCliente method, of class ClienteController.
     */
    @Test
    public void testBuscarPorNombreCliente() {
        System.out.println("buscarPorNombreCliente");
        String nombreCliente = "Juan";
        Cliente expResult = cliente1;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        Cliente result = instance.buscarPorNombreCliente(nombreCliente);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertirClienteDesdeDTO method, of class ClienteController.
     */
    @Test
    public void testConvertirClienteDesdeDTO() {
        System.out.println("convertirClienteDesdeDTO");
        List<PedidoDTO> listaPedidoDTO = new ArrayList<>();
        Coordenada coordenada = null;
        ClienteMemoryDAO clienteMemoryDAO = mock(ClienteMemoryDAO.getInstance());
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        Cliente expResult = new Cliente(clienteDTO.getNombre(), clienteDTO.getCuit(), clienteDTO.getEmail(), clienteDTO.getDireccion());
        Cliente result = instance.convertirClienteDesdeDTO(clienteDTO);
        assertEquals(expResult, result);
        //Ver si es igual aunque tenga menos datos en las variables de instancia (DEBERIA SER IGUAL)
    }

    /**
     * Test of filterClientePorId method, of class ClienteController.
     */
    @Test
    //Esto no se si daria bien porque en las listas compara por la referencia y no por el contenido
    public void testFilterClientePorId() {
        System.out.println("filterClientePorId");
        int filtroId = 1;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        List<Cliente> result = instance.filterClientePorId(clientes, filtroId);
        assertEquals(expResult, result);
    }

    /**
     * Test of filterClientePorNombre method, of class ClienteController.
     */
    @Test
    public void testFilterClientePorNombre() {
        System.out.println("filterClientePorNombre");
        String filtroNombre = "Juan";
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        List<Cliente> result = instance.filterClientePorNombre(clientes, filtroNombre);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteClientePorId method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorId() {
        System.out.println("deleteClientePorId");
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        expResult.add(cliente2);
        int filtroId = 3;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.deleteClientePorId(clientes, filtroId);
        assertEquals(expResult, instance.listarClientes());
    }

    /**
     * Test of deleteClientePorNombre method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorNombre() {
        System.out.println("deleteClientePorNombre");
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        expResult.add(cliente2);
        String filtroNombre = "Emilio";
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.deleteClientePorNombre(clientes, filtroNombre);
        assertEquals(expResult, instance.listarClientes());
    }

    /**
     * Test of deleteClientePorPosicion method, of class ClienteController.
     */
    @Test
    public void testDeleteClientePorPosicion() {
        System.out.println("deleteClientePorPosicion");
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente1);
        expResult.add(cliente2);
        int filtroPosicion = 2;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.deleteClientePorPosicion(clientes, filtroPosicion);
        assertEquals(expResult, instance.listarClientes());;
    }

    /**
     * Test of generarPagoPara method, of class ClienteController.
     */
    @Test
    public void testGenerarPagoPara() {
        System.out.println("generarPagoPara");
        int idCliente = 1;
        int idPedido = 1;
        ClienteController instance = new ClienteController(clienteMemoryDAO.getInstance());
        instance.generarPagoPara(idCliente, idPedido);
        assertEquals(estrategiaDePago, instance.mostrarEstrategiaDePagoDelPedido(idCliente, idPedido));
    }

}
