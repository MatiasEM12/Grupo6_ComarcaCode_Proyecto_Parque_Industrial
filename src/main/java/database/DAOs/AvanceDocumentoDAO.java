package database.DAOs;

import model.Documento;

import java.util.List;

public interface AvanceDocumentoDAO {
    void registrarDocumentos(List<Documento> documentos, int id);
}
