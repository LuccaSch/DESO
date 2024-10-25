package isi.deso.tp.model;

import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.service.PedidoController;

public class EfectivoStrategy implements PagoStrategy {

    public EfectivoStrategy() {
    }

    @Override
    public String toString() {
        return "EfectivoStrategy{" + '}';
    }

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal;
    }

    @Override
    public String nombreEstrategia() {
        return "Efectivo";
    }

    @Override
    public void generarPago(Pedido pedido) {

        PedidoController pedidoController = new PedidoController(PedidoMemoryDAO.getInstance());

        double precioFinalPedido = pedidoController.aplicarRecargo(pedido);

        System.out.println("Desde EfectivoStrategy: Precio final del pedido con id " + pedido.getId() + ": " + precioFinalPedido);

    }

}
