package isi.deso.tp.model;

import isi.deso.tp.observer.Observable;
import isi.deso.tp.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Observable {

    private int id;
    private Cliente cliente;
    private EstadoPedidoEnum estadoPedido;
    private List<ItemPedido> pedidoDetalle;
    private double precioTotal;
    private ContextoPago contextoPago;
    private List<Observer> observadores;

    public Pedido() {
        this.pedidoDetalle = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.contextoPago = new ContextoPago();
    }

    public Pedido(int id, Cliente cliente) {
        this.cliente = cliente;
        this.id = id;
        this.pedidoDetalle = new ArrayList<>();
        this.contextoPago = new ContextoPago();
        this.observadores = new ArrayList<>();
        this.observadores.add(cliente);

    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle) {
        this.id = id;
        this.cliente = cliente;
        this.pedidoDetalle = pedidoDetalle;
        this.precioTotal = pedidoDetalle.stream().mapToDouble(ItemPedido::getPrecio).sum();
        this.contextoPago = new ContextoPago();
        this.observadores = new ArrayList<>();
        this.observadores.add(cliente);

    }

    public Pedido(int id, Cliente cliente, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
        this.observadores.add(cliente);

    }

    public Pedido(int id, Cliente cliente, EstadoPedidoEnum estadoPedido, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.estadoPedido = estadoPedido;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
        this.observadores = new ArrayList<>();
        this.observadores.add(cliente);

    }

    public Pedido(int id, List<ItemPedido> pedidoDetalle, double precioTotal) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.pedidoDetalle = pedidoDetalle;
        this.contextoPago = new ContextoPago();
        this.observadores = new ArrayList<>();
        this.observadores.add(cliente);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoPedidoEnum getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidoEnum estadoPedido) {
        this.estadoPedido = estadoPedido;
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

    public void setContextoPago(ContextoPago contextoPago) {
        this.contextoPago = contextoPago;
    }

    public List<Integer> getIdVendedores() {
        return this.pedidoDetalle.stream().map(ItemPedido::getVendedor).map(Vendedor::getId).toList();
    }

    public List<Observer> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observer> observadores) {
        this.observadores = observadores;
    }

    public void setPagoStrategy(PagoStrategy pagoStrategy) {
        this.contextoPago.setPagoStrategy(pagoStrategy);
    }

    public void addPedidoDetalle(ItemPedido itemPedido) {
        this.pedidoDetalle.add(itemPedido);
    }

    public List<Observer> getListaObservadores() {
        return this.observadores;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", clienteNombre=" + cliente.getNombre() + ", estadoPedido=" + estadoPedido + ", precioTotal=" + precioTotal + ", contextoPago=" + contextoPago + '}';
    }

    @Override
    public void addObserver(Observer observer) {
        observadores.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        for (Observer o : observadores) {
            if (o.equals(observer)) {
                observadores.remove(o);
                return;
            }
        }
    }

    @Override
    public void setChange(EstadoPedidoEnum estadoPedidoNuevo) {
        this.estadoPedido = estadoPedidoNuevo;
        notifyChange(this.getId());
    }

    @Override
    public void notifyChange(int idPedido) {
        for (Observer observador : observadores) {
            observador.update(this.getEstadoPedido(), idPedido);
        }
    }

}
