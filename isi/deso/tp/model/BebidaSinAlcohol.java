package isi.deso.tp.model;

public class BebidaSinAlcohol extends Bebida {

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

    // Constructores
    public BebidaSinAlcohol() {}

    public BebidaSinAlcohol(int id,String nombre,String descripcion,double precio,Categoria categoria,double volumen,int tamano){
         this.volumen = volumen;
        this.tamano = tamano;
        this.peso = volumen * 1.04;
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
    }

    //Reescribiendo metodos heredados
    @Override
    public double peso() {
        return peso * 1.2;
    }
}
