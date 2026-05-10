package model;

import java.time.LocalDate;

public class SolicitudRadicacion {

    private static int contador = 1;

    private final int id;
    private final String numeroTramite;
    private EstadoSolicitud estadoSolicitud;
    private final LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    private final Usuario representante;

    private final String objeto;
    private final String nombreProyecto;
    private final String descripcionServicio;
    private final String emplazamiento;
    private final String personal;
    private final String tiempoRadicacion;
    private final String m2;
    private final String areaTrabajo;
    private final String areaDeposito;
    private final String estacionamiento;
    private final String planos;
    private final String personalOcupar;
    private final String materiasPrimas;
    private final String destinoProduccion;
    private final String tension;
    private final String potencia;
    private final String agua;
    private final String gas;
    private final String residuos;
    private final String tratamiento;
    private final String balanza;
    private final String comedor;
    private final String coworking;

    private final String descripcionArchivo;
    private final String nombreArchivoPDF;

    public SolicitudRadicacion(
            Usuario representante,
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
            String personalOcupar,
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
        validarUsuario(representante);
        validarObligatorio(descripcionServicio, "La descripción del servicio es obligatoria");
        validarObligatorio(m2, "La necesidad de m2 es obligatoria");
        validarObligatorio(areaTrabajo, "El área de trabajo es obligatoria");
        validarObligatorio(areaDeposito, "El área de depósito es obligatoria");
        validarObligatorio(planos, "Debe indicar si tiene planos");
        validarObligatorio(personalOcupar, "Debe indicar el personal a ocupar");

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
        this.personalOcupar = personalOcupar;
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
    }

    private void validarUsuario(Usuario representante) {
        if (representante == null) {
            throw new RuntimeException("Debe existir un usuario logueado");
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

    public Usuario representante() {
        return representante;
    }

    public String descripcionServicio() {
        return descripcionServicio;
    }

    public String nombreArchivoPDF() {
        return nombreArchivoPDF;
    }
}