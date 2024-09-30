package isi.deso.tp.model;

import java.util.ArrayList;
import java.util.List;

import isi.deso.tp.exception.EstadoPedidoNoDisponibleExeption;
import isi.deso.tp.model.estadoPedido.EstadoPedido;
import isi.deso.tp.model.estadoPedido.Inicializado;


public class Pedido {

    // Atributos
    private int id;
    private Cliente cliente;
    private EstadoPedido estadoPedido;
    private List<ItemPedido> pedidoDetalle;


    // Constructores
    public Pedido() {
        pedidoDetalle=new ArrayList<>();
        estadoPedido=new Inicializado();
    }

    public Pedido(int id,Cliente cliente) {
        this.cliente = cliente;
        this.id = id;
        pedidoDetalle=new ArrayList<>();
        estadoPedido=new Inicializado();
    }


    
    //Gestion de estadosPedidos

    public String getEstado(){
        return estadoPedido.getEstado();
    }

    public boolean prepararPedido() {
        try {
            estadoPedido.prepararPedido(this);
            return true; 
        } catch (EstadoPedidoNoDisponibleExeption e) {
            System.err.println(e.getMessage());
            return false; 
        }
    }

    public boolean enviarPedido() {
        try {
            estadoPedido.enviarPedido(this);
            return true; 
        } catch (EstadoPedidoNoDisponibleExeption e) {
            System.err.println(e.getMessage());
            return false; 
        }
    }

    public boolean entregarPedido() {
        try {
            estadoPedido.entregarPedido(this);
            return true; 
        } catch (EstadoPedidoNoDisponibleExeption e) {
            System.err.println(e.getMessage());
            return false; 
        }
    }

    public boolean cancelarPedido() {
        try {
            estadoPedido.cancelarPedido(this);
            return true; 
        } catch (EstadoPedidoNoDisponibleExeption e) {
            System.err.println(e.getMessage());
            return false; 
        }
    }

    
    // getters\setters 
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEstadoPedido() {
        return estadoPedido.getEstado();
    }

    public void setEstadoPedido(EstadoPedido newEstadoPedido){
        this.estadoPedido= newEstadoPedido;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<ItemPedido> getPedidoDetalle(){
        return this.pedidoDetalle;
    }

    public void setPedidoDetalle(List<ItemPedido> newPedidoDetalle){
        this.pedidoDetalle=newPedidoDetalle;
    }

    public void addPedidoDetalle(ItemPedido newItemPedido){
        this.pedidoDetalle.add(newItemPedido);
    }

}
