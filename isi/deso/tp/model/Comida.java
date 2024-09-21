/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public class Comida extends ItemMenu {

    private int calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;

    public Comida() {
    }

    public Comida(int calorias, boolean aptoCeliaco, boolean aptoVegano, double peso,int id, String nombre, String descripcion, double precio, Categoria categoria) {
        this.calorias = calorias;
        this.aptoCeliaco = aptoCeliaco;
        this.aptoVegano = aptoVegano;
        this.peso = peso;
         this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
    }

    @Override
    public boolean esBebida() {
        return false;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    public int getCalorias() {
        return calorias;
    }

    public boolean aptoCeliaco() {
        return aptoCeliaco;
    }

    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

    public double getPeso() {
        return peso;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double peso() {
        return (this.peso) * 1.1;
    }
}
