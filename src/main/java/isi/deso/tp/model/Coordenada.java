package isi.deso.tp.model;

public class Coordenada {

    private double lat;
    private double lgn;

    public Coordenada() {
    }

    public Coordenada(double lat, double lgn) {
        this.lat = lat;
        this.lgn = lgn;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLgn() {
        return lgn;
    }

    public void setLgn(double lgn) {
        this.lgn = lgn;
    }

    @Override
    public String toString() {
        return "Coordenada{" + "lat=" + this.lat + ", lgn=" + this.lgn + '}';
    }

    @Override
    public boolean equals(Object obj) {
        //Comparaciones preliminares

        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        // Dos vendedores son iguales si su lat y long son los mismos
        Coordenada otherCoordenada = (Coordenada) obj;

        return (this.lat == otherCoordenada.lat && this.lgn == otherCoordenada.lgn);
    }

    @Override
    public int hashCode() {

        return Double.hashCode(this.lat) + Double.hashCode(this.lgn);
    }
}