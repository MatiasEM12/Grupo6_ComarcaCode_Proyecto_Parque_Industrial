package database.DAOs;

import model.EvaluacionTecnica;
import model.DTO.EvaluacionTecnicaDTO;

import java.util.List;

public interface EvaluacionTecnicaDAO {

    void create(int idProyecto, EvaluacionTecnica evaluacion);

    List<EvaluacionTecnicaDTO> findAll();
    EvaluacionTecnicaDTO findById(int idEvaluacion);

    List<EvaluacionTecnicaDTO> findByProyecto(int idProyecto);
}