package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoDTO {

    @Size(max = 255)
    private String tipoEstrategia;

}
