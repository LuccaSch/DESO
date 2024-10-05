package isi.deso.tp.model;

public class BebidaSinAlcohol extends Bebida {

    /*
    @Override
    Atributos heredados de itemMenu:

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected Categoria categoria;
    protected double peso;
    protected Vendedor vendedor;

    Atributos heredados de bebida:

    protected Tamano tamano;
    protected double volumen; // En litros
     */
    // Constructores
    public BebidaSinAlcohol() {
    }

    public BebidaSinAlcohol(int id, String nombre, String descripcion, double precio, Categoria categoria, Vendedor vendedor, Tamano tamano, double volumen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.peso = volumen * 1.04;
        this.vendedor = vendedor;
        this.tamano = tamano;
        this.volumen = volumen;

    }

    //Reescribiendo metodos heredados
    @Override
    public double peso() {
        return peso * 1.2;
    }
}
