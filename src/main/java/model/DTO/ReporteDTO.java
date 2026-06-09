package model.DTO;

import java.time.LocalDate;

import java.util.List;

import model.Usuario;
import model.Documento;
import model.TipoReporte;

public record ReporteDTO(
    int id,
    TipoReporte tipo,
    String descripcion,
    LocalDate fecha,
    Usuario usuario,
    List<Documento> documentosAdjuntos
)
{
    public ReporteDTO(TipoReporte tipo, String descripcion, LocalDate fecha, Usuario usuario, List<Documento> documentosAdjuntos) {
        this(0, tipo, descripcion, fecha, usuario, documentosAdjuntos);
    }

    public int cantidadDocumentosAdjuntos() {
        return documentosAdjuntos != null ? documentosAdjuntos.size() : 0;
    }
}
