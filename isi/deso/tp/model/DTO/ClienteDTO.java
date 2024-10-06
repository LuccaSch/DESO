package isi.deso.tp.model.DTO;

import java.util.List;

public class ClienteDTO {

    private String nombre;
    private String cuit;
    private String email;
    private String direccion;
    private List<PedidoDTO> listaPedidosDTO;

    public ClienteDTO(String nombre, String cuit, String email, String direccion, List<PedidoDTO> listaPedidosDTO) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.listaPedidosDTO = listaPedidosDTO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PedidoDTO> getListaPedidosDTO() {
        return listaPedidosDTO;
    }

    public void setListaPedidosDTO(List<PedidoDTO> listaPedidosDTO) {
        this.listaPedidosDTO = listaPedidosDTO;
    }

}
