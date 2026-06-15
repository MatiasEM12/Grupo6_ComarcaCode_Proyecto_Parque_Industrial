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

        validarEstaodo(estado);
        validarInfraestructura(infraestructura);
        validarSuperficie(superficie);
        validarUbicacion(ubicacion);
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;
        loteDAO.create(this);

    }
    public Lote(int id, Ubicacion ubicacion, double superficie,
                String estado, String infraestructura) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.superficie = superficie;
        this.estado = estado;
        this.infraestructura = infraestructura;

    }

    private void validarUbicacion(Ubicacion ubicacion){
        if(ubicacion == null) throw  new IllegalArgumentException("ubicacion no puede ser nula");
    }

    private void validarSuperficie(double superficie){
        if(superficie==0.0) throw  new IllegalArgumentException("superficie no puede ser 0");
        if(superficie< 0.0) throw new IllegalArgumentException("la superficie no puede ser negativa");
    }
    private void validarEstaodo(String estado){
        if(estado==null) throw  new NullPointerException("superficie no puede ser nula");
        if(estado.isEmpty()) throw new IllegalArgumentException("estado no puede ser vacio");
    }
    private void validarInfraestructura(String infraestructura){
        if( infraestructura==null) throw  new NullPointerException(" infraestructura no puede ser nula");
        if( infraestructura.isEmpty()) throw new IllegalArgumentException(" infraestructura no puede ser vacio");
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
