package isi.deso.tp_spring.model;

import isi.deso.tp_spring.validation.ContextoPagoPagoUnique;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContextoPagoDTO {

    @ContextoPagoPagoUnique
    private Integer pago;

}
