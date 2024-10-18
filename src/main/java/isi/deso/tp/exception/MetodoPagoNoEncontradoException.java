package isi.deso.tp.exception;

public class MetodoPagoNoEncontradoException extends Exception {

    //constructor
    public MetodoPagoNoEncontradoException() {
        super("Metodo de pago no disponible");
    }

    public MetodoPagoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}
