package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ProyectoProductivoDAO;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoProductivoDAOJDBC implements ProyectoProductivoDAO {

    @Override
    public void registrarProyectoProductivo(
            ProyectoProductivo proyectoProductivo) {

        final String SQL =
                "INSERT INTO ProyectoProductivo (" +
                        "nombre, descripcion, superficie, " +
                        "necesidades, empleabilidad, materiaPrima, " +
                        "estado, cuit_empresa, id_lote" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement st =
                     conn.prepareStatement(SQL)) {

            st.setString(1, proyectoProductivo.nombre());

            st.setString(2, proyectoProductivo.descripcion());

            st.setDouble(3, proyectoProductivo.superficie());

            st.setString(4, proyectoProductivo.necesidades());

            st.setInt(5, proyectoProductivo.empleabilidad());

            st.setString(6, proyectoProductivo.materiaPrima());

            st.setBoolean(7, proyectoProductivo.enEjecucion());

            st.setString(
                    8,
                    proyectoProductivo.empresa().cuit()
            );

            st.setInt(
                    9,
                    proyectoProductivo.lote().id()
            );

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "Error al registrar proyecto productivo"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar proyecto productivo", e);
        }
    }

    @Override
    public ProyectoProductivo find(int idProyecto) {

        final String SQL =
                "SELECT p.*, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada, " +

                        "l.id AS lote_id, " +
                        "l.latitud, l.longitud, l.altitud, " +
                        "l.superficie AS lote_superficie, " +
                        "l.estado AS lote_estado, " +
                        "l.infraestructura " +

                        "FROM ProyectoProductivo p " +

                        "LEFT JOIN Empresa e " +
                        "ON p.cuit_empresa = e.cuit " +

                        "LEFT JOIN lotes l " +
                        "ON p.id_lote = l.id " +

                        "WHERE p.idProyecto = ?";

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement st =
                     conn.prepareStatement(SQL)) {

            st.setInt(1, idProyecto);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearProyecto(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyecto productivo", e);
        }
    }

    @Override
    public List<ProyectoProductivo> findAll() {

        final String SQL =
                "SELECT p.*, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada, " +

                        "l.id AS lote_id, " +
                        "l.latitud, l.longitud, l.altitud, " +
                        "l.superficie AS lote_superficie, " +
                        "l.estado AS lote_estado, " +
                        "l.infraestructura " +

                        "FROM ProyectoProductivo p " +

                        "LEFT JOIN Empresa e " +
                        "ON p.cuit_empresa = e.cuit " +

                        "LEFT JOIN lotes l " +
                        "ON p.id_lote = l.id";

        List<ProyectoProductivo> proyectos =
                new ArrayList<>();

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement st =
                     conn.prepareStatement(SQL);

             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                proyectos.add(mapearProyecto(rs));
            }

            return proyectos;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos productivos", e);
        }
    }

    @Override
    public List<ProyectoProductivo> findByEmpresa(
            String cuitEmpresa) {

        final String SQL =
                "SELECT p.*, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada, " +

                        "l.id AS lote_id, " +
                        "l.latitud, l.longitud, l.altitud, " +
                        "l.superficie AS lote_superficie, " +
                        "l.estado AS lote_estado, " +
                        "l.infraestructura " +

                        "FROM ProyectoProductivo p " +

                        "LEFT JOIN Empresa e " +
                        "ON p.cuit_empresa = e.cuit " +

                        "LEFT JOIN lotes l " +
                        "ON p.id_lote = l.id " +

                        "WHERE p.cuit_empresa = ?";

        List<ProyectoProductivo> proyectos =
                new ArrayList<>();

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement st =
                     conn.prepareStatement(SQL)) {

            st.setString(1, cuitEmpresa);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                proyectos.add(mapearProyecto(rs));
            }

            return proyectos;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos por empresa", e);
        }
    }

    @Override
    public void actualizarEstado(
            int idProyecto,
            boolean estado) {

        final String SQL =
                "UPDATE ProyectoProductivo " +
                        "SET estado = ? " +
                        "WHERE idProyecto = ?";

        try (Connection conn =
                     ConnectionManager.getConnection();

             PreparedStatement st =
                     conn.prepareStatement(SQL)) {

            st.setBoolean(1, estado);

            st.setInt(2, idProyecto);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró el proyecto");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estado", e);
        }
    }

    @Override
    public void actualizarEstadoProyecto(int idProyecto, EstadoProyecto estado) {

        final String SQL = "UPDATE ProyectoProductivo " +
                        "SET estadoProyecto = ? " +
                        "WHERE idProyecto = ?";

        try (Connection conn = ConnectionManager.getConnection();

             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, estado.name());

            st.setInt(2, idProyecto);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró el proyecto");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estado del proyecto", e);
        }
    }

    private ProyectoProductivo mapearProyecto(ResultSet rs)
            throws SQLException {

        Empresa empresa = null;

        String cuit = rs.getString("cuit");

        if (cuit != null) {

            empresa = new Empresa(
                    rs.getString("cuit"),
                    rs.getString("razonSocial"),
                    rs.getString("contacto"),
                    rs.getString("contactoRepresentante"),
                    rs.getBoolean("radicada")
            );
        }

        Ubicacion ubicacion = new Ubicacion(
                rs.getLong("latitud"),
                rs.getLong("longitud"),
                rs.getLong("altitud")
        );

        Lote lote = new Lote(
                rs.getInt("lote_id"),
                ubicacion,
                rs.getDouble("lote_superficie"),
                rs.getString("lote_estado"),
                rs.getString("infraestructura")
        );

        return new ProyectoProductivo(
                rs.getInt("idProyecto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("superficie"),
                rs.getString("necesidades"),
                rs.getInt("empleabilidad"),
                rs.getString("materiaPrima"),
                rs.getBoolean("estado"),
                empresa,
                lote
        );
    }
}