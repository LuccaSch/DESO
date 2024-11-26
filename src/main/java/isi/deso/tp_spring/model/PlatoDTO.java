package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlatoDTO {

    private Integer id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    private Double precio;

    private Double peso;

    private Boolean aptoCeliaco;

    private Boolean aptoVegano;

    private Integer calorias;

}
