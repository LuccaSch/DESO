package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleException;
import isi.deso.tp.model.Pedido;

public class Cancelado extends EstadoPedido{

    public Cancelado(){
        this.nombreEstado="Cancelado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleException {}
    
}
