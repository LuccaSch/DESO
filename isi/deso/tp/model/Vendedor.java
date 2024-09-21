/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

import java.util.*;

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

    @Override
    public String toString() {
        return "Vendedor{id=" + this.id + ", nombre='" + this.nombre + "'}";
    }

    public List<ItemMenu> getMenu() {
        return this.menu;
    }

    public void agregarPlato(ItemMenu item) {
        menu.add(item);
    }

}
