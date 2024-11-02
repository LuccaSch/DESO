package isi.deso.tp.model;

public class Plato extends ItemMenu {

    private int calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;

    public Plato() {
    }

    public Plato(int calorias, boolean aptoCeliaco, boolean aptoVegano, int id, String nombre, String descripcion, double precio, Categoria categoria, double peso, Vendedor vendedor) {
        super(id, nombre, descripcion, precio, categoria, peso, vendedor);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegano = aptoVegano;
    }

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

    public boolean getAptoVegano() {
        return this.aptoVegano;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

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
