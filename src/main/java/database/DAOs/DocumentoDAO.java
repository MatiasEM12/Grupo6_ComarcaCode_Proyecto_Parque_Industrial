package database.DAOs;

import model.Documento;

import java.util.List;

public interface DocumentoDAO {

    void create(Documento documento, int idReporte);

    Documento find(int id);

    List<Documento> findAll();
    
    List<Documento> findByReporteId(int reporteId);

    void update(Documento documento);

    void remove(int id);

    Documento findPorRuta(String ruta);
}