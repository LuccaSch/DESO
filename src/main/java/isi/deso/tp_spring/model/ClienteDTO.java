package isi.deso.tp_spring.model;

import isi.deso.tp_spring.validation.ClienteCuitUnique;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    @ClienteCuitUnique
    private String cuit;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String direccion;

    private Integer coordenada;

}
