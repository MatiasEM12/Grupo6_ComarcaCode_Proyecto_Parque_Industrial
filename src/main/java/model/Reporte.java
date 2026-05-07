package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reporte {
    private int id = 0;
    private final TipoReporte tipo;
    private final String descripcion;
    private final LocalDate fecha;
    private final Usuario usuario; //Lo cree porque es necesario para saber quien genero el reporte
    private final List<Documento> documentos;

    public Reporte(TipoReporte tipo, String descripcion, Usuario generadoPor) {
        this.id = id;
        validarTipo(tipo);
        validarDescripcion(descripcion);
        validarUsuario(generadoPor);

        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.usuario = generadoPor;
        this.documentos = new ArrayList<>();
    }

    private static void validarUsuario(Usuario generadoPor) {
        if (generadoPor == null) {
            throw new RuntimeException("Debe indicarse quién generó el reporte");
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("La descripción del reporte es obligatoria");
        }
    }

    private static void validarTipo(TipoReporte tipo) {
        if (tipo == null) {
            throw new RuntimeException("El tipo de reporte es obligatorio");
        }
    }
    public boolean tieneDocumentacionValida() {
        return !documentos.isEmpty()
                && documentos.stream().allMatch(Documento::esValido);
    }
    public void adjuntarDocumento(Documento documento) {
        if (documento == null) {
            throw new RuntimeException("El documento no puede ser nulo");
        }

        documentos.add(documento);
    }
    public String generarReporte() {
        return """
                REPORTE DEL PARQUE INDUSTRIAL
                -----------------------------
                ID: %d
                Tipo: %s
                Descripción: %s
                Fecha: %s
                Generado por: %s
                Cantidad de documentos adjuntos: %d
                """.formatted(
                id,
                tipo,
                descripcion,
                fecha,
                usuario.getUserName(),
                documentos.size()
        );
    }
}
