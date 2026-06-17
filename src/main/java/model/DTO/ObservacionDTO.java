package model.DTO;

import java.time.LocalDateTime;

public record ObservacionDTO(
        int id,
        int idSolicitud,
        String observacion,
        LocalDateTime fechaCreacion,
        String dniAdministrador) {
    public ObservacionDTO(int idSolicitud, String observacion, String dniAdministrador) {
        this(0,
                idSolicitud,
                observacion,
                LocalDateTime.now(),
                dniAdministrador);
    }

    public ObservacionDTO(Integer id,int idSolicitud, String observacion, String dniAdministrador) {
        this(id,
                idSolicitud,
                observacion,
                LocalDateTime.now(),
                dniAdministrador);
    }
}