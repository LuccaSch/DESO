package isi.deso.tp.model;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.service.PedidoController;

public class TransferenciaStrategy implements PagoStrategy {

    private String cuit;
    private String cbu;

    public TransferenciaStrategy(String cuit, String cbu) {
        this.cuit = cuit;
        this.cbu = cbu;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    @Override
    public String toString() {
        return "TransferenciaStrategy{" + "cuit=" + cuit + ", cbu=" + cbu + '}';
    }

    @Override
    public Double agregarRecargo(Double precioTotal) {
        return precioTotal * 1.02;
    }

    @Override
    public String nombreEstrategia() {
        return "Transferencia";
    }

    @Override
    public void generarPago(Pedido pedido) {
        PedidoController pedidoController = new PedidoController(PedidoMemoryDAO.getInstance());

        Double precioFinalPedido = pedidoController.aplicarRecargo(pedido);

        System.out.println("Desde TransferenciaStrategy: Precio final del pedido con id " + pedido.getId() + ": " + precioFinalPedido);
    }

}
