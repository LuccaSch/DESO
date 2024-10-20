package isi.deso.tp.dao;

public abstract class ItemsPedidoFactoryDAO {

    public static final int MEMORY_FACTORY = 1;

    public abstract ItemsPedidoDAO getUsuarioDAO();

    public static ItemsPedidoFactoryDAO getFactory(int idFactory) {
        return switch (idFactory) {
            case MEMORY_FACTORY ->
                new ItemsPedidoMemoryFactoryDAO();
            default ->
                null;
        };

    }

}
