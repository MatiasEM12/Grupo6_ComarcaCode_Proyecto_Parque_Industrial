package database.JDBCs;

import database.DAOs.ProyectoDocumentoDAO;
import database.DAOs.ProyectoProductivoDAO;
import model.Documento;

import java.util.List;

public class ProyectoDocumentoDAOJDBC implements ProyectoDocumentoDAO {
    @Override
    public void registrarDocumentos(int idProyecto, List<Documento> documentos) {

    }

    @Override
    public List<Documento> findAllBy(int idProyecto) {
        return List.of();
    }
}
