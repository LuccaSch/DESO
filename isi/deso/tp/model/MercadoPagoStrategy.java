package isi.deso.tp.model;

public class MercadoPagoStrategy implements PagoStrategy {

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal * 1.04;
    }

    @Override
    public String nombreEstrategia() {
        return "MercadoPago";
    }
}
