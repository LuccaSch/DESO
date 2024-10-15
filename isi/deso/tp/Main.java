/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package isi.deso.tp;

import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.*;


public class Main {

  public static void main(String[] args) throws ItemNoEncontradoException {
    System.out.println("hola franco");
      
    double pago=10;
    ContextoPago contextoDePago;

    int seleccion = 1;

    switch(seleccion){
            case 1: 
                contextoDePago = new ContextoPago(new MercadoPagoStrategy());
                pago= contextoDePago.agregarRecargo(pago);
                break;
            case 2: 
                contextoDePago = new ContextoPago(new TransferenciaStrategy());
                pago= contextoDePago.agregarRecargo(pago);
                break;
            case 3: 
                contextoDePago = new ContextoPago(new EfectivoStrategy());
                pago= contextoDePago.agregarRecargo(pago);
                break;
    }
    
    System.out.println("el pago es "+pago);
      
  }
  
}