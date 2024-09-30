package isi.deso.tp.exception;

public class EstadoPedidoNoDisponibleExeption extends Exception{
    // Constructor por defecto
    public EstadoPedidoNoDisponibleExeption() {
        super("El pedido no puede ser Inicializado");
    }

    // Constructor que permite personalizar el mensaje de error
    public EstadoPedidoNoDisponibleExeption(String mensaje) {
        super(mensaje);
    }
}
