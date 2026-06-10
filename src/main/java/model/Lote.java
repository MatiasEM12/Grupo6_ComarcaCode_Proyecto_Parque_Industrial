package model;

import database.JDBCs.LoteDAOJDBC;

public class Lote {
    private int id;
    private Ubicacion ubicacion;
    private double superficie;
    private String estado;
    private String infraestructura;
    private LoteDAOJDBC loteDAO = new LoteDAOJDBC();

    public Lote( Ubicacion ubicacion, double superficie, String estado, String infraestructura) {

        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;
        loteDAO.create(this);
        System.out.println("lote agregado");
    }
    public Lote(int id, Ubicacion ubicacion, double superficie,
                String estado, String infraestructura) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;

    }

    public void asignarEmpresa(Empresa empresa){
       // empresa.asignarLote(this);
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



    public void Ocupado() {
        this.estado="OCUPADO";
        loteDAO.update( this);
    }
}
