/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model.DTO;

import java.util.List;

class ClienteDTO {

    private String nombre;
    private String cuit;
    private String email;
    private String direccion;
    private List<PedidoDTO> listaPedidos;

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

    public List<PedidoDTO> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<PedidoDTO> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

}
