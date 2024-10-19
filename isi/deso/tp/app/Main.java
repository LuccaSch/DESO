package isi.deso.tp.app;

import isi.deso.tp.dao.ClienteMemoryDAO;
import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.*;
import isi.deso.tp.observer.Observer;
import isi.deso.tp.service.GestorCliente;
import isi.deso.tp.service.GestorItemPedido;
import isi.deso.tp.service.GestorPedido;
import isi.deso.tp.service.GestorVendedor;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int EFECTIVO = 1;
    private static final int TRANSFERENCIA = 2;
    private static final int MERCADOPAGO = 3;

    public static void main(String[] args) throws ItemNoEncontradoException {
        GestorVendedor gestorVendedor = new GestorVendedor();
        GestorCliente gestorCliente = new GestorCliente(ClienteMemoryDAO.getInstance());

        Vendedor vendedor1 = gestorVendedor.crearVendedor(1, "Roticeria Pampita", "San Luis 3984", new Coordenada(1, 2));
        Vendedor vendedor2 = gestorVendedor.crearVendedor(2, "Polleria Don Pechuga", "Paraguay 4244", new Coordenada(2, 3));
        Vendedor vendedor3 = gestorVendedor.crearVendedor(3, "Polleria Alitas", "Vicente 2301", new Coordenada(3, 6));
        Vendedor vendedor4 = gestorVendedor.crearVendedor(1, "Almacen El Tio", "Lujan 2354", new Coordenada(1, 2));

        List<Vendedor> listaVendedores = new ArrayList<>();

        listaVendedores.add(vendedor1);
        listaVendedores.add(vendedor2);
        listaVendedores.add(vendedor3);
        listaVendedores.add(vendedor4);

        Cliente cliente1 = gestorCliente.crearCliente(1, "Lucca Perez", "2043350012", "lsch@gmail.com", "San Luis 6612", new Coordenada(1, 1));
        Cliente cliente2 = gestorCliente.crearCliente(2, "Pedro Suarez", "2042250012", "pedrito@gmail.com", "Salta 981", new Coordenada(2, 3));
        Cliente cliente3 = gestorCliente.crearCliente(2, "Patricio Vilozco", "2042255512", "p@gmail.com", "Peron 2022", new Coordenada(4, 4));
        Cliente cliente4 = gestorCliente.crearCliente(4, "Juan Alcaraz", "2042255512", "jalcaraz12@gmail.com", "Libertador 2323", new Coordenada(4, 4));

        List<Cliente> listaClientes = new ArrayList<>();

        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);
        listaClientes.add(cliente4);

        Categoria categoria1 = new Categoria(1, "Bebida");
        Categoria categoria2 = new Categoria(2, "Carne");
        Categoria categoria3 = new Categoria(3, "Postre");

        ItemMenu itemMenu1 = new BebidaAlcoholica(1, "Ron Anejo", "Ron oscuro con notas de vainilla y caramelo", 16000.00, categoria1, vendedor4, Tamano.GRANDE, 0.85, 40);
        ItemMenu itemMenu2 = new BebidaSinAlcohol(2, "Limonada Natural", "Limonada refrescante con jugo de limon natural y un toque de menta", 2000.0, categoria1, vendedor4, Tamano.CHICA, 0.5);
        ItemMenu itemMenu3 = new Plato(3, "Flan", "Flan casero con crema", 1000.0, categoria3, 0.5, vendedor4, 300, true, false);

        vendedor4.addItemMenu(itemMenu1);
        vendedor4.addItemMenu(itemMenu2);
        vendedor4.addItemMenu(itemMenu3);

        // --------------------------------
        // App de TP Etapa 4 Modulo de gestion de creacion de Pedido
        //
        System.out.println("INICIO App TP Etapa 4 Modulo de gestion de creacion de Pedido\n");

        GestorPedido gestorPedido = new GestorPedido(PedidoMemoryDAO.getInstance());
        GestorItemPedido gestorItemPedido = new GestorItemPedido();

        ItemPedido itemPedido1 = gestorItemPedido.crearItemPedido(1, itemMenu2, 3);
        ItemPedido itemPedido2 = gestorItemPedido.crearItemPedido(2, itemMenu1, 2);

        ItemPedido itemPedido3 = gestorItemPedido.crearItemPedido(3, itemMenu3, 1);
        ItemPedido itemPedido4 = gestorItemPedido.crearItemPedido(4, itemMenu2, 4);
        ItemPedido itemPedido5 = gestorItemPedido.crearItemPedido(5, itemMenu1, 1);

        List<ItemPedido> listaItemPedido1 = new ArrayList<>();

        listaItemPedido1.add(itemPedido1);
        listaItemPedido1.add(itemPedido2);

        /*
        List<ItemPedido> listaItemPedido2 = new ArrayList<>();


        listaItemPedido2.add(itemPedido3);
        listaItemPedido2.add(itemPedido4);

         */
        Pedido pedido1 = gestorPedido.crearPedido(1, cliente4, listaItemPedido1);
        gestorPedido.agregarItemPedido(pedido1, itemPedido5);
        gestorCliente.agregarPedido(pedido1.getCliente(), pedido1);

        pedido1.getContextoPago().setEstrategiaPago(new TransferenciaStrategy("45365632", "20-32734242-3"));

        System.out.println("\nFIN App TP Etapa 4 Modulo de gestion de creacion de Pedido\n");

        //
        // --------------------------------
        //
        
         System.out.println("\nINICIO App TP Etapa 5:");
        
        //Etapa 5 prueba
        /*orden de los enum de estado pedido
        1-RECIBIDO
        2-CANCELADO
        3-PREPARANDO
        4-ENVIADO
        5-ENTREGADO
        */
        
        pedido1.setEstadoPedido(EstadoPedidoEnum.RECIBIDO);
        pedido1.addObserver(pedido1.getCliente());
        List<Pedido> filtroPorEstado = gestorVendedor.buscarPedidosPorEstado(vendedor4.getId(), EstadoPedidoEnum.RECIBIDO);
        
        //gestorVendedor.actualizarEstado(EstadoPedidoEnum.PREPARANDO, filtroPorEstado);
        
        gestorVendedor.actualizarEstado(EstadoPedidoEnum.ENVIADO, filtroPorEstado);
        
        
        for(Pedido p : filtroPorEstado){
            System.out.println(p.getId());
            System.out.println("\n");   
            System.out.println(p.getEstadoPedido());
            System.out.println("\n");   
        }
        
        System.out.println("\nFIN App TP Etapa 5");
        
    }

}
