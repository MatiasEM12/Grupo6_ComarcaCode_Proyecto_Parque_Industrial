package database.DAOs;

import model.DTO.ReporteDTO;

public interface ReporteDAO {

    int generarReporte(ReporteDTO reporte);

    ReporteDTO find(int id);

}
