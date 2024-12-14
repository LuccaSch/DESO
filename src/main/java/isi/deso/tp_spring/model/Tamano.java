package isi.deso.tp_spring.model;

public enum Tamano {
    GRANDE,
    MEDIANA,
    CHICA;

    public Integer toInteger() {
        return this.ordinal();
    }

    public static Tamano fromInteger(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        Tamano[] values = Tamano.values();
        if (value < 0 || value >= values.length) {
            throw new IllegalArgumentException("Valor inválido para el tamaño: " + value);
        }
        return values[value];
    }

}
