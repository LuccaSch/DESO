package isi.deso.tp_spring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendedorDTO {

    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "El nombre del vendedor es obligatorio.")
    private String nombre;

    @Size(max = 255)
    @NotBlank(message = "La direccion del vendedor es obligatoria.")
    private String direccion;

    @NotNull(message = "La coordenada del vendedor es obligatoria.")
    private Integer coordenada;

}
