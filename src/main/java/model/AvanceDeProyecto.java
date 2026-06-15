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


        validarFecha(fechaCreacion);
        validarEstado(estado);
        validarDescripcion(descripcion);
        validarProyectoProductivo(proyectoProductivo);

        this.id = id;
        this.proyectoProductivo = proyectoProductivo;
        this.fechaCreacion = fechaCreacion;
        this.documentos = documentos;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public AvanceDeProyecto(ProyectoProductivo proyectoProductivo, String descripcion, EstadoProyecto estado) {

        validarEstado(estado);
        validarDescripcion(descripcion);
        validarProyectoProductivo(proyectoProductivo);

        this.proyectoProductivo = proyectoProductivo;
        this.fechaCreacion = LocalDate.now();
        this.descripcion = descripcion;
        this.estado = estado;
        proyectoProductivo.cambiarEstado(estado);
    }

    private void validarDocumentos( List<Documento> documentos){
        if(documentos ==null) throw  new NullPointerException("los documentos no pueden ser nulos");
    }
    private void validarProyectoProductivo(ProyectoProductivo proyectoProductivo){
        if(proyectoProductivo==null) throw  new NullPointerException("proyecto productivo no puede ser nulo");
    }
    private void validarFecha(LocalDate fecha){
        if(fecha==null) throw new NullPointerException("la decha no puede ser nula");
    }
    private void validarDescripcion(String descripcion){
        if(descripcion == null) throw  new IllegalArgumentException("La descripcion no puede ser nula");
        if(descripcion.isEmpty()) throw new IllegalArgumentException("la descripcion no puede ser vacia");
    }

    private void validarEstado(EstadoProyecto estado){
        if (estado == null) throw new NullPointerException("estado de proyecto no puede ser nulo");
    }

    public void cargarDocumentos(List<Documento> documentos) {
        validarDocumentos(documentos);
        for(Documento documento : documentos){
            this.avanceDocumentoDAO.vincular(this.id,documento.id());
        }
        this.documentos.addAll(documentos);
        proyectoProductivo.cargarDocumentos(documentos);
    }

    public void setDocumentos(List<Documento> documentos) {
        validarDocumentos(documentos);
        this.documentos = documentos;
    }

    public List<Documento> documentos() {
        return List.copyOf(documentos);
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

    public void cargate() {
        avanceDeProyectoDAO.create(this);
    }
}
