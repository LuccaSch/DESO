package isi.deso.tp.exception;

public class VendedorNoEncontradoException extends Exception {

    // Constructor por defecto
    public VendedorNoEncontradoException() {
        super("El vendedor no existen");
    }

    // Constructor que permite personalizar el mensaje de error
    public VendedorNoEncontradoException(String msg) {
        super(msg);
    }

}
