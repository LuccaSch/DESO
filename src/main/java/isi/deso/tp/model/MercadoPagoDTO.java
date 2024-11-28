package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MercadoPagoDTO {

    private Integer id;

    @Size(max = 255)
    private String alias;

}
