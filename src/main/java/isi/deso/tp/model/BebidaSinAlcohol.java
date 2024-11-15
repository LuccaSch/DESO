package isi.deso.tp.model;

public class BebidaSinAlcohol extends Bebida {

    public BebidaSinAlcohol() {
    }

    public BebidaSinAlcohol(Tamano tamano, Double volumen, Integer id, String nombre, String descripcion, Double precio, Categoria categoria, Vendedor vendedor) {
        super(tamano, volumen, id, nombre, descripcion, precio, categoria, volumen * 1.04, vendedor);
    }

    @Override
    public Double peso() {
        return this.peso * 1.2;
    }
}
