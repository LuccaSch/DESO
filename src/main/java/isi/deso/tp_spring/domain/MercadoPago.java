package isi.deso.tp_spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("2")
public class MercadoPago extends Pago {

    @Column
    private String alias;

}
