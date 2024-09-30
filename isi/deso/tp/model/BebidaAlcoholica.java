package isi.deso.tp.model;

public class BebidaAlcoholica extends Bebida {

    /* 
    @Override
    Atributos heredados de itemMenu:
    
    protected int id;
    protected Vendedor vendedor;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected Categoria categoria;
    protected double peso;
    

    Atributos heredados de bebida:

    protected int tamano;
    protected double volumen;
    */


    
    // Atributos
    private int graduacionAlcoholica;



    // Constructores

    public BebidaAlcoholica(){}

    public BebidaAlcoholica(int id,String nombre,String descripcion,double precio,Categoria categoria,double volumen,int tamano,int graduacionAlcoholica){
        this.volumen = volumen;
        this.tamano = tamano;
        this.peso = volumen * 0.99;
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
        this.graduacionAlcoholica=graduacionAlcoholica;
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
