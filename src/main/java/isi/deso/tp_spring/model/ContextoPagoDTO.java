package isi.deso.tp_spring.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContextoPagoDTO {

    private Integer id;

    @ContextoPagoPagoUnique
    private Integer pago;

}
