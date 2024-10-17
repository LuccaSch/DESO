package isi.deso.tp.model.DTO;

public class ItemPedidoDTO {

    private int id;
    private int idItemMenu;
    private int cantidad;
    private double precio;

    public ItemPedidoDTO(int id, int idItemMenu, int cantidad, double precio) {
        this.id = id;
        this.idItemMenu = idItemMenu;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItemMenu() {
        return idItemMenu;
    }

    public void setIdItemMenu(int idItemMenu) {
        this.idItemMenu = idItemMenu;
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

}
