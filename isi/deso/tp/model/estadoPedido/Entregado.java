package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleException;
import isi.deso.tp.model.Pedido;

public class Entregado extends EstadoPedido{

    public Entregado(){
        this.nombreEstado="Entregado";
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
