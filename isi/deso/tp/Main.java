/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package isi.deso.tp;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.*;
import isi.deso.tp.model.DTO.PedidoDTO;
import isi.deso.tp.service.GestorCliente;
import isi.deso.tp.service.GestorItemPedido;
import isi.deso.tp.service.GestorPedido;
import isi.deso.tp.service.GestorVendedor;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ItemNoEncontradoException {
        GestorVendedor gestorVendedor = new GestorVendedor();
        GestorCliente gestorCliente = new GestorCliente();

        // Creando los 3 vendedores
        Vendedor vendedor1 = gestorVendedor.crearVendedor(1, "Roticeria Pampita", "San Luis 3984", new Coordenada(1, 2));
        Vendedor vendedor2 = gestorVendedor.crearVendedor(2, "Polleria Don Pechuga", "Paraguay 4244", new Coordenada(2, 3));
        Vendedor vendedor3 = gestorVendedor.crearVendedor(3, "Polleria Alitas", "Vicente 2301", new Coordenada(3, 6));

        // Creando lista de vendedores
        List<Vendedor> listaVendedores = new ArrayList<>();

        // Agregando los 3 vendedores a la lista
        listaVendedores.add(vendedor1);
        listaVendedores.add(vendedor2);
        listaVendedores.add(vendedor3);

        System.out.println("Vendedores filtrados");

        // Creando los 3 clientes
        Cliente cliente1 = gestorCliente.crearCliente(1, "Lucca Perez", "2043350012", "lsch@gmail.com", "San Luis 6612", new Coordenada(1, 1));
        Cliente cliente2 = gestorCliente.crearCliente(2, "Pedro Suarez", "2042250012", "pedrito@gmail.com", "Salta 981", new Coordenada(2, 3));
        Cliente cliente3 = gestorCliente.crearCliente(2, "Patricio Vilozco", "2042255512", "p@gmail.com", "Peron 2022", new Coordenada(4, 4));

        // Creando lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();

        // Agregando los 3 clientes a la lista
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);
        listaClientes.add(cliente3);

        Vendedor vendedor4 = gestorVendedor.crearVendedor(1, "Almacen El Tio", "Lujan 2354", new Coordenada(1, 2));
        Cliente cliente4 = gestorCliente.crearCliente(4, "Patricio Alcaraz", "2042255512", "p@gmail.com", "Peron 2323", new Coordenada(4, 4));

        Categoria categoria1 = new Categoria(1, "Bebida");
        Categoria categoria2 = new Categoria(2, "Carne");
        Categoria categoria3 = new Categoria(3, "Postre");

        ItemMenu itemMenu1 = new BebidaAlcoholica(1, "Ron Anejo", "Ron oscuro con notas de vainilla y caramelo", 16000.00, categoria1, vendedor4, Tamano.GRANDE, 0.85, 40);
        ItemMenu itemMenu2 = new BebidaSinAlcohol(2, "Limonada Natural", "Limonada refrescante con jugo de limon natural y un toque de menta", 2000.0, categoria1, vendedor4, Tamano.CHICA, 0.5);
        ItemMenu itemMenu3 = new Plato(3, "Flan", "Flan casero con crema", 1000.0, categoria3, 0.5, vendedor4, 300, true, false);

        vendedor4.addItemMenu(itemMenu1);
        vendedor4.addItemMenu(itemMenu2);
        vendedor4.addItemMenu(itemMenu3);

        GestorPedido gestorPedido = new GestorPedido();
        GestorItemPedido gestorItemPedido = new GestorItemPedido();

        ItemPedido itemPedido1 = gestorItemPedido.crearItemPedido(1, itemMenu2, 3);
        ItemPedido itemPedido2 = gestorItemPedido.crearItemPedido(2, itemMenu1, 2);
        ItemPedido itemPedido3 = gestorItemPedido.crearItemPedido(3, itemMenu3, 1);
        ItemPedido itemPedido4 = gestorItemPedido.crearItemPedido(4, itemMenu2, 4);
        ItemPedido itemPedido5 = gestorItemPedido.crearItemPedido(5, itemMenu1, 1);

        List<ItemPedido> listaItemPedido1 = new ArrayList<>();
        listaItemPedido1.add(itemPedido1);
        listaItemPedido1.add(itemPedido2);

        Pedido pedido1 = gestorPedido.crearPedido(1, cliente4, listaItemPedido1, 21500.0);
        final int EFECTIVO = 3;
        final int TRANSFERENCIA = 2;
        final int MERCADOPAGO = 1;

        PedidoDTO pedidoDTO1 = new PedidoDTO(pedido1.getCliente().getId(), gestorItemPedido.convertirAListaDTO(pedido1.getPedidoDetalle()), MERCADOPAGO, pedido1.getPrecioTotal());
        pedidoDTO1.setAlias("nachomazzoni.mp");
        pedidoDTO1.setCbu("45365632");
        pedidoDTO1.setCuit("63273424");
        pedido1.setPrecioTotal(gestorPedido.realizarPagoPorDTO(pedidoDTO1));

        pedido1.getCliente().agregarPedido(pedido1);

        System.out.println("Precio Pedido1: " + pedido1.getPrecioTotal());

        //int idCliente, List<ItemPedidoDTO> listaItemPedidoDTO, int metodoPago, double saldo
        /*List<ItemPedido> listaItemPedido = new ArrayList<>();
        listaItemPedido.add(itemPedido1);
        listaItemPedido.add(itemPedido2);
        listaItemPedido.add(itemPedido3);
        listaItemPedido.add(itemPedido4);
        listaItemPedido.add(itemPedido5);

        GestorItemPedido pedidoDetalle = new GestorItemPedido();
        pedidoDetalle.setLista(listaItemPedido);*/
        // GestorPedido gestorPedido = new GestorPedido();
        //gestorPedido.crearPedido();
        // ContextoPago contextoDePago = new ContextoPago(new TransferenciaStrategy());
        // double totalParcial = pedido1.calcularParcial();

        /*pedido1.setContextoPago(contextoDePago);
        double total = pedido1.getContextoPago().agregarRecargo(totalParcial);

        pedido1.setEstadoPedido(EstadoPedidoEnum.RECIBIDO);*/
    }

}
