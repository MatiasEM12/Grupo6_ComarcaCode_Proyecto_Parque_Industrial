package model;

import database.DAOs.SolicitudRadicacionDAO;
import database.JDBCs.SolicitudRadicacionDAOJDBC;

import java.time.LocalDate;

public class SolicitudRadicacion {

    private SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();
    private static int contador = 1;

    private final int id;
    private final String numeroTramite;
    private EstadoSolicitud estadoSolicitud; // 
    private final LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    private final RepresentanteEmpresa representante;
    private ProyectoProductivo proyectoProductivo;
    private Empresa empresa;

    private  String objeto;
    private final String nombreProyecto; // 
    private final String descripcionServicio; // 
    private String emplazamiento;
    private String personal;
    private String tiempoRadicacion;
    private String m2;
    private String areaTrabajo;
    private String areaDeposito;
    private String estacionamiento;
    private String planos;
    private String empleabilidad;
    private String materiasPrimas;
    private String destinoProduccion;
    private String tension;
    private String potencia;
    private String agua;
    private String gas;
    private String residuos;
    private String tratamiento;
    private String balanza;
    private String comedor;
    private String coworking;

    private String descripcionArchivo;
    private String nombreArchivoPDF;

    public SolicitudRadicacion(
            RepresentanteEmpresa representante,
            String objeto,
            String nombreProyecto,
            String descripcionServicio,
            String emplazamiento,
            String personal,
            String tiempoRadicacion,
            String m2,
            String areaTrabajo,
            String areaDeposito,
            String estacionamiento,
            String planos,
            String empleabilidad,
            String materiasPrimas,
            String destinoProduccion,
            String tension,
            String potencia,
            String agua,
            String gas,
            String residuos,
            String tratamiento,
            String balanza,
            String comedor,
            String coworking,
            String descripcionArchivo,
            String nombreArchivoPDF
    ) {

        validarObligatorio(nombreProyecto, "El nombre del proyecto es obligatorio");
        validarObligatorio(objeto, "El objeto es obligatorio");

        validarObligatorio(descripcionServicio, "La descripción del servicio es obligatoria");
        validarObligatorio(emplazamiento, "El emplazamiento es obligatorio");
        validarObligatorio(personal, "Debe indicar el personal");
        validarObligatorio(tiempoRadicacion, "Debe indicar el tiempo de radicación");

        validarObligatorio(m2, "La necesidad de m2 es obligatoria");
        validarObligatorio(areaTrabajo, "El área de trabajo es obligatoria");
        validarObligatorio(areaDeposito, "El área de depósito es obligatoria");

        validarObligatorio(planos, "Debe indicar si tiene planos");
        validarObligatorio(empleabilidad, "Debe indicar el nivel de empleabilidad");

        validarObligatorio(materiasPrimas, "Debe indicar las materias primas");
        validarObligatorio(destinoProduccion, "Debe indicar el destino de la producción");

        validarObligatorio(tension, "Debe indicar la tensión requerida");
        validarObligatorio(potencia, "Debe indicar la potencia requerida");

        validarObligatorio(agua, "Debe indicar el consumo de agua");
        validarObligatorio(gas, "Debe indicar el consumo de gas");

        validarObligatorio(residuos, "Debe indicar los residuos generados");
        validarObligatorio(tratamiento, "Debe indicar el tratamiento de residuos");


// VALIDACIONES NUMÉRICAS
        validarNumeroPositivo(m2, "Los m2 deben ser un número positivo");
        validarNumeroPositivo(areaTrabajo, "El área de trabajo debe ser un número positivo");
        validarNumeroPositivo(areaDeposito, "El área de depósito debe ser un número positivo");


// LONGITUDES
        validarLongitud(nombreProyecto, 100,
                "El nombre del proyecto no puede superar los 100 caracteres");

        validarLongitud(descripcionServicio, 1000,
                "La descripción del servicio no puede superar los 1000 caracteres");

        validarLongitud(objeto, 255,
                "El objeto no puede superar los 255 caracteres");

        this.id = contador++;
        this.numeroTramite = "SOL-" + id;
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.fechaActualizacion = LocalDate.now();

        this.representante = representante;
        this.objeto = objeto;
        this.nombreProyecto=nombreProyecto;
        this.descripcionServicio = descripcionServicio;
        this.emplazamiento = emplazamiento;
        this.personal = personal;
        this.tiempoRadicacion = tiempoRadicacion;
        this.m2 = m2;
        this.areaTrabajo = areaTrabajo;
        this.areaDeposito = areaDeposito;
        this.estacionamiento = estacionamiento;
        this.planos = planos;
        this.empleabilidad = empleabilidad;
        this.materiasPrimas = materiasPrimas;
        this.destinoProduccion = destinoProduccion;
        this.tension = tension;
        this.potencia = potencia;
        this.agua = agua;
        this.gas = gas;
        this.residuos = residuos;
        this.tratamiento = tratamiento;
        this.balanza = balanza;
        this.comedor = comedor;
        this.coworking = coworking;
        this.descripcionArchivo = descripcionArchivo;
        this.nombreArchivoPDF = nombreArchivoPDF;

        this.representante.NopuedeIngresarSolicitud();
    }

    //Constructor sobrecargado para usar en la base de datos
    public SolicitudRadicacion(int id, String numeroTramite, String estadoSolicitud, LocalDate fechaCreacion,
         LocalDate fechaActualizacion, String nombreProyecto, String descripcionServicio,
        ProyectoProductivo proyecto, Empresa empresa, RepresentanteEmpresa representante) {
            validarUsuario(representante);
            validarId(id);
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
        return nombreProyecto;
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

    public String descripcionServicio() {
        return descripcionServicio;
    }

    public String nombreArchivoPDF() {
        return nombreArchivoPDF;
    }

    public ProyectoProductivo proyecto() {
        return proyectoProductivo;
    }

    public Empresa empresa() {
        return empresa;
    }

    public EstadoSolicitud transformador(String estado) {

        switch (estado) {
            case "APROBADA":
                return EstadoSolicitud.APROBADA;
            case "RECHAZADA":
                return EstadoSolicitud.RECHAZADA;
            case "OBSERVADA":
                return EstadoSolicitud.OBSERVADA;
            case "PENDIENTE":
                return EstadoSolicitud.PENDIENTE;
            default:
                throw new RuntimeException("Estado de solicitud no válido");
        }
    }

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

    private void validarLongitud(String valor, int maximo, String mensaje) {
        if (valor != null && valor.length() > maximo) {
            throw new RuntimeException(mensaje);
        }
    }
    private void validarId(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID debe ser un número positivo");
        }
    }

    private void validarNumeroPositivo(String valor, String mensaje) {
        try {
            double numero = Double.parseDouble(valor);

            if (numero <= 0) {
                throw new RuntimeException(mensaje);
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(mensaje);
        }
    }
    public String objeto() { return objeto; }
    public String emplazamiento() { return emplazamiento; }
    public String personal() { return personal; }
    public String tiempoRadicacion() { return tiempoRadicacion; }
    public String m2() { return m2; }
    public String areaTrabajo() { return areaTrabajo; }
    public String areaDeposito() { return areaDeposito; }
    public String estacionamiento() { return estacionamiento; }
    public String planos() { return planos; }
    public String empleabilidad() { return empleabilidad; }
    public String materiasPrimas() { return materiasPrimas; }
    public String destinoProduccion() { return destinoProduccion; }
    public String tension() { return tension; }
    public String potencia() { return potencia; }
    public String agua() { return agua; }
    public String gas() { return gas; }
    public String residuos() { return residuos; }
    public String tratamiento() { return tratamiento; }
    public String balanza() { return balanza; }
    public String comedor() { return comedor; }
    public String coworking() { return coworking; }
    public String descripcionArchivo() { return descripcionArchivo; }
    public void cargate() {
        solicitudRadicacionDAO.create(this);
    }


}