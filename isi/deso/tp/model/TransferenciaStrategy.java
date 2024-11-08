package isi.deso.tp.model;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.service.GestorPedido;

public class TransferenciaStrategy implements PagoStrategy {

    private String cuit;
    private String cbu;

    public TransferenciaStrategy(String cuit, String cbu) {
        this.cuit = cuit;
        this.cbu = cbu;
    }

    @Override
    public String toString() {
        return "TransferenciaStrategy{" + "cuit=" + cuit + ", cbu=" + cbu + '}';
    }

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal * 1.02;
    }

    @Override
    public String nombreEstrategia() {
        return "Transferencia";
    }

    @Override
    public void generarPago(Pedido pedido) {
        GestorPedido gestorPedido = new GestorPedido(PedidoMemoryDAO.getInstance());

        double precioFinalPedido = gestorPedido.aplicarRecargo(pedido);

        System.out.println("Desde TransferenciaStrategy: Precio final del pedido con id " + pedido.getId() + ": " + precioFinalPedido);
    }

}
