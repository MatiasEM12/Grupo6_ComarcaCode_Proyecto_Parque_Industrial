package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRadicacion {

    private static int contador = 1;

    private final int id;
    private final String numeroTramite;
    private EstadoSolicitud estado;
    private final LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private final Usuario representante;

    private final String objeto;
    private final String nombreProyecto;
    private final String descripcionServicio;
    private final String emplazamiento;
    private final String personal;
    private final String tiempo;
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

    private final List<Documento> documentos;
    private final List<Observacion> observaciones;

    public SolicitudRadicacion(String numeroTramite) {
        this(null, null, numeroTramite, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null);
    }

    public SolicitudRadicacion(Usuario representante,
                               String objeto,
                               String nombreProyecto,
                               String descripcionServicio,
                               String emplazamiento,
                               String personal,
                               String tiempo,
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
                               String nombreArchivoPDF) {

        this.id = contador++;
        this.numeroTramite = "SOL-" + String.format("%03d", id);
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.fechaActualizacion = LocalDate.now();
        this.representante = representante;

        this.objeto = valorSeguro(objeto);
        this.nombreProyecto = valorSeguro(nombreProyecto).isBlank()
                ? "Proyecto sin nombre"
                : valorSeguro(nombreProyecto);
        this.descripcionServicio = valorSeguro(descripcionServicio);
        this.emplazamiento = valorSeguro(emplazamiento);
        this.personal = valorSeguro(personal);
        this.tiempo = valorSeguro(tiempo);
        this.m2 = valorSeguro(m2);
        this.areaTrabajo = valorSeguro(areaTrabajo);
        this.areaDeposito = valorSeguro(areaDeposito);
        this.estacionamiento = valorSeguro(estacionamiento);
        this.planos = valorSeguro(planos);
        this.personalOcupar = valorSeguro(personalOcupar);
        this.materiasPrimas = valorSeguro(materiasPrimas);
        this.destinoProduccion = valorSeguro(destinoProduccion);
        this.tension = valorSeguro(tension);
        this.potencia = valorSeguro(potencia);
        this.agua = valorSeguro(agua);
        this.gas = valorSeguro(gas);
        this.residuos = valorSeguro(residuos);
        this.tratamiento = valorSeguro(tratamiento);
        this.balanza = valorSeguro(balanza);
        this.comedor = valorSeguro(comedor);
        this.coworking = valorSeguro(coworking);
        this.descripcionArchivo = valorSeguro(descripcionArchivo);
        this.nombreArchivoPDF = valorSeguro(nombreArchivoPDF);

        this.documentos = new ArrayList<>();
        this.observaciones = new ArrayList<>();
    }

    private String valorSeguro(String valor) {
        return valor == null ? "" : valor;
    }

    public void agregarDocumento(Documento documento) {
        if (documento == null) {
            throw new RuntimeException("El documento no puede ser nulo");
        }

        documentos.add(documento);
        this.fechaActualizacion = LocalDate.now();
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public void agregarObservacion(Observacion observacion) {
        if (observacion == null) {
            throw new RuntimeException("La observación no puede ser nula");
        }

        observaciones.add(observacion);
        this.estado = EstadoSolicitud.OBSERVADA;
        this.fechaActualizacion = LocalDate.now();
    }

    public ProyectoProductivo crearProyectoProductivo() {
        return new ProyectoProductivo(
                nombreProyecto,
                descripcionServicio,
                extraerNumero(m2),
                unirNecesidades(),
                extraerEntero(personalOcupar),
                materiasPrimas
        );
    }

    private String unirNecesidades() {
        return "Área de trabajo: " + areaTrabajo
                + " | Área de depósito: " + areaDeposito
                + " | Estacionamiento: " + estacionamiento
                + " | Energía: " + tension + " / " + potencia
                + " | Agua: " + agua
                + " | Gas: " + gas;
    }

    private double extraerNumero(String texto) {
        if (texto == null || texto.isBlank()) {
            return 0;
        }

        String limpio = texto.replaceAll("[^0-9.,]", "")
                .replace(",", ".");

        if (limpio.isBlank()) {
            return 0;
        }

        try {
            return Double.parseDouble(limpio);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int extraerEntero(String texto) {
        return (int) extraerNumero(texto);
    }

    public int id() {
        return id;
    }

    public String numeroTramite() {
        return numeroTramite;
    }

    public EstadoSolicitud estadoSolicitud() {
        return estado;
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

    public String objeto() {
        return objeto;
    }

    public String nombreProyecto() {
        return nombreProyecto;
    }

    public String descripcionServicio() {
        return descripcionServicio;
    }

    public String emplazamiento() {
        return emplazamiento;
    }

    public String personal() {
        return personal;
    }

    public String tiempo() {
        return tiempo;
    }

    public String m2() {
        return m2;
    }

    public String areaTrabajo() {
        return areaTrabajo;
    }

    public String areaDeposito() {
        return areaDeposito;
    }

    public String estacionamiento() {
        return estacionamiento;
    }

    public String planos() {
        return planos;
    }

    public String personalOcupar() {
        return personalOcupar;
    }

    public String materiasPrimas() {
        return materiasPrimas;
    }

    public String destinoProduccion() {
        return destinoProduccion;
    }

    public String descripcionArchivo() {
        return descripcionArchivo;
    }

    public String nombreArchivoPDF() {
        return nombreArchivoPDF;
    }

    public List<Observacion> observaciones() {
        return observaciones;
    }

    public List<Documento> documentos() {
        return documentos;
    }
}
