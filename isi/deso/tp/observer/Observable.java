package isi.deso.tp.observer;

import isi.deso.tp.model.EstadoPedidoEnum;

public interface Observable<T> {

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void setChange(EstadoPedidoEnum estadoPedidoNuevo);

    public void notifyChange(int idPedido);
}
