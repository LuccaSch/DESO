package isi.deso.tp.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {

    // Atributos
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;
    private List<ItemMenu> menu;

    // Constructores
    public Vendedor() {
        this.menu = new ArrayList<>();
    }

    public Vendedor(int id, String nombre, String direccion, Coordenada coordenada) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.menu = new ArrayList<>();
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

    public void setMenu(List<ItemMenu> newMenu) {
        this.menu = newMenu;
    }

    public List<ItemMenu> getMenu() {
        return this.menu;
    }

    public void addItemMenu(ItemMenu item) {
        menu.add(item);
    }

    // Calculo de distancia, con lat y long en radianes
    public double distancia(Cliente cliente) {
        double radio = 6378;
        double latV = this.coordenada.getLat();
        double lonV = this.coordenada.getLgn();
        double latC = cliente.getCoordenada().getLat();
        double lonC = cliente.getCoordenada().getLgn();
        double dentroRaiz;
        dentroRaiz = ((Math.pow(Math.sin((latC - latV) / 2), 2)) + Math.cos(latV) * Math.cos(latC) * (Math.pow(Math.sin((lonC - lonV) / 2), 2)));

        return 2 * radio * Math.asin(Math.sqrt(dentroRaiz));
    }

    public List<ItemMenu> getItemsBebidas() {
        return this.menu.stream()
                .filter(item -> item.esBebida())
                .toList();

    }

    public List<ItemMenu> getItemsComidas() {
        return this.menu.stream()
                .filter(item -> item.esComida())
                .toList();
    }

    public List<ItemMenu> getItemsComidasVeganas() {
        return this.menu.stream()
                .filter(item -> item.aptoVegano())
                .toList();
    }

    public List<ItemMenu> getItemsBebidasSinAlcohol() {
        return this.menu.stream()
                .filter(item -> item.esBebida() && !(item instanceof BebidaAlcoholica)) // Asegúrate de que sea bebida y no alcohólica
                .toList();
    }
    //Reescribiendo metodos heredados

    @Override
    public String toString() {
        return "Vendedor{id=" + this.id + ", nombre='" + this.nombre + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        //Comparaciones preliminares

        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        // Dos vendedores son iguales si su id es el mismo
        Vendedor otherVendedor = (Vendedor) obj;
        return this.id == otherVendedor.getId();
    }

    @Override
    public int hashCode() {
        // Genera un hash code basado en el id
        return Integer.hashCode(id);
    }

}
