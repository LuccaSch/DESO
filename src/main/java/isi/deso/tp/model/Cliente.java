package isi.deso.tp.model;

import isi.deso.tp.dao.ClienteMemoryDAO;
import isi.deso.tp.dao.PedidoMemoryDAO;
import isi.deso.tp.observer.Observer;
import isi.deso.tp.service.ClienteController;
import isi.deso.tp.service.PedidoController;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Observer {

    private Integer id;
    private String nombre;
    private String cuit;
    private String email;
    private String direccion;
    private Coordenada coordenada;
    private List<Pedido> listaPedidos;

    public Cliente() {
        this.listaPedidos = new ArrayList<>();
    }

    public Cliente(Integer id, String nombre, String cuit, String email, String direccion, Coordenada coordenada) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public void display(Pedido pedido) {
        System.out.println("Desde Cliente: estado pedido actualizado: " + pedido.getEstadoPedido());
    }

    @Override
    public String toString() {
        return "Cliente{id=" + this.id + ", nombre='" + this.nombre + "'}";
    }

    @Override
    public void update(EstadoPedidoEnum estadoPedido, Integer idPedido) {
        switch (estadoPedido) {
            case EstadoPedidoEnum.RECIBIDO:
                PedidoController pedidoController = new PedidoController(PedidoMemoryDAO.getInstance());
                this.listaPedidos.add(pedidoController.buscarPorIdPedido(idPedido).getFirst());
            case EstadoPedidoEnum.ENVIADO:
                ClienteController clienteController = new ClienteController(ClienteMemoryDAO.getInstance());
                for (Pedido pedido : this.listaPedidos) {
                    if (pedido.getId() == idPedido) {
                        display(pedido);
                        //pedidoController.generarPagoPara(pedido);
                        clienteController.generarPagoPara(this.getId(), idPedido);
                    }
                }

        }
    }

}
