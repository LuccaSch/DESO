package isi.deso.tp.model;

public class BebidaAlcoholica extends Bebida {

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
    // Atributos
    private int graduacionAlcoholica; // En porcentaje

    // Constructores
    public BebidaAlcoholica() {
    }

    public BebidaAlcoholica(int id, String nombre, String descripcion, double precio, Categoria categoria, Vendedor vendedor, Tamano tamano, double volumen, int graduacionAlcoholica) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.peso = volumen * 0.99;
        this.vendedor = vendedor;
        this.tamano = tamano;
        this.volumen = volumen;
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    // getters\setters
    public int getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    //Reescribiendo metodos heredados
    @Override
    public double peso() {
        return peso * 1.2;
    }
}
