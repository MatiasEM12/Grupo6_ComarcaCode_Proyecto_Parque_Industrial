package model;

import java.time.LocalDate;

public class Observacion {

    private String descripcion;
    private LocalDate fecha;

    public Observacion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("La observación no puede estar vacía");
        }

        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }
}