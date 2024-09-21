package isi.deso.tp.exception;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
/**
 *
 * @author franciscokuchen
 */
public class ItemNoEncontradoException extends Exception {

    /**
     * Creates a new instance of <code>ItemNoEncontradoException</code> without
     * detail message.
     */
    public ItemNoEncontradoException() {
    }

    /**
     * Constructs an instance of <code>ItemNoEncontradoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ItemNoEncontradoException(String msg) {
        super(msg);
    }

}
