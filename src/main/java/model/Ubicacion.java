package model;

public class Ubicacion {
    public long latitud;
    public long longitud;
    public long altitud;

    public Ubicacion (long latitud,long longitud,long altitud){
        this.latitud=latitud;
        this.longitud=longitud;
        this.altitud=altitud;
    }
    public long latitud() {
        return latitud;
    }

    public long longitud() {
        return longitud;
    }

    public long altitud() {
        return altitud;
    }

}
