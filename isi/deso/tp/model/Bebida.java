package isi.deso.tp.model;

public abstract class Bebida extends ItemMenu {

    /*

    @Override
    Atributos heredados de itemMenu

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected Categoria categoria;
    protected double peso; // En kilogramos
    protected Vendedor vendedor;


     */
    // Atributos
    protected Tamano tamano;
    protected double volumen; // En litros

    // getters\setters
    public Tamano getTamano() {
        return tamano;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    //Reescribiendo metodos heredados
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
