package isi.deso.tp.model;

public class Comida extends ItemMenu {

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

    private int calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;

    // Constructores
    public Comida(){}

    public Comida(int calorias, boolean aptoCeliaco, boolean aptoVegano, double peso,int id, String nombre, String descripcion, double precio, Categoria categoria) {
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
    }

    // getters\setters 
    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }


    public boolean getAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }
    

    public boolean getAptoVegano(){
        return this.aptoVegano;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }


    //Reescribiendo metodos heredados
    @Override
    public double peso() {
        return (this.peso) * 1.1;
    }

    @Override
    public boolean esBebida() {
        return false;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

}
