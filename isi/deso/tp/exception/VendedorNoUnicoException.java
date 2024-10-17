package isi.deso.tp.exception;

public class VendedorNoUnicoException extends Exception {

    // Constructor por defecto
    public VendedorNoUnicoException() {
        super("Vendedor no es unico");
    }

    // Constructor que permite personalizar el mensaje de error
    public VendedorNoUnicoException(String msg) {
        super(msg);
    }
}
