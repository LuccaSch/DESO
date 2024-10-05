package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleException;
import isi.deso.tp.model.Pedido;

public class Enviado extends EstadoPedido{

    public Enviado(){
        this.nombreEstado="Enviado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
        //pedido.setEstadoPedido(new Entregado());
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
        throw new EstadoPedidoNoDisponibleException("No se puede cancelar un pedido enviado");
    }
    
}
