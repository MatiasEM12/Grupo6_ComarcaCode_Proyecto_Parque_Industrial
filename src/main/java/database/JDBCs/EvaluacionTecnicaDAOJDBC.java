package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.EvaluacionTecnicaDAO;
import model.EvaluacionTecnica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EvaluacionTecnicaDAOJDBC implements EvaluacionTecnicaDAO {

    @Override
    public void create(int idProyecto, EvaluacionTecnica evaluacion) {

        String sql = """
                INSERT INTO EvaluacionTecnica
                (idProyecto, descripcion, resultado, observaciones)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProyecto);
            ps.setString(2, evaluacion.descripcion());
            ps.setString(3, evaluacion.resultado());
            ps.setString(4, evaluacion.observaciones());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la evaluación técnica", e);
        }
    }
}