package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BebidaDTO {

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    private Double precio;

    private Double peso;

    private Double volumen;

    private Integer graduacionAlcoholica;

    private Integer tipoBebida;

    private Integer tamano;

}
