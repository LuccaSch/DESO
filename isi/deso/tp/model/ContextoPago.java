package isi.deso.tp.model;

public class ContextoPago {

    private PagoStrategy pagoStrategy;

    public ContextoPago() {
    }

    public ContextoPago(PagoStrategy pagoStrategy) {
        this.pagoStrategy = pagoStrategy;
    }

    public PagoStrategy getPagoStrategy() {
        return pagoStrategy;
    }

    public void setPagoStrategy(PagoStrategy pagoStrategy) {
        this.pagoStrategy = pagoStrategy;
    }

    public double agregarRecargo(double precioTotal) {
        return pagoStrategy.agregarRecargo(precioTotal);
    }

    @Override
    public String toString() {
        return "ContextoPago{" + "estrategiaPago=" + pagoStrategy + '}';
    }

}
