package isi.deso.tp.service;

import isi.deso.tp.exception.VendedorNoUnicoException;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.ContextoPago;
import isi.deso.tp.model.DTO.ItemPedidoDTO;
import isi.deso.tp.model.DTO.PedidoDTO;
import isi.deso.tp.model.EfectivoStrategy;
import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.MercadoPagoStrategy;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.TransferenciaStrategy;
import java.util.ArrayList;
import java.util.List;

public class GestorPedido {

    public Pedido crearPedido() {
        return new Pedido();
    }

    public Pedido crearPedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle) {
        Pedido pedido = null;
        try {
            pedido = new Pedido(id, cliente, pedidoDetalle);
        } catch (VendedorNoUnicoException excep) {
            System.err.println(excep.getMessage());
        }
        return pedido;
    }

    public Pedido crearPedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle, double precioTotal) {
        Pedido pedido = null;
        try {
            pedido = new Pedido(id, cliente, pedidoDetalle, precioTotal);
        } catch (VendedorNoUnicoException excep) {
            System.err.println(excep.getMessage());
        }
        return pedido;
    }

    public Pedido crearPedido(int id, Cliente cliente, EstadoPedidoEnum estadoPedido, List<ItemPedido> pedidoDetalle, double precioTotal) {
        Pedido pedido = null;
        try {
            pedido = new Pedido(id, cliente, estadoPedido, pedidoDetalle, precioTotal);
        } catch (VendedorNoUnicoException excep) {
            System.err.println(excep.getMessage());
        }
        return pedido;

    }

    public Pedido convertirPedidoDesdeDTO(PedidoDTO pedidoDTO) {
        List<ItemPedido> itemsPedido = new ArrayList<>();
        GestorItemPedido gestorItemPedido = new GestorItemPedido();
        Pedido pedidoNuevo = null;
        for (ItemPedidoDTO item : pedidoDTO.getListaItemPedidoDTO()) {
            ItemPedido itemPedido = gestorItemPedido.convertirDesdeDTO(item);
            itemsPedido.add(itemPedido);
        }
        try {
            pedidoNuevo = new Pedido(pedidoDTO.getIdPedido(), itemsPedido, pedidoDTO.getPrecioTotal());
        } catch (VendedorNoUnicoException excep) {
            System.err.println(excep.getMessage());
        }
        return pedidoNuevo;
    }

    public double realizarPagoPorDTO(PedidoDTO pedidoDTO) {
        ContextoPago contextoDePago;

        switch (pedidoDTO.getMetodoPago()) {
            case 1:
                contextoDePago = new ContextoPago(new MercadoPagoStrategy());
                System.out.println("Realiza el pago el cliente: " + pedidoDTO.getAlias());
                break;
            case 2:
                contextoDePago = new ContextoPago(new TransferenciaStrategy());
                System.out.println("Realiza el pago el cliente: " + pedidoDTO.getCbu());
                System.out.println(pedidoDTO.getCuit());
                break;
            case 3:
                contextoDePago = new ContextoPago(new EfectivoStrategy());
                System.out.println("El cliente realiza el pago en efectivo");
                break;
            default:
                System.out.println("Mal error en seleccion de metodo de pago");
                return -1;
        }

        return contextoDePago.agregarRecargo(pedidoDTO.getPrecioTotal());
    }

}
