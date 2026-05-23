package model;

public class Lote {
    private int id;
    private Ubicacion ubicacion;
    private double superficie;
    private String estado;
    private String infraestructura;

    public Lote(int id, Ubicacion ubicacion, double superficie,
                String estado, String infraestructura) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;
    }

    public Lote asignarEmpresa(Empresa empresa){
        empresa.asignarLote(this);
        return null;
    }
    public int id() {
        return id;
    }

    public Ubicacion ubicacion() {
        return ubicacion;
    }

    public double superficie() {
        return superficie;
    }

    public String estado() {
        return estado;
    }

    public String infraestructura() {
        return infraestructura;
    }
}
