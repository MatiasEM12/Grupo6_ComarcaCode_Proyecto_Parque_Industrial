package model;

import database.DAOs.SolicitudRadicacionDAO;
import database.JDBCs.SolicitudRadicacionDAOJDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRadicacion {



    private int id;
    private String numeroTramite;
    private EstadoSolicitud estadoSolicitud; // 
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    private RepresentanteEmpresa representante;
    private ProyectoProductivo proyectoProductivo;
    private SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();
    private List<Documento> archivosAdjuntos;
    public SolicitudRadicacion(RepresentanteEmpresa representante, ProyectoProductivo proyecto) {

        if(proyecto == null){
            throw new RuntimeException("El proyecto es obligatorio");
        }
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.fechaActualizacion = LocalDate.now();
        this.representante = representante;
        this.proyectoProductivo = proyecto;
        this.archivosAdjuntos=new ArrayList<>();
       // solicitudRadicacionDAO.create(this);

    }

    public SolicitudRadicacion(int id, String numeroTramite,RepresentanteEmpresa representante, ProyectoProductivo proyecto,LocalDate fechaCreacion,LocalDate fechaActualizacion
                               ,String estado) {

        if(proyecto == null){
            throw new RuntimeException("El proyecto es obligatorio");
        }
        this.id=id;
        this.numeroTramite=numeroTramite;
        this.estadoSolicitud = this.transformador(estado);
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion();
        this.representante = representante;
        this.proyectoProductivo = proyecto;
        this.archivosAdjuntos=new ArrayList<>();

    }

    //Constructor sobrecargado para usar en la base de datos
    /*public SolicitudRadicacion(int id, String numeroTramite, String estadoSolicitud, LocalDate fechaCreacion,
         LocalDate fechaActualizacion, String nombreProyecto, String descripcionServicio,
        ProyectoProductivo proyecto, Empresa empresa, RepresentanteEmpresa representante) {
            validarUsuario(representante);

            this.id = id;
            this.numeroTramite = numeroTramite;
            this.estadoSolicitud = transformador(estadoSolicitud);
            this.fechaCreacion = fechaCreacion;
            this.fechaActualizacion = fechaActualizacion;
            this.representante = representante;
            this.nombreProyecto = nombreProyecto;
            this.descripcionServicio = descripcionServicio;
            this.proyectoProductivo = proyecto;
            this.empresa = empresa;
         }*/

    private void validarUsuario(RepresentanteEmpresa representante) {
        if (representante == null) {
            throw new RuntimeException("Debe existir un representante logueado");
        }
    }

    private void validarObligatorio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new RuntimeException(mensaje);
        }
    }

    public void aprobar() {
        this.estadoSolicitud = EstadoSolicitud.APROBADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public void rechazar() {
        this.estadoSolicitud = EstadoSolicitud.RECHAZADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public void observar() {
        this.estadoSolicitud = EstadoSolicitud.OBSERVADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public int id() {
        return id;
    }

    public String nombreProyecto(){
        return proyectoProductivo.nombre();
    }
    public String descripcionServicio(){
        return proyectoProductivo.descripcionServicio();
    }
    public ProyectoProductivo proyectoProductivo() {
        return proyectoProductivo;
    }
    public String numeroTramite() {
        return numeroTramite;
    }

    public EstadoSolicitud estadoSolicitud() {
        return estadoSolicitud;
    }

    public LocalDate fechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate fechaActualizacion() {
        return fechaActualizacion;
    }

    public RepresentanteEmpresa representante() {
        return representante;
    }




    public ProyectoProductivo proyecto() {
        return proyectoProductivo;
    }



    public EstadoSolicitud transformador(String estado) {

        switch (estado) {
            case "APROBADA":
                return EstadoSolicitud.APROBADA;
            case "RECHAZADA":
                return EstadoSolicitud.RECHAZADA;
            case "OBSERVADA":
                return EstadoSolicitud.OBSERVADA;
            default:
                throw new RuntimeException("Estado de solicitud no válido");
        }
    }
}