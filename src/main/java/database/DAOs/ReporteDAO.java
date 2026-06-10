package database.DAOs;

import model.Reporte;

import java.util.List;

public interface ReporteDAO {

    void guardar(Reporte reporte);

    List<Reporte> findAll();

    Reporte findById(int id);

    void vincularDocumento(int idReporte, int idDocumento);
}
