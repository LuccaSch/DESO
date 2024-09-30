package isi.deso.tp.model.estadoPedido;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.Pedido;

public abstract class EstadoPedido{

    protected String nombreEstado;

    public String getEstado(){
        return this.nombreEstado;
    }

    public abstract void prepararPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption;

    public abstract void enviarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption;

    public abstract void entregarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption;

    public abstract void cancelarPedido(Pedido pedido) throws EstadoPedidoNoDisponibleExeption;
}
