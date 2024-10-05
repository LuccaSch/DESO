/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model.DTO;

import java.util.*;

public class PedidoDTO {

    private int idCliente;
    private List<ItemPedidoDTO> listaItemPedidoDTO;
    private int metodoPago;
    private double precioTotal;
    private String cbu;
    private String cuit;
    private String alias;
    private int idPedido;

    public PedidoDTO(int idCliente, List<ItemPedidoDTO> listaItemPedidoDTO, int metodoPago, double saldo) {
        this.idCliente = idCliente;
        this.listaItemPedidoDTO = listaItemPedidoDTO;
        this.metodoPago = metodoPago;
        this.precioTotal = saldo;
        this.cbu=null;
        this.alias=null;
        this.cuit=null;
    }

    public PedidoDTO() {
    }
    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemPedidoDTO> getListaItemPedidoDTO() {
        return listaItemPedidoDTO;
    }

    public void setListaItemPedidoDTO(List<ItemPedidoDTO> listaItemPedidoDTO) {
        this.listaItemPedidoDTO = listaItemPedidoDTO;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double saldo) {
        this.precioTotal = saldo;
    }
    
    public void addItemPedidoDTO(ItemPedidoDTO... itemsPedidoDTO) {
        this.listaItemPedidoDTO.addAll(Arrays.asList(itemsPedidoDTO));
    }
    
    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
    
}
