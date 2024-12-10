package isi.deso.tp_spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transferencia extends Pago {

    @Column
    private String cuit;

    @Column
    private String cbu;

}
