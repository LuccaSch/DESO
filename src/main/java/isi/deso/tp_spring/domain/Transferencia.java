package isi.deso.tp_spring.domain;

import isi.deso.tp_spring.model.TipoEstrategia;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("1")
public class Transferencia extends Pago {

    @Column
    private String cuit;

    @Column
    private String cbu;

}
