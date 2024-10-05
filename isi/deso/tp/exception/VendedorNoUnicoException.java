/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package isi.deso.tp.exception;

public class VendedorNoUnicoException extends Exception {

    /**
     * Creates a new instance of <code>VendedorNoUnicoException</code> without
     * detail message.
     */
    public VendedorNoUnicoException() {
        super("Vendedor no es unico");
    }

    /**
     * Constructs an instance of <code>VendedorNoUnicoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public VendedorNoUnicoException(String msg) {
        super(msg);
    }
}
