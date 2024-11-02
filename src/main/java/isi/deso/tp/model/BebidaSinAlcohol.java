package isi.deso.tp.model;

public class BebidaSinAlcohol extends Bebida {

    public BebidaSinAlcohol() {
    }

    public BebidaSinAlcohol(Tamano tamano, double volumen, int id, String nombre, String descripcion, double precio, Categoria categoria, Vendedor vendedor) {
        super(tamano, volumen, id, nombre, descripcion, precio, categoria, volumen * 1.04, vendedor);
    }

    @Override
    public double peso() {
        return this.peso * 1.2;
    }
}
