package isi.deso.tp_spring.domain;

import isi.deso.tp_spring.model.Tamano;
import isi.deso.tp_spring.model.TipoBebida;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bebida extends ItemMenu {

    @Column
    private Double volumen;

    @Column
    private Integer graduacionAlcoholica;

    @Column
    private TipoBebida tipoBebida;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Tamano tamano;

}
