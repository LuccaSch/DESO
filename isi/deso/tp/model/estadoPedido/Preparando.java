package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleException;
import isi.deso.tp.model.Pedido;

public class Preparando extends EstadoPedido{

    public Preparando(){
        this.nombreEstado="Preparando";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
     //   pedido.setEstadoPedido(new Enviado());
    }

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
        throw new EstadoPedidoNoDisponibleException("No peude Entregar un pedido sin Enviar");
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
       // pedido.setEstadoPedido(new Cancelado());
    }
    
}
