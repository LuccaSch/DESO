package isi.deso.tp_spring.model;

import isi.deso.tp_spring.validation.PedidoContextoPagoUnique;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

    private Integer id;

    @NotNull(message = "El estado del pedido es obligatorio.")
    private Integer estadoPedido;

    @NotNull(message = "El precio del pedido es obligatorio.")
    private Double precioTotal;

    @NotNull(message = "El id del cliente del pedido es obligatorio.")
    private Integer cliente;

    @PedidoContextoPagoUnique
    @NotNull(message = "El contexto pago del pedido es obligatorio.")
    private Integer contextoPago;

}
