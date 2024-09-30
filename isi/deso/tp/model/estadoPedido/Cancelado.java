package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.Pedido;

public class Cancelado extends EstadoPedido{

    public Cancelado(){
        this.nombreEstado="Cancelado";
    }

    @Override
    public void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}

    @Override
    public void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption {}
    
}
