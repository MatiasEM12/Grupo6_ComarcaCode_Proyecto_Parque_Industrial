package database.DAOs;

import model.DTO.ObservacionDTO;
import model.DTO.ProyectoProductivoDTO;
import model.Observacion;

import java.util.List;

public interface ObservacionesDAO {
    void crear(Observacion observacion);
    List<Observacion> buscarPorSolicitud(int idSolicitud);
}
