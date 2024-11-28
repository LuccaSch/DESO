package isi.deso.tp_spring.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemPedidoDTO {

    private Integer id;
    private Integer cantidad;
    private Double precio;
    private Integer pedido;

}
