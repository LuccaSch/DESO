package isi.deso.tp.model;

public class BebidaAlcoholica extends Bebida {

    private int graduacionAlcoholica; // En porcentaje

    public BebidaAlcoholica() {
    }

    public BebidaAlcoholica(int graduacionAlcoholica, Tamano tamano, double volumen, int id, String nombre, String descripcion, double precio, Categoria categoria, Vendedor vendedor) {
        super(tamano, volumen, id, nombre, descripcion, precio, categoria, volumen * 0.99, vendedor);
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public int getGraduacionAlcoholica() {
        return this.graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    public double peso() {
        return this.peso * 1.2;
    }
}
