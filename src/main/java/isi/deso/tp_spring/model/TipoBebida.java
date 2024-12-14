package isi.deso.tp_spring.model;

public enum TipoBebida {
    ALCOHOLICA,
    SIN_ALCOHOL;

    public Integer toInteger() {
        return this.ordinal();
    }

    public static TipoBebida fromInteger(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        TipoBebida[] values = TipoBebida.values();
        if (value < 0 || value >= values.length) {
            throw new IllegalArgumentException("Valor inválido para el tipo de bebida: " + value);
        }
        return values[value];
    }

}
