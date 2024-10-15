package isi.deso.tp.observer;

public interface Observable<T> {

    public void addObserver(Observer<T> observer);

    public void notifyChange();

    public T get();
}
