package isi.deso.tp.exception;

public class ItemNoEncontradoException extends Exception {

    // Constructor por defecto
    public ItemNoEncontradoException() {
        super("Item no encontrado en este contexto");
    }

    // Constructor que permite personalizar el mensaje de error
    public ItemNoEncontradoException(String msg) {
        super(msg);
    }

}
