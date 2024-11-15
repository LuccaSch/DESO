package isi.deso.tp.model;

public class Plato extends ItemMenu {

    private Integer calorias;
    private Boolean aptoCeliaco;
    private Boolean aptoVegano;

    public Plato() {
    }

    public Plato(Integer calorias, Boolean aptoCeliaco, Boolean aptoVegano, Integer id, String nombre, String descripcion, Double precio, Categoria categoria, Double peso, Vendedor vendedor) {
        super(id, nombre, descripcion, precio, categoria, peso, vendedor);
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegano = aptoVegano;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Boolean getAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(Boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public Boolean getAptoVegano() {
        return this.aptoVegano;
    }

    public void setAptoVegano(Boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

    @Override
    public Double peso() {
        return (this.peso) * 1.1;
    }

    @Override
    public Boolean esBebida() {
        return false;
    }

    @Override
    public Boolean esComida() {
        return true;
    }

    @Override
    public Boolean aptoVegano() {
        return aptoVegano;
    }

}
