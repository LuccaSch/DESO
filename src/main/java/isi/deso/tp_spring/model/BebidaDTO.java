package isi.deso.tp_spring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BebidaDTO {

    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "El nombre de la bebida es obligatorio.")
    private String nombre;

    @Size(max = 255)
    @NotBlank(message = "La descripción de la bebida es obligatoria.")
    private String descripcion;

    @NotNull(message = "El precio de la bebida es obligatorio.")
    private Double precio;

    @NotNull(message = "El peso de la bebida es obligatorio.")
    private Double peso;

    @NotNull(message = "La categoría de la bebida es obligatorio.")
    private Integer categoria;

    @NotNull(message = "El vendedor de la bebida es obligatorio.")
    private Integer vendedor;

    @NotNull(message = "El volumen de la bebida es obligatorio.")
    private Double volumen;

    @NotNull(message = "La graduación alcoholica de la bebida es obligatoria.")
    private Integer graduacionAlcoholica;

    @NotNull(message = "El tipo de la bebida es obligatorio.")
    private Integer tipoBebida;

    @NotNull(message = "El tamaño de la bebida es obligatorio.")
    private Integer tamano;

}
