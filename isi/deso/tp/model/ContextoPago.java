package isi.deso.tp.model;

public class ContextoPago {

    private PagoStrategy estrategiaPago;

    public ContextoPago() {
    }

    public ContextoPago(PagoStrategy estrategiaPago) {
        this.estrategiaPago = estrategiaPago;
    }

    public double agregarRecargo(double precioTotal) {
        return estrategiaPago.agregarRecargo(precioTotal);
    }

    public PagoStrategy getEstrategiaPago() {
        return estrategiaPago;
    }

    public void setEstrategiaPago(PagoStrategy estrategiaPago) {
        this.estrategiaPago = estrategiaPago;
    }

}
