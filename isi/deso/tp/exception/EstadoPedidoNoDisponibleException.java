package isi.deso.tp.exception;

public class EstadoPedidoNoDisponibleException extends Exception{
    // Constructor por defecto
    public EstadoPedidoNoDisponibleException() {
        super("El pedido no puede ser Inicializado");
    }

    // Constructor que permite personalizar el mensaje de error
    public EstadoPedidoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
