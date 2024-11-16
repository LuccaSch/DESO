package isi.deso.tp.service;

import isi.deso.tp.dao.ClienteMemoryDAO;
import isi.deso.tp.model.Cliente;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteControllerTest {

    private ClienteMemoryDAO clienteMemoryDAO;
    private ClienteController clienteController;

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
        clienteMemoryDAO = ClienteMemoryDAO.getInstance();
        clienteController = new ClienteController();

        clienteController.setClienteDAO(clienteMemoryDAO);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testListarClientes() {
        // Configurar datos iniciales
        Cliente cliente1 = new Cliente(1, "Juan Perez", "20-12345678-9", "juan@mail.com", "Calle Falsa 123", null);
        Cliente cliente2 = new Cliente(2, "Maria Gomez", "20-98765432-1", "maria@mail.com", "Avenida Siempreviva 456", null);

        clienteController.getClienteDAO().agregarClienteALista(cliente1);
        clienteController.getClienteDAO().agregarClienteALista(cliente2);

        // Ejecutar el método
        List<Cliente> clientes = clienteController.listarClientes();

        // Verificar los resultados
        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Juan Perez", clientes.get(0).getNombre());
        assertEquals("Maria Gomez", clientes.get(1).getNombre());
    }

    @Test
    public void testCrearCliente() {

        Cliente nuevoCliente = clienteController.crearCliente(3, "Carlos Lopez", "20-12312312-3", "carlos@mail.com", "Diagonal 789", null);

        List<Cliente> clientes = clienteMemoryDAO.listarClientes();

        assertNotNull(nuevoCliente);
        assertEquals(3, nuevoCliente.getId());
        assertEquals("Carlos Lopez", nuevoCliente.getNombre());
        assertEquals(1, clientes.size());
    }

    @Test
    public void testBuscarPorNombreCliente() {
        // Configurar datos iniciales
        Cliente cliente = new Cliente(1, "Laura Martinez", "20-34567890-1", "laura@mail.com", "Calle Principal 321", null);
        clienteMemoryDAO.agregarClienteALista(cliente);

        // Ejecutar el método
        Cliente encontrado = clienteController.buscarPorNombreCliente("Laura Martinez");

        // Verificar los resultados
        assertNotNull(encontrado);
        assertEquals(cliente.getId(), encontrado.getId());
        assertEquals(cliente.getNombre(), encontrado.getNombre());
    }

    @Test
    public void testBuscarPorIdCliente() {

        Cliente cliente = new Cliente(2, "Ricardo Gomez", "20-45678901-2", "ricardo@mail.com", "Avenida Central 654", null);
        clienteMemoryDAO.agregarClienteALista(cliente);

        Cliente encontrado = clienteController.buscarPorIdCliente(2);

        assertNotNull(encontrado);
        assertEquals(cliente.getNombre(), encontrado.getNombre());
    }

    @Test
    public void testEliminarClientePorId() {

        Cliente cliente1 = new Cliente(1, "Cliente A", "20-11111111-1", "a@mail.com", "Direccion A", null);
        Cliente cliente2 = new Cliente(4, "Cliente B", "20-22222222-2", "b@mail.com", "Direccion B", null);
        clienteMemoryDAO.agregarClienteALista(cliente1);
        clienteMemoryDAO.agregarClienteALista(cliente2);

        List<Cliente> clientes = clienteMemoryDAO.listarClientes();
        clienteController.deleteClientePorId(clientes, 1);

        assertEquals(1, clientes.size());
        assertEquals("Cliente B", clientes.get(0).getNombre());
    }

    @Test
    public void testEliminarClientePorNombre() {

        Cliente cliente1 = new Cliente(1, "Cliente A", "20-11111111-1", "a@mail.com", "Direccion A", null);
        Cliente cliente2 = new Cliente(4, "Cliente B", "20-22222222-2", "b@mail.com", "Direccion B", null);
        clienteMemoryDAO.agregarClienteALista(cliente1);
        clienteMemoryDAO.agregarClienteALista(cliente2);

        List<Cliente> clientes = clienteMemoryDAO.listarClientes();
        clienteController.deleteClientePorNombre(clientes, "Cliente A");

        assertEquals(1, clientes.size());
        assertEquals("Cliente B", clientes.get(0).getNombre());
    }
}
