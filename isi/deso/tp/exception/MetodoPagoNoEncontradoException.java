/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.exception;

/**
 *
 * @author nacho
 */
public class MetodoPagoNoEncontradoException extends Exception {
    //constructor
    public MetodoPagoNoEncontradoException(){
        super("Metodo de Pago no Disponible");
    }
    
    public MetodoPagoNoEncontradoException(String mensaje){
        super(mensaje);
    }
    
    
}
