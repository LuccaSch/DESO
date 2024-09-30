package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.Pedido;

public class Enviado extends EstadoPedido{

    public Enviado(){
        this.nombreEstado="Enviado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        pedido.setEstadoPedido(new Entregado());
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {
        throw new EstadoPedidoNoDisponibleExeption("No se puede cancelar un pedido enviado");
    }
    
}
