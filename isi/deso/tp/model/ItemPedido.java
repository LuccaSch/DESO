/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public class ItemPedido {

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    private int id;
    private ItemMenu itemMenu;
    private int cantidad;
    private double precio;
    private Pedido pedido;
    public ItemPedido(int id, ItemMenu itemMenu, int cantidad, double precio) {
        this.id = id;
        this.itemMenu = itemMenu;
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }

    public int getId() {
        return id;
    }

    public ItemMenu getItemMenu() {
        return itemMenu;
    }
 public Vendedor getVendedor() {
        return this.itemMenu.getVendedor();
    }
  public Cliente getCliente() {
        return this.pedido.getCliente();
    }
    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemMenu(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
