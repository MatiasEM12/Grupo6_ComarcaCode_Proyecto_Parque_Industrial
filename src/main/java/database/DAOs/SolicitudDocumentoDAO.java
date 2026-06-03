package database.DAOs;

import model.Documento;

import java.util.List;

public interface SolicitudDocumentoDAO {

    void vincular(int idSolicitud, int idDocumento);

    List<Documento> documentosDe(int idSolicitud);
}