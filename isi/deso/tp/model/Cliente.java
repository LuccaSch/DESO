package isi.deso.tp.model;

import isi.deso.tp.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Observer{

    // Atributos
    private int id;
    private String nombre;
    private String cuit;
    private String email;
    private String direccion;
    private Coordenada coordenada;
    private List<Pedido> listaPedidos;

    // Constructores
    public Cliente() {
        this.listaPedidos = new ArrayList<>();
    }

    public Cliente(int id, String nombre, String cuit, String email, String direccion, Coordenada coordenada) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.listaPedidos = new ArrayList<>();
    }

    public Cliente(String nombre, String cuit, String email, String direccion) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.listaPedidos = new ArrayList<>();
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    // getters\setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return this.cuit;
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

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
    public void generarPago(Pedido pedido){
        ContextoPago contextoPago = new ContextoPago();
        System.out.println("generar el pago"); //nose como genramos el pago anteriormente
    }

    @Override
    public String toString() {
        return "Cliente{id=" + this.id + ", nombre='" + this.nombre + "'}";
    }

    @Override
    public void setChange(EstadoPedidoEnum estadoNuevo, int idPedido) {
        for(Pedido ped : listaPedidos){
            if(ped.getId() == idPedido){
                ped.setEstadoPedido(estadoNuevo);
            }
        }
    }

    @Override
    public void update(EstadoPedidoEnum estadoPedido, int idPedido) {
        setChange(estadoPedido, idPedido);
        if(estadoPedido == EstadoPedidoEnum.ENVIADO){
            for(Pedido pedido : this.listaPedidos){
                if(pedido.getId() == idPedido){
                    generarPago(pedido);
                }
            }
        }
    }

}
