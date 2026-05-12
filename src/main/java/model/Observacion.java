package model;

import java.time.LocalDate;

public class Observacion {

    private int id;
    private String descripcion;
    private LocalDate fecha;
    private Documento documentoRespuesta;

    public Observacion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("La descripción es obligatoria");
        }

        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
    }

    public void agregarRespuesta(Documento documento) {
        if (documento == null) {
            throw new RuntimeException("Debe adjuntarse un documento de respuesta");
        }

        this.documentoRespuesta = documento;
    }
}