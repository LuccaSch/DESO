package isi.deso.tp.observer;

import isi.deso.tp.model.EstadoPedidoEnum;

public interface Observer<T> {

    public void update(EstadoPedidoEnum estadoPedido, int idPedido);

}
