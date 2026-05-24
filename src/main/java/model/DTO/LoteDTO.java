package model.DTO;

public record LoteDTO (
    int id,
    double latitud,
    double longitud,
    double altitud,
    double superficie,
    String estado,
    String infraestructura) {

    public LoteDTO(int id, double latitud, double longitud, double altitud,
                   double superficie, String estado, String infraestructura) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;
    }

    public int getId() {
        return id;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getAltitud() {
        return altitud;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getEstado() {
        return estado;
    }

    public String getInfraestructura() {
        return infraestructura;
    }
}
