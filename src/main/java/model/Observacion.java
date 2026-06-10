package model;



import database.DAOs.ObservacionesDAO;
import database.JDBCs.ObservacionesDAOJDBC;

import java.time.LocalDateTime;

public class Observacion {

    private int id;
    private int idSolicitud;
    private String observacion;
    private LocalDateTime fechaCreacion;
    private String dniAdministrador;
    private ObservacionesDAO observacionesDAO = new ObservacionesDAOJDBC();
    public Observacion(int idSolicitud, String observacion, String dniAdministrador) {

        if (observacion == null || observacion.isBlank()) {
            throw new RuntimeException(
                    "La observación es obligatoria"
            );
        }

        this.idSolicitud = idSolicitud;
        this.observacion = observacion;
        this.dniAdministrador = dniAdministrador;
        this.fechaCreacion = LocalDateTime.now();
        observacionesDAO.crear(this);
    }

    public Observacion(int id, int idSolicitud, String observacion, LocalDateTime fechaCreacion, String dniAdministrador) {

        this.id = id;
        this.idSolicitud = idSolicitud;
        this.observacion = observacion;
        this.fechaCreacion = fechaCreacion;
        this.dniAdministrador = dniAdministrador;

    }

    public int id() {
        return id;
    }

    public int idSolicitud() {
        return idSolicitud;
    }

    public String observacion() {
        return observacion;
    }

    public LocalDateTime fechaCreacion() {
        return fechaCreacion;
    }

    public String dniAdministrador() {
        return dniAdministrador;
    }
}

