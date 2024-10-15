/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.observer;

import isi.deso.tp.model.EstadoPedidoEnum;
import isi.deso.tp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nacho
 */
public interface Observable{
    
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void setChange(EstadoPedidoEnum estadoPedidoNuevo);
    public void notifyChange(int idPedido);
    public String get();
}
