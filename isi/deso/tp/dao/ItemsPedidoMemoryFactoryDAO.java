/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isi.deso.tp.dao;

public class ItemsPedidoMemoryFactoryDAO extends ItemsPedidoFactoryDAO {

    public ItemsPedidoDAO getUsuarioDao() {
        return new ItemsPedidoMemoryDAO();

    }

}
