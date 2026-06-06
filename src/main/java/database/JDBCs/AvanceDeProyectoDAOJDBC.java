package database.JDBCs;


import database.ConnectionManager;
import database.DAOs.AvanceDeProyectoDAO;
import database.DAOs.AvanceDocumentoDAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvanceDeProyectoDAOJDBC implements AvanceDeProyectoDAO {

    @Override
    public void create(AvanceDeProyecto avance) {

        final String SQL =
                "INSERT INTO AvanceProyecto " +
                        "(idProyecto, fechaCreacion, descripcion, estado) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, avance.proyectoProductivo().idProyecto());

            st.setDate(2, Date.valueOf(avance.fechaCreacion()));

            st.setString(3, avance.descripcion());

            st.setString(4, avance.estado().name());

            st.executeUpdate();

        } catch (Exception e) {

            throw new RuntimeException("Error al registrar avance", e);
        }
    }

    @Override
    public void actualizarEstado(int idAvance, EstadoProyecto estado) {

        final String SQL = "UPDATE AvanceProyecto " +
                        "SET estado = ? " +
                        "WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, estado.name());

            st.setInt(2, idAvance);

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException("No se encontró el avance con id " + idAvance);
            }

        } catch (Exception e) {

            throw new RuntimeException("Error al actualizar estado del avance", e);
        }
    }

    @Override
    public AvanceDeProyecto find(int id) {

        final String SQL =
                "SELECT ap.*, " +

                        "p.*, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada, " +

                        "l.id AS lote_id, " +
                        "l.latitud, l.longitud, l.altitud, " +
                        "l.superficie AS lote_superficie, " +
                        "l.estado AS lote_estado, " +
                        "l.infraestructura " +

                        "FROM AvanceProyecto ap " +

                        "JOIN ProyectoProductivo p " +
                        "ON ap.idProyecto = p.idProyecto " +

                        "LEFT JOIN Empresa e " +
                        "ON p.cuit_empresa = e.cuit " +

                        "LEFT JOIN lotes l " +
                        "ON p.id_lote = l.id " +

                        "WHERE ap.id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearAvance(rs);
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException("Error al obtener avance", e);
        }
    }

    @Override
    public List<AvanceDeProyecto> findAllBy(int idProyecto) {

        final String SQL =
                "SELECT ap.*, " +

                        "p.*, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada, " +

                        "l.id AS lote_id, " +
                        "l.latitud, l.longitud, l.altitud, " +
                        "l.superficie AS lote_superficie, " +
                        "l.estado AS lote_estado, " +
                        "l.infraestructura " +

                        "FROM AvanceProyecto ap " +

                        "JOIN ProyectoProductivo p " +
                        "ON ap.idProyecto = p.idProyecto " +

                        "LEFT JOIN Empresa e " +
                        "ON p.cuit_empresa = e.cuit " +

                        "LEFT JOIN lotes l " +
                        "ON p.id_lote = l.id " +

                        "WHERE ap.idProyecto = ? " +
                        "ORDER BY ap.fechaCreacion DESC";

        List<AvanceDeProyecto> avances = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, idProyecto);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                avances.add(
                        mapearAvance(rs)
                );
            }

            return avances;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al obtener avances",
                    e
            );
        }
    }

    private AvanceDeProyecto mapearAvance(ResultSet rs)
            throws SQLException {


        AvanceDocumentoDAO documentoDAO = new AvanceDocumentoDAOJDBC();
        AvanceDeProyecto avance = new AvanceDeProyecto(
                rs.getInt("id"),
                new ProyectoProductivoDAOJDBC().find(rs.getInt("idProyecto")),
                rs.getDate("fechaCreacion").toLocalDate(),
                documentoDAO.documentosDe(rs.getInt("id")),
                rs.getString("descripcion"),
                EstadoProyecto.valueOf(rs.getString("estado")
                )
        );

        return avance;
    }

}