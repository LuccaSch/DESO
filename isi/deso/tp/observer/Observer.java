package isi.deso.tp.observer;

import isi.deso.tp.model.EstadoPedidoEnum;

public interface Observer<T> {

    //public void evento(PedidoObservable<T> o);
    public void setChange(EstadoPedidoEnum estadoPedido, int IdPedido);

    public void update(EstadoPedidoEnum estadoPedido, int idPedido);
    
    public EstadoPedidoEnum getEstadoPedido(int idPedido);
}
