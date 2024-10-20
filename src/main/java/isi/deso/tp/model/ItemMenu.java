package isi.deso.tp.model;

public abstract class ItemMenu {

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected Categoria categoria;
    protected double peso; // En kg
    protected Vendedor vendedor;

    public ItemMenu() {
    }

    public ItemMenu(int id, String nombre, String descripcion, double precio, Categoria categoria, double peso, Vendedor vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.peso = peso;
        this.vendedor = vendedor;
    }

    public abstract double peso();

    public abstract boolean esBebida();

    public abstract boolean esComida();

    public abstract boolean aptoVegano();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Vendedor newVendedor) {
        this.vendedor = newVendedor;
    }

}
