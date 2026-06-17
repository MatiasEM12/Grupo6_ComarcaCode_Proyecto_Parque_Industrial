package model.DTO;

import model.ProyectoProductivo;

import java.time.LocalDateTime;
import java.util.List;

public record InformeParqueDTO(
        LocalDateTime fechaGeneracion,
        int totalProyectos,
        int proyectosEnEjecucion,
        int proyectosFinalizados,
        int proyectosSuspendidos,
        int proyectosSinIniciar,
        int totalLotes,
        int lotesDisponibles,
        int lotesOcupados,
        int totalEvaluacionesTecnicas,
        int empleabilidadTotal,
        double superficieTotalProyectos,
        List<ProyectoProductivo> proyectos
) {}
