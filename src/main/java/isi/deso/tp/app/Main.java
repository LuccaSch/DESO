package isi.deso.tp.app;

import isi.deso.tp.dao.jdbc.ClienteJDBC;
import isi.deso.tp.dao.jdbc.ItemMenuJDBC;
import isi.deso.tp.dao.jdbc.PedidoJDBC;
import isi.deso.tp.dao.jdbc.VendedorJDBC;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.*;
import isi.deso.tp.service.ClienteController;
import isi.deso.tp.service.ItemMenuController;
import isi.deso.tp.service.ItemPedidoController;
import isi.deso.tp.service.PedidoController;
import isi.deso.tp.service.VendedorController;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Integer EFECTIVO = 1;
    private static final Integer TRANSFERENCIA = 2;
    private static final Integer MERCADOPAGO = 3;

    public static void main(String[] args) throws ItemNoEncontradoException {
        VendedorController vendedorController = new VendedorController(new VendedorJDBC());
        ClienteController clienteController = new ClienteController(new ClienteJDBC());

        Vendedor vendedor1 = vendedorController.crearVendedor(1, "Roticeria Pampita", "San Luis 3984", new Coordenada(1.0, 2.0));
        Vendedor vendedor2 = vendedorController.crearVendedor(2, "Polleria Don Pechuga", "Paraguay 4244", new Coordenada(2.0, 3.0));
        Vendedor vendedor3 = vendedorController.crearVendedor(3, "Polleria Alitas", "Vicente 2301", new Coordenada(3.0, 6.0));
        Vendedor vendedor4 = vendedorController.crearVendedor(1, "Almacen El Tio", "Lujan 2354", new Coordenada(1.0, 2.0));

        List<Vendedor> listaVendedores = new ArrayList<>();

        listaVendedores.add(vendedor1);
        listaVendedores.add(vendedor2);
        listaVendedores.add(vendedor3);
        listaVendedores.add(vendedor4);

        Cliente cliente1 = clienteController.crearCliente(1, "Lucca Perez", "2043350012", "lsch@gmail.com", "San Luis 6612", new Coordenada(1.0, 1.0));
        Cliente cliente2 = clienteController.crearCliente(2, "Pedro Suarez", "2042250012", "pedrito@gmail.com", "Salta 981", new Coordenada(2.0, 3.0));
        Cliente cliente3 = clienteController.crearCliente(3, "Patricio Vilozco", "2042255512", "p@gmail.com", "Peron 2022", new Coordenada(4.0, 4.0));
        Cliente cliente4 = clienteController.crearCliente(4, "Juan Alcaraz", "2042255512", "jalcaraz12@gmail.com", "Libertador 2323", new Coordenada(4.0, 4.0));

        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);
        listaClientes.add(cliente4);

        Categoria categoria1 = new Categoria(1, "Bebida");
        Categoria categoria2 = new Categoria(2, "Carne");
        Categoria categoria3 = new Categoria(3, "Postre");
        ItemMenu itemMenu1 = new BebidaAlcoholica(40, Tamano.GRANDE, 0.85, 1, "Ron Anejo", "Ron oscuro con notas de vainilla y caramelo", 16000.00, categoria1, vendedor4);
        ItemMenu itemMenu2 = new BebidaSinAlcohol(Tamano.CHICA, 0.5, 2, "Limonada Natural", "Limonada refrescante con jugo de limon natural y un toque de menta", 2000.0, categoria1, vendedor1);
        ItemMenu itemMenu3 = new Plato(300, true, false, 3, "Flan", "Flan casero con crema", 1000.0, categoria3, 0.5, vendedor4);
        ItemMenuController itemMenuController = new ItemMenuController(new ItemMenuJDBC());
        itemMenuController.actualizarItemMenu(itemMenu1);
        itemMenuController.actualizarItemMenu(itemMenu2);

        vendedor4.addItemMenu(itemMenu1);
        vendedor4.addItemMenu(itemMenu2);
        vendedor4.addItemMenu(itemMenu3);

        PedidoController pedidoController = new PedidoController(new PedidoJDBC());
        ItemPedidoController itemPedidoController = new ItemPedidoController();

        List<ItemPedido> listaItemPedido1 = new ArrayList<>();

        ContextoPago contextoPago1 = new ContextoPago(new TransferenciaStrategy("45365632", "20-32734242-3"));

        Pedido pedido1 = pedidoController.crearPedido(1, clienteController.listarClientes().getFirst(), listaItemPedido1, EstadoPedidoEnum.RECIBIDO, contextoPago1);

        ItemPedido itemPedido1 = itemPedidoController.crearItemPedido(1, 1, itemMenu2, 3);

        // Inicio i = new Inicio();
        // i.setVisible(true);
        //
        // --------------------------------
        //
        System.out.println("\nINICIO App TP Etapa 7\n");

        List<Cliente> lista = clienteController.listarClientes();
        System.out.println(lista.toString());

        System.out.println("\nFIN App TP Etapa 7");
        //
        // --------------------------------
        //

    }

}
