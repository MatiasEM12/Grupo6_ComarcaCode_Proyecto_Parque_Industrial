package model;

import database.DAOs.AvanceDeProyectoDAO;
import database.DAOs.AvanceDocumentoDAO;
import database.JDBCs.AvanceDeProyectoDAOJDBC;
import database.JDBCs.AvanceDocumentoDAOJDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvanceDeProyecto {

    private int id;
    private ProyectoProductivo proyectoProductivo;
    private LocalDate fechaCreacion;
    private List<Documento> documentos = new ArrayList<>();
    private String descripcion;
    private EstadoProyecto estado;

    private AvanceDeProyectoDAO avanceDeProyectoDAO = new AvanceDeProyectoDAOJDBC();
    private AvanceDocumentoDAO avanceDocumentoDAO = new AvanceDocumentoDAOJDBC();
    public AvanceDeProyecto(int id, ProyectoProductivo proyectoProductivo, LocalDate fechaCreacion, List<Documento> documentos, String descripcion, EstadoProyecto estado) {
        this.id = id;
        this.proyectoProductivo = proyectoProductivo;
        this.fechaCreacion = fechaCreacion;
        this.documentos = documentos;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public AvanceDeProyecto(ProyectoProductivo proyectoProductivo, String descripcion, EstadoProyecto estado) {
        this.proyectoProductivo = proyectoProductivo;
        this.fechaCreacion = LocalDate.now();
        this.descripcion = descripcion;
        this.estado = estado;

        this.avanceDeProyectoDAO.create(this);

        proyectoProductivo.cambiarEstado(estado);


    }

    public void cargarDocumentos(List<Documento> documentos) {
        for(Documento documento : documentos){
            this.avanceDocumentoDAO.vincular(this.id,documento.id());
        }
        this.documentos.addAll(documentos);
        proyectoProductivo.cargarDocumentos(documentos);
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Documento> documentos() {
        return List.copyOf(documentos);
    }


    public void cargate() {
    }
    public int id() {
        return id;
    }

    public ProyectoProductivo proyectoProductivo() {
        return proyectoProductivo;
    }

    public LocalDate fechaCreacion() {
        return fechaCreacion;
    }

    public String descripcion() {
        return descripcion;
    }

    public EstadoProyecto estado() {
        return estado;
    }
}
