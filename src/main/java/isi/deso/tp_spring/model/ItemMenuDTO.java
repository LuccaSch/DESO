package isi.deso.tp_spring.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemMenuDTO {

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @Size(max = 255)
    private Double precio;

    @Size(max = 255)
    private Double peso;

    private Integer categoria;

    private Integer vendedor;

}
