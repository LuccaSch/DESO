package isi.deso.tp.model;

public abstract class Bebida extends ItemMenu {
    
    /* 
    
    @Override
    Atributos heredados de itemMenu
    
    protected int id;
    protected Vendedor vendedor;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected Categoria categoria;
    protected double peso;
    
    */

    // Atributos

    protected int tamano;
    protected double volumen;

    // getters\setters 

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public double getVolumen() {
        return volumen;
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
