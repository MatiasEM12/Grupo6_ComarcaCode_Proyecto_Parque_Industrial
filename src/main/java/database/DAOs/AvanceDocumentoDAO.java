package database.DAOs;

import model.Documento;

import java.util.List;

public interface AvanceDocumentoDAO {
    void vincular(int idAvance, int idDocumento);

    List<Documento> documentosDe(int idAvance);
}
