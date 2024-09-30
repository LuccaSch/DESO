package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.Pedido;

public class Inicializado extends EstadoPedido {

    public Inicializado(){
        this.nombreEstado="Inicializado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
       pedido.setEstadoPedido(new Preparando());
    }

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        throw new EstadoPedidoNoDisponibleExeption("No puede Enviar un pedido sin preparar");
    }

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        throw new EstadoPedidoNoDisponibleExeption("No peude Entregar Enviar un pedido sin preparar");
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        pedido.setEstadoPedido(new Cancelado());
    }

}
