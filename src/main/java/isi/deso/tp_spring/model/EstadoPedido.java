package isi.deso.tp_spring.model;

public enum EstadoPedido {

    RECIBIDO,
    PREPARANDO,
    CANCELADO,
    ENVIADO,
    ENTREGADO;

    public Integer toInteger() {
        return this.ordinal();
    }

    public static EstadoPedido fromInteger(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        EstadoPedido[] values = EstadoPedido.values();
        if (value < 0 || value >= values.length) {
            throw new IllegalArgumentException("Valor inválido para el estado del pedido: " + value);
        }
        return values[value];
    }

}
