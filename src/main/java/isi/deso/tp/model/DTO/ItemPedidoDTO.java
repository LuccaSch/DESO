package isi.deso.tp.model.DTO;

public class ItemPedidoDTO {

    private Integer id;
    private Integer idItemMenu;
    private Integer cantidad;
    private Double precio;

    public ItemPedidoDTO(Integer id, Integer idItemMenu, Integer cantidad, Double precio) {
        this.id = id;
        this.idItemMenu = idItemMenu;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdItemMenu() {
        return idItemMenu;
    }

    public void setIdItemMenu(Integer idItemMenu) {
        this.idItemMenu = idItemMenu;
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

}
