/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public class BebidaAlcoholica extends Bebida {

    private int graduacionAlcoholica;

    public BebidaAlcoholica(int tamano, double volumen,int id, String nombre, String descripcion, double precio, Categoria categoria) {
        this.volumen = volumen;
        this.tamano = tamano;
        this.peso = volumen * 0.99;
         this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
    }

    public BebidaAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public int getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    public double peso() {
        return peso * 1.2;
    }

    @Override
    public boolean esBebidaAlcoholica() {
        return true;
    }

}
