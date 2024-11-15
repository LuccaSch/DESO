package isi.deso.tp.model;

public class ItemPedido {

    private Integer id;
    private ItemMenu itemMenu;
    private Integer cantidad;
    private Double precio;

    public ItemPedido() {
    }

    public ItemPedido(Integer id, ItemMenu itemMenu, Integer cantidad) {
        this.id = id;
        this.itemMenu = itemMenu;
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }

    public ItemPedido(Integer id, Integer cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public ItemPedido(Integer id, ItemMenu itemMenu, Integer cantidad, Double precio) {
        this.id = id;
        this.itemMenu = itemMenu;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return this.itemMenu.getVendedor();
    }

}
