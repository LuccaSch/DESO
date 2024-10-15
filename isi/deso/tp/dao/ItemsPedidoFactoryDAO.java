package isi.deso.tp.dao;

public abstract class ItemsPedidoFactoryDAO {

    public static final int MEMORY_FACTORY = 1;

    public abstract ItemsPedidoDAO getUsuarioDAO();

    public static ItemsPedidoFactoryDAO getFactory(int claveFactory) {
        switch (claveFactory) {
            case MEMORY_FACTORY:
                return new ItemsPedidoMemoryFactoryDAO();
            default:
                return null;
        }

    }

}
