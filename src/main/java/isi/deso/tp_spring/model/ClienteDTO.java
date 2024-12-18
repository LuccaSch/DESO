package isi.deso.tp_spring.model;

import isi.deso.tp_spring.validation.ClienteCuitUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;

    @Size(max = 255)
    @NotBlank(message = "El nombre del cliente es obligatorio.")
    private String nombre;

    @Size(max = 255)
    @ClienteCuitUnique
    @NotBlank(message = "El cuit del cliente es obligatorio.")
    private String cuit;

    @Size(max = 255)
    @Email
    @NotBlank(message = "El email del cliente es obligatorio.")
    private String email;

    @Size(max = 255)
    @NotBlank(message = "La dirección del cliente es obligatoria.")
    private String direccion;

    @NotNull(message = "La coordenada del cliente es obligatorio.")
    private Integer coordenada;

}
