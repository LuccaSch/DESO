package isi.deso.tp_spring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoDTO {

    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "El nombre del plato es obligatorio.")
    private String nombre;

    @Size(max = 255)
    @NotBlank(message = "La descripción del plato es obligatoria.")
    private String descripcion;

    @NotNull(message = "El precio del plato es obligatorio.")
    private Double precio;

    @NotNull(message = "El peso del plato es obligatorio.")
    private Double peso;

    @NotNull(message = "La categoría del plato es obligatorio.")
    private Integer categoria;

    @NotNull(message = "El vendedor del plato es obligatorio.")
    private Integer vendedor;

    @NotNull(message = "Apto celiaco del plato es obligatorio.")
    private Boolean aptoCeliaco;

    @NotNull(message = "Apto vegano del plato es obligatorio.")
    private Boolean aptoVegano;

    @NotNull(message = "Las calorias del plato es obligatoria.")
    private Integer calorias;

}
