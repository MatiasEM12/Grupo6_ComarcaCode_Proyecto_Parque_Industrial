package database.DAOs;
import model.EvaluacionTecnica;

public interface EvaluacionTecnicaDAO {

    void create(int idProyecto, EvaluacionTecnica evaluacion);
}