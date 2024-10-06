package isi.deso.tp.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    // Atributos
    private int id;
    private Cliente cliente;
    private EstadoPedidoEnum estadoPedido;
    private List<ItemPedido> pedidoDetalle;
    private double precioTotal;
    private ContextoPago contextoPago;

    // Constructores
    public Pedido() {
        this.pedidoDetalle = new ArrayList<>();
        this.estadoPedido = EstadoPedidoEnum.RECIBIDO;
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, Cliente cliente) {
        this.cliente = cliente;
        this.id = id;
        this.pedidoDetalle = new ArrayList<>();
        this.estadoPedido = EstadoPedidoEnum.RECIBIDO;
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle) {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = null;
        this.pedidoDetalle = pedidoDetalle;
        this.precioTotal = pedidoDetalle.stream().mapToDouble(ItemPedido::getPrecio).sum();
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = null;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, Cliente cliente, EstadoPedidoEnum estadoPedido, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = estadoPedido;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
    }

    // getters\setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoPedidoEnum getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidoEnum estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getPedidoDetalle() {
        return this.pedidoDetalle;
    }

    public void setPedidoDetalle(List<ItemPedido> pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ContextoPago getContextoPago() {
        return contextoPago;
    }

    public void setPagoStrategy(PagoStrategy estrategiaPago) {
        this.contextoPago.setEstrategiaPago(estrategiaPago);
    }

    public void addPedidoDetalle(ItemPedido itemPedido) {
        this.pedidoDetalle.add(itemPedido);
    }

}
