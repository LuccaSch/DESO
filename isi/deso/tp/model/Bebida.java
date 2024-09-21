/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public abstract class Bebida extends ItemMenu {

    protected int tamano;
    protected double volumen;

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return false;
    }

    public abstract boolean esBebidaAlcoholica();

    public int getTamano() {
        return tamano;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

}
