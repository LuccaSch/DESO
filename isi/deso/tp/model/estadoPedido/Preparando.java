package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.Pedido;

public class Preparando extends EstadoPedido{

    public Preparando(){
        this.nombreEstado="Preparando";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        pedido.setEstadoPedido(new Enviado());
    }

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        throw new EstadoPedidoNoDisponibleExeption("No peude Entregar un pedido sin Enviar");
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        pedido.setEstadoPedido(new Cancelado());
    }
    
}
