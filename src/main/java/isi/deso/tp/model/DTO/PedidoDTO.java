package isi.deso.tp.model.DTO;

import java.util.*;

public class PedidoDTO {

    private Integer id;
    private Integer idCliente;
    private List<ItemPedidoDTO> listaItemPedidoDTO;
    private Integer metodoPago;
    private Double precioTotal;
    private String cbu;
    private String cuit;
    private String alias;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer idCliente, List<ItemPedidoDTO> listaItemPedidoDTO, Double saldo) {
        this.idCliente = idCliente;
        this.listaItemPedidoDTO = listaItemPedidoDTO;
        this.precioTotal = saldo;
    }

    public PedidoDTO(Integer idCliente, List<ItemPedidoDTO> listaItemPedidoDTO, Integer metodoPago, Double saldo) {
        this.idCliente = idCliente;
        this.listaItemPedidoDTO = listaItemPedidoDTO;
        this.metodoPago = metodoPago;
        this.precioTotal = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemPedidoDTO> getListaItemPedidoDTO() {
        return listaItemPedidoDTO;
    }

    public void setListaItemPedidoDTO(List<ItemPedidoDTO> listaItemPedidoDTO) {
        this.listaItemPedidoDTO = listaItemPedidoDTO;
    }

    public Integer getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Integer metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double saldo) {
        this.precioTotal = saldo;
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

    public void addItemPedidoDTO(ItemPedidoDTO... itemsPedidoDTO) {
        this.listaItemPedidoDTO.addAll(Arrays.asList(itemsPedidoDTO));
    }

}
