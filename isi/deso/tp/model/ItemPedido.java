package isi.deso.tp.model;

public class ItemPedido {

    // Atributos
    private int id;
    private ItemMenu itemMenu;
    private int cantidad;
    private double precio;

    // Constructores
    public ItemPedido() {
    }
    public ItemPedido(int id, ItemMenu itemMenu, int cantidad) {
        this.id = id;
        this.itemMenu = itemMenu;
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }
    
    public ItemPedido(int id, int cantidad){
        this.id=id;
        this.cantidad = cantidad;
    }

    public ItemPedido(int id, ItemMenu itemMenu, int cantidad, double precio) {
        this.id = id;
        this.itemMenu = itemMenu;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // getters\setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return this.itemMenu.getVendedor();
    }

}
