package isi.deso.tp.model;

public abstract class Bebida extends ItemMenu {

    protected Tamano tamano;
    protected Double volumen; // En litros

    public Bebida() {
    }

    public Bebida(Tamano tamano, Double volumen, Integer id, String nombre, String descripcion, Double precio, Categoria categoria, Double peso, Vendedor vendedor) {
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

    public Double getVolumen() {
        return this.volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    @Override
    public Boolean esBebida() {
        return true;
    }

    @Override
    public Boolean esComida() {
        return false;
    }

    @Override
    public Boolean aptoVegano() {
        return false;
    }

}
