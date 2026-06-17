package database.JDBCs;

import database.DAOs.ObservacionesDAO;
import model.DTO.ObservacionDTO;
import database.ConnectionManager;
import database.DAOs.LoteDAO;
import model.DTO.LoteDTO;
import model.Lote;
import model.Observacion;
import model.Ubicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.util.List;

public class ObservacionesDAOJDBC implements ObservacionesDAO {
    @Override
    public void crear(Observacion observacion) {

        final String SQL =
                "INSERT INTO observacionsolicitud " +
                        "(id_solicitud, dni_administrador, observacion, fecha_creacion) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, observacion.idSolicitud());
            st.setString(2, observacion.dniAdministrador());
            st.setString(3, observacion.observacion());
            st.setTimestamp(
                    4,
                    Timestamp.valueOf(observacion.fechaCreacion())
            );

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException(
                        "No se pudo registrar la observación"
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al registrar observación",
                    e
            );
        }
    }

    @Override
    public List<Observacion> buscarPorSolicitud(int idSolicitud) {
        List<Observacion> observaciones = new ArrayList<>();

        final String SQL = "SELECT * FROM observacionsolicitud WHERE id_solicitud = ? ORDER BY fecha_creacion DESC";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setInt(1, idSolicitud);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                observaciones.add(new Observacion(
                                rs.getInt("id"),
                                rs.getInt("id_solicitud"),
                                rs.getString("observacion"),
                                rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                                rs.getString("dni_administrador")));
            }
            return observaciones;
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar observaciones", e);
        }
    }
}
