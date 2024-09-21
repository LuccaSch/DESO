/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package isi.deso.tp.model;
public class BebidaSinAlcohol extends Bebida {

    public BebidaSinAlcohol(int tamano, double volumen,int id, String nombre, String descripcion, double precio, Categoria categoria) {
         this.volumen = volumen;
        this.tamano = tamano;
        this.peso = volumen * 1.04;
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.categoria=categoria;
    }

    public BebidaSinAlcohol(int tamano, double volumen) {
        this.volumen = volumen;
        this.tamano = tamano;
        this.peso = volumen * 1.04;
    }

    @Override
    public double peso() {
        return peso * 1.2;
    }

    @Override
    public boolean esBebidaAlcoholica() {
        return false;
    }

}
