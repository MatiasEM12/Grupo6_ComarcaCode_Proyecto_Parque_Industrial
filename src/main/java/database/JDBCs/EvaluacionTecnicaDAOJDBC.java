package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.EvaluacionTecnicaDAO;
import model.DTO.EvaluacionTecnicaDTO;
import model.EvaluacionTecnica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<EvaluacionTecnicaDTO> findAll() {

        List<EvaluacionTecnicaDTO> evaluaciones = new ArrayList<>();

        String sql = """
            SELECT 
                e.id,
                e.idProyecto,
                p.nombre AS nombreProyecto,
                e.descripcion,
                e.resultado,
                e.observaciones,
                e.fecha
            FROM EvaluacionTecnica e
            INNER JOIN ProyectoProductivo p
                ON e.idProyecto = p.idProyecto
            ORDER BY e.fecha DESC
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                evaluaciones.add(new EvaluacionTecnicaDTO(
                        rs.getInt("id"),
                        rs.getInt("idProyecto"),
                        rs.getString("nombreProyecto"),
                        rs.getString("descripcion"),
                        rs.getString("resultado"),
                        rs.getString("observaciones"),
                        rs.getTimestamp("fecha").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener evaluaciones técnicas", e);
        }

        return evaluaciones;
    }
    @Override
    public List<EvaluacionTecnicaDTO> findByProyecto(int idProyecto) {

        List<EvaluacionTecnicaDTO> evaluaciones = new ArrayList<>();

        String sql = """
            SELECT 
                e.id,
                e.idProyecto,
                p.nombre AS nombreProyecto,
                e.descripcion,
                e.resultado,
                e.observaciones,
                e.fecha
            FROM EvaluacionTecnica e
            INNER JOIN ProyectoProductivo p 
                ON e.idProyecto = p.idProyecto
            WHERE e.idProyecto = ?
            ORDER BY e.fecha DESC
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProyecto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    evaluaciones.add(new EvaluacionTecnicaDTO(
                            rs.getInt("id"),
                            rs.getInt("idProyecto"),
                            rs.getString("nombreProyecto"),
                            rs.getString("descripcion"),
                            rs.getString("resultado"),
                            rs.getString("observaciones"),
                            rs.getTimestamp("fecha").toLocalDateTime()
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener evaluaciones del proyecto", e);
        }

        return evaluaciones;
    }
    @Override
    public EvaluacionTecnicaDTO findById(int idEvaluacion) {

        String sql = """
            SELECT 
                e.id,
                e.idProyecto,
                p.nombre AS nombreProyecto,
                e.descripcion,
                e.resultado,
                e.observaciones,
                e.fecha
            FROM EvaluacionTecnica e
            INNER JOIN ProyectoProductivo p 
                ON e.idProyecto = p.idProyecto
            WHERE e.id = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvaluacion);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new EvaluacionTecnicaDTO(
                            rs.getInt("id"),
                            rs.getInt("idProyecto"),
                            rs.getString("nombreProyecto"),
                            rs.getString("descripcion"),
                            rs.getString("resultado"),
                            rs.getString("observaciones"),
                            rs.getTimestamp("fecha").toLocalDateTime()
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener evaluación técnica", e);
        }

        return null;
    }
}