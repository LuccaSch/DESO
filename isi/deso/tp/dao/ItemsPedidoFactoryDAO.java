/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package isi.deso.tp.dao;

public abstract class ItemsPedidoFactoryDAO {

    public static final int MEMORY_FACTORY = 1;

    public abstract ItemsPedidoDAO getUsuarioDao();

    public static ItemsPedidoFactoryDAO getFactory(int claveFactory) {
        switch (claveFactory) {
            case MEMORY_FACTORY:
                return new ItemsPedidoMemoryFactoryDAO();
            default:
                return null;
        }

    }

}
