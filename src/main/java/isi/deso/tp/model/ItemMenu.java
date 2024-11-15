package isi.deso.tp.model;

import java.util.List;

public abstract class ItemMenu {

    protected Integer id;
    protected String nombre;
    protected String descripcion;
    protected Double precio;
    protected Categoria categoria;
    protected Double peso; // En kg
    protected Vendedor vendedor;

    public ItemMenu() {
    }

    public ItemMenu(Integer id, String nombre, String descripcion, Double precio, Categoria categoria, Double peso, Vendedor vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.peso = peso;
        this.vendedor = vendedor;
    }

    public abstract Double peso();

    public abstract Boolean esBebida();

    public abstract Boolean esComida();

    public abstract Boolean aptoVegano();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ItemMenu obtenerItem(String n, List<ItemMenu> l) {
        for (ItemMenu i : l) {
            if (n.equals(i.getNombre())) {
                return i;
            }
        }
        return null;

    }

}
