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

        validarobservacion(observacion);
        validarDniAdmin(dniAdministrador);
        validarIDSolicitud(idSolicitud);

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


    private void validarobservacion(String observacion) {
        if (observacion == null || observacion.isBlank()) {
            throw new RuntimeException(
                    "La observación es obligatoria"
            );
        }
    }

    private void validarDniAdmin(String dniAdministrador){
        if (dniAdministrador== null ||dniAdministrador.isBlank()) {
            throw new RuntimeException(
                    "dniAdministrador es obligatoria"
            );
        }
    }

    private void validarIDSolicitud(int id){
        if (id <= 0) {
            throw new RuntimeException(
                    "id obligatorio"
            );
        }
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

