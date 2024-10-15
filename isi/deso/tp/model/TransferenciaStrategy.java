package isi.deso.tp.model;

public class TransferenciaStrategy implements PagoStrategy {

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal * 1.02;
    }

    @Override
    public String nombreEstrategia() {
        return "Transferencia";
    }
}
