package isi.deso.tp.model;

public abstract class Bebida extends ItemMenu {

    protected Tamano tamano;
    protected double volumen; // En litros

    public Bebida() {
    }

    public Bebida(Tamano tamano, double volumen, int id, String nombre, String descripcion, double precio, Categoria categoria, double peso, Vendedor vendedor) {
        super(id, nombre, descripcion, precio, categoria, peso, vendedor);
        this.tamano = tamano;
        this.volumen = volumen;
    }

    public Tamano getTamano() {
        return this.tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public double getVolumen() {
        return this.volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return false;
    }

}
