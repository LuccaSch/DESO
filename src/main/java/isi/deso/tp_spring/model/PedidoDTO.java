package isi.deso.tp_spring.model;

import isi.deso.tp_spring.validation.PedidoContextoPagoUnique;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

    private Integer id;

    private EstadoPedido estadoPedido;

    private Double precioTotal;

    private Integer cliente;

    @PedidoContextoPagoUnique
    private Integer contextoPago;

}
