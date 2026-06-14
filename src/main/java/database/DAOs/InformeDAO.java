package database.DAOs;

import model.Documento;
import model.Informe;

import java.util.List;

public interface InformeDAO {

    void guardar(Informe informe);

    List<Informe> findAll();

    Informe findById(int id);

    void vincularDocumento(int idReporte, int idDocumento);

    Documento obtenerDocumento(int idInforme);
}
