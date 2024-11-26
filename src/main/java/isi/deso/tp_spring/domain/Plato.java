package isi.deso.tp_spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Plato extends ItemMenu {

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean aptoCeliaco;

    @Column(columnDefinition = "tinyint", length = 1)
    private Boolean aptoVegano;

    @Column
    private Integer calorias;

}
