package isi.deso.tp.exception;
public class ItemNoEncontradoException extends Exception {

    public ItemNoEncontradoException() {
        super("Item no encontrado en este contexto");
    }

    public ItemNoEncontradoException(String msg) {
        super(msg);
    }

}
