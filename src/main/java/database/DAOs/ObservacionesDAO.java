package database.DAOs;

import model.DTO.ObservacionDTO;
import model.DTO.ProyectoProductivoDTO;

import java.util.List;

public interface ObservacionesDAO {
    void crear(ObservacionDTO observacion);
    List<ObservacionDTO> buscarPorSolicitud(int idSolicitud);
}
