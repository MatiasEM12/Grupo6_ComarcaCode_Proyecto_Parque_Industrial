package model.DTO;

import java.time.LocalDateTime;

public record EvaluacionTecnicaDTO(
        int id,
        int idProyecto,
        String nombreProyecto,
        String descripcion,
        String resultado,
        String observaciones,
        LocalDateTime fecha
) {}