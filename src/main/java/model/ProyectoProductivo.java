package model;

import database.DAOs.AvanceDeProyectoDAO;
import database.DAOs.ProyectoDocumentoDAO;
import database.DAOs.ProyectoProductivoDAO;
import database.JDBCs.AvanceDeProyectoDAOJDBC;
import database.JDBCs.ProyectoDocumentoDAOJDBC;
import database.JDBCs.ProyectoProductivoDAOJDBC;

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
                              Empresa empresa,Lote lote) {

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
    public void actualizarEstado(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getNecesidades() {
        return necesidades;
    }

    public int getEmpleabilidad() {
        return empleabilidad;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public boolean isEnEjecucion() {
        return enEjecucion;
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

    public void cambiarEstado(EstadoProyecto estado) {

        if (estado == EstadoProyecto.EN_EJECUCION) {
            this.estadoEnEjecucion();
        }
        if (estado == EstadoProyecto.FINALIZADO) {
            this.estadoFinalizado();
        }
        if (estado == EstadoProyecto.SUSPENDIDO) {
            this.estadoSuspendido();
        }
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

        for (Documento documento : documentos) {
            this.proyectoDocumentoDAO.registrarDocumentos(this.idProyecto, documento.id());
        }

    }

    public List<Documento> documentos() {
        return proyectoDocumentoDAO.findAllBy(this.idProyecto);
    }

    public List<AvanceDeProyecto> avances() {
        return avanceDeProyectoDAO.findAllBy(this.idProyecto);
    }
}
