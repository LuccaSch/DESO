/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.model;

public class ContextoPago {

    PagoStrategy estrategiaPago;

    public ContextoPago(PagoStrategy estrategiaPago) {
        this.estrategiaPago = estrategiaPago;
    }
    
    public double agregarRecargo(double saldo) {
        return estrategiaPago.agregarRecargo(saldo);
    }

}
