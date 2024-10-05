package isi.deso.tp.model;

import isi.deso.tp.exception.VendedorNoUnicoException;
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
        pedidoDetalle = new ArrayList<>();
        estadoPedido = EstadoPedidoEnum.RECIBIDO;
    }

    public Pedido(int id, Cliente cliente) {
        this.cliente = cliente;
        this.id = id;
        pedidoDetalle = new ArrayList<>();
        estadoPedido = EstadoPedidoEnum.RECIBIDO;
    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle) throws VendedorNoUnicoException {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = null;
        vendedorEsUnico(pedidoDetalle);
        this.pedidoDetalle = pedidoDetalle;
        this.precioTotal = pedidoDetalle.stream().mapToDouble(ItemPedido::getPrecio).sum();
    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle, double precioTotal) throws VendedorNoUnicoException {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = null;
        this.precioTotal = precioTotal;
        vendedorEsUnico(pedidoDetalle);
        this.pedidoDetalle = pedidoDetalle;
    }

    public Pedido(int id, Cliente cliente, EstadoPedidoEnum estadoPedido, List<ItemPedido> pedidoDetalle, double precioTotal) throws VendedorNoUnicoException {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = estadoPedido;
        this.precioTotal = precioTotal;
        vendedorEsUnico(pedidoDetalle);
        this.pedidoDetalle = pedidoDetalle;
    }
    
    public Pedido(int id, List<ItemPedido> pedidoDetalle, double precioTotal) throws VendedorNoUnicoException {
        this.id = id;
        this.precioTotal = precioTotal;
        vendedorEsUnico(pedidoDetalle);
        this.pedidoDetalle = pedidoDetalle;
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

    public void setPedidoDetalle(List<ItemPedido> pedidoDetalle) throws VendedorNoUnicoException {
        vendedorEsUnico(pedidoDetalle);
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

    public void setContextoPago(ContextoPago contextoPago) {
        this.contextoPago = contextoPago;
    }

    public void addPedidoDetalle(ItemPedido newItemPedido) {
        this.pedidoDetalle.add(newItemPedido);
    }

    public double calcularParcial() {
        return this.pedidoDetalle.stream().mapToDouble(ItemPedido::getPrecio).sum();
    }

    public double realizarPago(PagoStrategy metodoPago) {
        return metodoPago.agregarRecargo(calcularParcial());
    }

    public final void vendedorEsUnico(List<ItemPedido> pedidoDetalle) throws VendedorNoUnicoException {
        Vendedor vendedor = pedidoDetalle.getFirst().getVendedor();
        boolean vendedorEsUnico = pedidoDetalle.stream().allMatch(item -> item.getVendedor().equals(vendedor));

        if (!vendedorEsUnico) {
            throw new VendedorNoUnicoException("Vendedor no es unico para pedidoDetalle");
        }
    }

}
