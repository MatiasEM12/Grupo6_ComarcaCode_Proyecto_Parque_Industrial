package database.DAOs;

import model.Documento;

import java.util.List;

public interface ProyectoDocumentoDAO {
    void registrarDocumentos(int idProyecto, List<Documento> documentos);

    List<Documento> findAllBy(int idProyecto);
}
