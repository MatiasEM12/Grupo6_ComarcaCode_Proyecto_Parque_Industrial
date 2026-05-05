package model;

public class Lote {
    private int id;
    private Ubicacion ubicacion;
    private double superficie;
    private String estado;
    private String infraestructura;

    public Lote( Ubicacion ubicacion, double superficie, String estado, String infraestructura) {
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;
    }

    public Lote asignarEmpresa(Empresa empresa){
        empresa.asignarLote(this);
    }
}
