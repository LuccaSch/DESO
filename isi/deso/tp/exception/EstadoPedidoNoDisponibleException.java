package isi.deso.tp.exception;

public class EstadoPedidoNoDisponibleException extends Exception {

    // Constructor por defecto
    public EstadoPedidoNoDisponibleException() {
        super("Pedido no puede ser inicializado");
    }

    // Constructor que permite personalizar el mensaje de error
    public EstadoPedidoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
