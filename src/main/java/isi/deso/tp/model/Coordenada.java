package isi.deso.tp.model;

public class Coordenada {

    private Double lat;
    private Double lgn;

    public Coordenada() {
    }

    public Coordenada(Double lat, Double lgn) {
        this.lat = lat;
        this.lgn = lgn;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLgn() {
        return lgn;
    }

    public void setLgn(Double lgn) {
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

        return (this.lat == otherCoordenada.lat.doubleValue() && this.lgn == otherCoordenada.lgn.doubleValue());
    }

    @Override
    public int hashCode() {

        return Double.hashCode(this.lat) + Double.hashCode(this.lgn);
    }
}
