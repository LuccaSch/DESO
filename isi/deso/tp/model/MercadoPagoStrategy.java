/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public class MercadoPagoStrategy implements PagoStrategy {

    @Override
    public double agregarRecargo(double saldo) {
        return saldo * 1.04;
    }
}
