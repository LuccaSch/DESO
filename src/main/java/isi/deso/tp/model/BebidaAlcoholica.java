package isi.deso.tp.model;

public class BebidaAlcoholica extends Bebida {

    private Integer graduacionAlcoholica; // En porcentaje

    public BebidaAlcoholica() {
    }

    public BebidaAlcoholica(Integer graduacionAlcoholica, Tamano tamano, Double volumen, Integer id, String nombre, String descripcion, Double precio, Categoria categoria, Vendedor vendedor) {
        super(tamano, volumen, id, nombre, descripcion, precio, categoria, volumen * 0.99, vendedor);
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public Integer getGraduacionAlcoholica() {
        return this.graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(Integer graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    public Double peso() {
        return this.peso * 1.2;
    }
}
