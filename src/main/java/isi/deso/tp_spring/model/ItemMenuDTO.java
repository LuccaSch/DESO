package isi.deso.tp_spring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemMenuDTO {

    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "El nombre del item menú es obligatorio.")
    private String nombre;

    @Size(max = 255)
    @NotBlank(message = "La descripción del item menú es obligatoria.")
    private String descripcion;

    @NotNull(message = "El precio del item menú es obligatorio.")
    private Double precio;

    @NotNull(message = "El peso del item menú es obligatorio.")
    private Double peso;

    @NotNull(message = "La categoría del item menú es obligatorio.")
    private Integer categoria;

    @NotNull(message = "El vendedor del item menú es obligatorio.")
    private Integer vendedor;

}
