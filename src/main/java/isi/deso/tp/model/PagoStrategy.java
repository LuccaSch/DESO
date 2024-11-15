package isi.deso.tp.model;

public interface PagoStrategy {

    public Double agregarRecargo(Double precioTotal);

    public String nombreEstrategia();

    public void generarPago(Pedido pedido);

}
