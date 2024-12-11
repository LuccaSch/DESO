package isi.deso.tp_spring.model;

import isi.deso.tp_spring.domain.ItemMenu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

    private Integer cantidad;
    private Double precio;
    private Integer pedido;
    private ItemMenu itemMenu;

}
