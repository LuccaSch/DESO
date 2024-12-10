package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendedorDTO {

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String direccion;

    private Integer coordenada;

}
