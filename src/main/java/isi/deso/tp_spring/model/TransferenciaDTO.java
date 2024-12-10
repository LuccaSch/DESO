package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDTO {

    @Size(max = 255)
    private String cuit;

    @Size(max = 255)
    private String cbu;

}
