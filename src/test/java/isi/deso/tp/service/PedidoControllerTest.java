package isi.deso.tp.service;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.EstadoPedidoEnum;
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

public class PedidoControllerTest {

    PedidoMemoryDAO pedidoMemoryDAO;
    PedidoController pedidoController;

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
        pedidoMemoryDAO = PedidoMemoryDAO.getInstance();
        pedidoController = new PedidoController(pedidoMemoryDAO);

    }

    @AfterEach
    public void tearDown() {
        pedidoMemoryDAO.listarPedidos().clear();
    }

    /**
     * Test of crearPedido method, of class PedidoController.
     */
    @Test
    public void testCrearPedido_3args() {

        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v1);

        Cliente cliente = new Cliente(1, "Cliente Prueba", "20304050", "cliente@correo.com", "Dirección", null);
        List<ItemPedido> items = new ArrayList<>();

        items.add(new ItemPedido(1, i1, 10));
        items.add(new ItemPedido(2, i2, 20));

        Pedido pedido = pedidoController.crearPedido(1, cliente, items);

        assertNotNull(pedido);
        assertEquals(1, pedido.getId());
        assertEquals(cliente, pedido.getCliente());
        assertEquals(items.size(), pedido.getPedidoDetalle().size());
    }

    /**
     * Test of vendedorEsUnico method, of class PedidoController.
     */
    @Test
    public void testVendedorNoUnicoException() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        Vendedor v2 = new Vendedor(2, "Pepe", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        ItemMenu i2 = new Plato(600, true, false, 3, "Helado", "Flan casero con crema", 1000.0, null, 0.5, v2);

        Cliente cliente = new Cliente(1, "Cliente Prueba", "20304050", "cliente@correo.com", "Dirección", null);
        List<ItemPedido> items = new ArrayList<>();

        items.add(new ItemPedido(1, i1, 10));
        items.add(new ItemPedido(2, i2, 20));

        Pedido pedido = pedidoController.crearPedido(1, cliente, items);

        assertNull(pedido);
    }

    /**
     * Test of agregarItemPedido method, of class PedidoController.
     */
    @Test
    public void testAgregarItemPedido() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);

        Cliente cliente = new Cliente(3, "Cliente Ejemplo", "20304050", "ejemplo@correo.com", "Dirección", null);
        List<ItemPedido> items = new ArrayList<>();
        items.add(new ItemPedido(1, i1, 10));

        Pedido pedido = pedidoController.crearPedido(3, cliente, items);

        ItemPedido nuevoItem = new ItemPedido(3, i1, 30);
        pedidoController.agregarItemPedido(pedido, nuevoItem);

        assertEquals(2, pedido.getPedidoDetalle().size());
        assertTrue(pedido.getPedidoDetalle().contains(nuevoItem));
    }

    /**
     * Test of calcularParcial method, of class PedidoController.
     */
    @Test
    public void testCalcularParcial() {
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        Cliente cliente = new Cliente(4, "Cliente Total", "20304050", "total@correo.com", "Dirección", null);

        List<ItemPedido> items = new ArrayList<>();
        items.add(new ItemPedido(1, i1, 50));
        items.add(new ItemPedido(2, i1, 100));

        Pedido pedido = pedidoController.crearPedido(4, cliente, items);

        Double parcial = pedidoController.calcularParcial(pedido);
        assertEquals(150000.0, parcial);
    }

    /**
     * Test of actualizarEstadoPedido method, of class PedidoController.
     */
    @Test
    public void testActualizarEstadoPedido() {
        System.out.println("actualizarEstadoPedido");
        Vendedor v1 = new Vendedor(1, "Juan", "Calle 1", new Coordenada(10, 20));
        ItemMenu i1 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, null, 0.5, v1);
        Cliente cliente = new Cliente(5, "Cliente Estado", "20304050", "estado@correo.com", "Dirección", null);
        List<ItemPedido> items = new ArrayList<>();
        items.add(new ItemPedido(1, i1, 20));

        Pedido pedido = pedidoController.crearPedido(5, cliente, items);

        pedidoController.actualizarEstadoPedido(pedido, EstadoPedidoEnum.RECIBIDO);

        assertEquals(EstadoPedidoEnum.RECIBIDO, pedido.getEstadoPedido());
    }

    /**
     * Test of filtrarPorEstado method, of class PedidoController.
     */
    @Test
    public void testFiltrarPorEstado() {
        Cliente cliente1 = new Cliente(6, "Cliente1", "20304050", "cliente1@correo.com", "Dirección", null);
        Cliente cliente2 = new Cliente(7, "Cliente2", "20304050", "cliente2@correo.com", "Dirección", null);

        Pedido pedido1 = new Pedido(1, cliente1, EstadoPedidoEnum.RECIBIDO, new ArrayList<>(), 100.0);
        Pedido pedido2 = new Pedido(2, cliente2, EstadoPedidoEnum.ENTREGADO, new ArrayList<>(), 200.0);

        pedidoMemoryDAO.listarPedidos().add(pedido1);
        pedidoMemoryDAO.listarPedidos().add(pedido2);

        List<Pedido> filtrados = pedidoController.filtrarPorEstado(pedidoMemoryDAO.listarPedidos(), EstadoPedidoEnum.RECIBIDO);

        assertEquals(1, filtrados.size());
        assertEquals(pedido1, filtrados.get(0));
    }

    /**
     * Test of buscarPorIdPedido method, of class PedidoController.
     */
    @Test
    public void testBuscarPorIdPedido() {
        System.out.println("buscarPorIdPedido");
        Cliente cliente1 = new Cliente(6, "Cliente1", "20304050", "cliente1@correo.com", "Dirección", null);
        Cliente cliente2 = new Cliente(7, "Cliente2", "20304050", "cliente2@correo.com", "Dirección", null);

        Pedido pedido1 = new Pedido(1, cliente1, EstadoPedidoEnum.RECIBIDO, new ArrayList<>(), 100.0);
        Pedido pedido2 = new Pedido(2, cliente2, EstadoPedidoEnum.ENTREGADO, new ArrayList<>(), 200.0);
        pedidoMemoryDAO.listarPedidos().add(pedido1);
        pedidoMemoryDAO.listarPedidos().add(pedido2);
        List<Pedido> filtrados = pedidoController.buscarPorIdPedido(1);

        assertEquals(pedido1, filtrados.get(0));

    }

}
