package isi.deso.tp_spring.domain;

import isi.deso.tp_spring.model.TipoEstrategia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoEstrategia", discriminatorType = DiscriminatorType.INTEGER)
public class Pago {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
