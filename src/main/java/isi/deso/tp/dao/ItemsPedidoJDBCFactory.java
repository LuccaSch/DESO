package isi.deso.tp.dao;

import isi.deso.tp.dao.jdbc.ItemsPedidoJDBC;

public class ItemsPedidoJDBCFactory extends ItemsPedidoFactoryDAO {

    public ItemsPedidoDAO getUsuarioDAO() {
        return new ItemsPedidoJDBC();

    }

}
