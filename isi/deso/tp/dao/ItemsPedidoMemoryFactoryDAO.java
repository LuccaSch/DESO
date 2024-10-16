package isi.deso.tp.dao;

public class ItemsPedidoMemoryFactoryDAO extends ItemsPedidoFactoryDAO {

    public ItemsPedidoDAO getUsuarioDAO() {
        return new ItemsPedidoMemoryDAO();

    }

}
