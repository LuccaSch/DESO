package isi.deso.tp.model;

public class EfectivoStrategy implements PagoStrategy {

    @Override
    public double agregarRecargo(double precioTotal) {
        return precioTotal;
    }

    @Override
    public String nombreEstrategia() {
        return "Efectivo";
    }

}
