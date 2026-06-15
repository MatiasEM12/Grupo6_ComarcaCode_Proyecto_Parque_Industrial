package model;

import database.DAOs.AvanceDeProyectoDAO;
import database.DAOs.EvaluacionTecnicaDAO;
import database.DAOs.ProyectoDocumentoDAO;
import database.DAOs.ProyectoProductivoDAO;
import database.JDBCs.AvanceDeProyectoDAOJDBC;
import database.JDBCs.EvaluacionTecnicaDAOJDBC;
import database.JDBCs.ProyectoDocumentoDAOJDBC;
import database.JDBCs.ProyectoProductivoDAOJDBC;
import model.DTO.EvaluacionTecnicaDTO;

import java.util.ArrayList;
import java.util.List;

public class ProyectoProductivo {
    private int idProyecto;
    private String nombre;
    private String descripcion;
    private double superficie;
    private String necesidades;
    private int empleabilidad;
    private String materiaPrima;
    private boolean enEjecucion;
    private Empresa empresa;
    private Lote lote;
    private EstadoProyecto estado;
    private List<Documento> documentos = new ArrayList<>();
    private List<AvanceDeProyecto> avanceDeProyectos = new ArrayList<>();


    private ProyectoProductivoDAO proyectoProductivoDAO = new ProyectoProductivoDAOJDBC();
    private AvanceDeProyectoDAO avanceDeProyectoDAO = new AvanceDeProyectoDAOJDBC();
    private ProyectoDocumentoDAO proyectoDocumentoDAO = new ProyectoDocumentoDAOJDBC();
    public ProyectoProductivo(int idProyecto, String nombre, String descripcion,
                              double superficie, String necesidades,
                              int empleabilidad, String materiaPrima,
                              String estado, Empresa empresa,Lote lote, List<Documento> documentos) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
        this.estado = EstadoProyecto.valueOf(estado);
        this.empresa = empresa;
        this.lote=lote;
        this.documentos = documentos;

    }
    public ProyectoProductivo(String nombre,
                              String descripcion,
                              double superficie,
                              String necesidades,
                              int empleabilidad,
                              String materiaPrima,
                              Empresa empresa,
                              Lote lote) {

        validarNombre(nombre);
        validarDescripcion(descripcion);
        validarSuperficie(superficie);

        validarEmpleabilidad(empleabilidad);
        validarMateriaPrima(materiaPrima);
        validarEmpresa(empresa);
        validarlote(lote);

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
        this.empresa = empresa;
        this.lote = lote;
        this.enEjecucion = false;
        this.estado = EstadoProyecto.SIN_INICIAR;

        proyectoProductivoDAO.registrarProyectoProductivo(this);
    }

    private  void validarlote(Lote lote) {
        if (lote == null) {
            throw new IllegalArgumentException("El lote no puede ser nulo");
        }
    }

    private  void validarEmpresa(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
    }

    private  void validarMateriaPrima(String materiaPrima) {
        if (materiaPrima == null || materiaPrima.trim().isEmpty()) {
            throw new IllegalArgumentException("La materia prima no puede estar vacía");
        }
    }

    private  void validarEmpleabilidad(int empleabilidad) {
        if (empleabilidad < 0) {
            throw new IllegalArgumentException("La empleabilidad no puede ser negativa");
        }
    }

    private  void validarSuperficie(double superficie) {
        if (superficie <= 0) {
            throw new IllegalArgumentException("La superficie debe ser mayor que cero");
        }
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
    }

    private  void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
    }


    public boolean estaEnEjecucion(){
        if (!enEjecucion){
            return false;
        }
        return true;
    }

    public String nombre(){
        return nombre;
    }

    public String descripcion(){
        return descripcion;
    }

    public double superficie(){
        return superficie;
    }

    public String necesidades(){
        return necesidades;
    }

    public int empleabilidad(){
        return empleabilidad;
    }

    public String materiaPrima(){
        return materiaPrima;
    }

    public boolean enEjecucion(){
        return enEjecucion;
    }
    public Empresa empresa(){
        return empresa;
    }

    public int idProyecto() {
        return idProyecto;
    }

    public Lote lote(){
        return lote;
    }

    public int idLote() {
        return lote.id();
    }

    public void cambiarEstado(EstadoProyecto nuevoEstado) {

        switch (nuevoEstado) {
            case EN_EJECUCION:
                if (this.estado != EstadoProyecto.SIN_INICIAR) {
                    throw new IllegalStateException("Solo se puede pasar a EN_EJECUCION desde SIN_INICIAR.");
                }
                this.estadoEnEjecucion();
                break;

            case FINALIZADO:
                if (this.estado != EstadoProyecto.EN_EJECUCION) {
                    throw new IllegalStateException("Solo se puede finalizar un proyecto que está EN_EJECUCION.");
                }
                if (this.superficie <= 0) {
                    throw new IllegalStateException("No se puede finalizar el proyecto con superficie inválida.");
                }
                this.estadoFinalizado();
                break;

            case SUSPENDIDO:
                if (this.estado != EstadoProyecto.EN_EJECUCION) {
                    throw new IllegalStateException("Solo se puede suspender un proyecto que está EN_EJECUCION.");
                }
                this.estadoSuspendido();
                break;

            case SIN_INICIAR:
                if (this.estado != EstadoProyecto.SUSPENDIDO) {
                    throw new IllegalStateException("Solo se puede volver a SIN_INICIAR desde SUSPENDIDO.");
                }
                this.estadoSinIniciar();
                break;

            default:
                throw new IllegalArgumentException("Estado no válido: " + nuevoEstado);
        }
    }

    private void estadoSinIniciar() {
        this.estado = EstadoProyecto.SIN_INICIAR;
        this.proyectoProductivoDAO.actualizarEstadoProyecto(this.idProyecto, this.estado);
    }

    private void estadoSuspendido() {
        this.estado = EstadoProyecto.SUSPENDIDO;
        this.proyectoProductivoDAO.actualizarEstadoProyecto(this.idProyecto, this.estado);
    }

    private void estadoFinalizado() {
        this.estado = EstadoProyecto.FINALIZADO;
        this.proyectoProductivoDAO.actualizarEstadoProyecto(this.idProyecto, this.estado);
    }

    private void estadoEnEjecucion() {
        this.estado = EstadoProyecto.EN_EJECUCION;
        this.proyectoProductivoDAO.actualizarEstadoProyecto(this.idProyecto, this.estado);

    }

    public void cargarDocumentos(List<Documento> documentos) {
        validarDocumentos(documentos);
        for (Documento documento : documentos) {
            this.proyectoDocumentoDAO.registrarDocumentos(this.idProyecto, documento.id());
        }

    }
    private void validarDocumentos(List<Documento> documentos){

        if(documentos==null) throw new NullPointerException("documentos no puede ser nulo");

    }

    public List<Documento> documentos() {
        return proyectoDocumentoDAO.findAllBy(this.idProyecto);
    }
    public List<EvaluacionTecnicaDTO> evaluaciones() {
        EvaluacionTecnicaDAO evaluacionTecnicaDAO = new EvaluacionTecnicaDAOJDBC();
        return evaluacionTecnicaDAO.findByProyecto(idProyecto);
    }
    public List<AvanceDeProyecto> avances() {
        return avanceDeProyectoDAO.findAllBy(this.idProyecto);
    }

    public int cargarAvance(AvanceDeProyecto avance) {
        validarAvance(avance);
        return this.avanceDeProyectoDAO.create(avance);
    }
    public String estado() {
        return estado.name();
    }

    private void validarAvance(AvanceDeProyecto avanceDeProyecto){
        if(avanceDeProyecto ==null) throw new NullPointerException("avance de proyecto no puede ser nulo");
    }


}
