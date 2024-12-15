package isi.deso.tp_spring.model;

public enum TipoEstrategia {
    EFECTIVO,
    TRANSFERENCIA,
    MERCADO_PAGO;

    public Integer toInteger() {
        return this.ordinal();
    }

    public static TipoEstrategia fromInteger(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        TipoEstrategia[] values = TipoEstrategia.values();
        if (value < 0 || value >= values.length) {
            throw new IllegalArgumentException("Valor inválido para el tipo de estrategia: " + value);
        }
        return values[value];
    }

}
