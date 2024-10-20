package isi.deso.tp.model;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.service.GestorPedido;

public class MercadoPagoStrategy implements PagoStrategy {

    private String alias;

    public MercadoPagoStrategy(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "MercadoPagoStrategy{" + "alias=" + alias + '}';
    }

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal * 1.04;
    }

    @Override
    public String nombreEstrategia() {
        return "MercadoPago";
    }

    @Override
    public void generarPago(Pedido pedido) {
        GestorPedido gestorPedido = new GestorPedido(PedidoMemoryDAO.getInstance());

        double precioFinalPedido = gestorPedido.aplicarRecargo(pedido);

        System.out.println("Desde MercadoPagoStrategy: Precio final del pedido con id " + pedido.getId() + ": " + precioFinalPedido);
    }

}
