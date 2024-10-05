package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleException;
import isi.deso.tp.model.Pedido;

public class Inicializado extends EstadoPedido {

    public Inicializado(){
        this.nombreEstado="Inicializado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
      // pedido.setEstadoPedido(new Preparando());
    }

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
        throw new EstadoPedidoNoDisponibleException("No puede Enviar un pedido sin preparar");
    }

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
        throw new EstadoPedidoNoDisponibleException("No peude Entregar Enviar un pedido sin preparar");
    }

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {
       // pedido.setEstadoPedido(new Cancelado());
    }

}
