package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRadicacion {

    private String numeroTramite;
    private EstadoSolicitud estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    private List<Documento> documentos;
    private List<Observacion> observaciones;

    public SolicitudRadicacion(String numeroTramite) {
        this.numeroTramite = numeroTramite;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDate.now();
        this.fechaActualizacion = LocalDate.now();
        this.documentos = new ArrayList<>();
        this.observaciones = new ArrayList<>();
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

    public void agregarObservacion(Observacion observacion) {
        if (observacion == null) {
            throw new RuntimeException("La observación no puede ser nula");
        }

        observaciones.add(observacion);
        this.estado = EstadoSolicitud.OBSERVADA;
        this.fechaActualizacion = LocalDate.now();
    }
}