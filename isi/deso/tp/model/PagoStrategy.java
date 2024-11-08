package isi.deso.tp.model;

public interface PagoStrategy {

    public double agregarRecargo(double precioTotal);

    public String nombreEstrategia();

    public void generarPago(Pedido pedido);

}
