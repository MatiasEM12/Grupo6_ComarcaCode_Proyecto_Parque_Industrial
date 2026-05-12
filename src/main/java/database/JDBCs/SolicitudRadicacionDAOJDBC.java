package database.JDBCs;

import database.ConnectionManager;

import database.DAOs.SolicitudRadicacionDAO;
import model.ProyectoProductivo;
import model.RepresentanteEmpresa;
import model.SolicitudRadicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRadicacionDAOJDBC implements SolicitudRadicacionDAO {

    @Override
    public void create(SolicitudRadicacion solicitudRadicacion) {

        final String SQL = " INSERT INTO SolicitudRadicacion(numero_tramite, estado_solicitud, fecha_creacion, fecha_actualizacion, id_proyecto, dni_representante) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, solicitudRadicacion.numeroTramite());

            st.setString(2, solicitudRadicacion.estadoSolicitud().name());

            st.setDate(3, java.sql.Date.valueOf(solicitudRadicacion.fechaCreacion()));

            st.setDate(4, java.sql.Date.valueOf(solicitudRadicacion.fechaActualizacion()));

            st.setInt(5, solicitudRadicacion.proyectoProductivo().id());

            st.setString(6, solicitudRadicacion.representante().dni());

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException(
                        "Error al registrar solicitud"
                );
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al registrar solicitud",e
            );
        }
    }

    @Override
    public void update(SolicitudRadicacion solicitudRadicacion) {

        final String SQL = "UPDATE SolicitudRadicacion SET numero_tramite = ?, estado_solicitud = ?, fecha_actualizacion = ?, id_proyecto = ?, dni_representante = ? WHERE id = ? ";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setString(1, solicitudRadicacion.numeroTramite());

            st.setString(2, solicitudRadicacion.estadoSolicitud().name());

            st.setDate(3, java.sql.Date.valueOf(solicitudRadicacion.fechaActualizacion()));

            st.setInt(4, solicitudRadicacion.proyectoProductivo().id());

            st.setString(5, solicitudRadicacion.representante().dni());

            st.setInt(6, solicitudRadicacion.id());

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException(
                        "Error al actualizar solicitud"
                );
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al actualizar solicitud",
                    e
            );
        }
    }

    @Override
    public void remove(Integer id) {

        final String SQL = "DELETE FROM SolicitudRadicacion WHERE id = ? ";

        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setInt(1, id);

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException(
                        "Error al eliminar solicitud"
                );
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al eliminar solicitud",
                    e
            );
        }
    }

    @Override
    public void remove(SolicitudRadicacion solicitudRadicacion) {
        remove(solicitudRadicacion.id());
    }

    @Override
    public SolicitudRadicacion find(Integer id) {

        final String SQL = "SELECT * FROM SolicitudRadicacion WHERE id = ? ";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setInt(1, id);

            var rs = st.executeQuery();

            if (rs.next()) {

                RepresentanteEmpresa representante = new ReprecentanteEmpresaDAOJDBC().find(rs.getString("dni_representante"));

                ProyectoProductivo proyecto = new ProyectoProductivoDAOJDBC().find(rs.getInt("id_proyecto"));

                return new SolicitudRadicacion(

                        rs.getInt("id"),
                        rs.getString("numero_tramite"),
                        representante,
                        proyecto,
                        rs.getDate("fecha_creacion").toLocalDate(),
                        rs.getDate("fecha_actualizacion").toLocalDate(),
                        rs.getString("estado_solicitud")
                );
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al buscar solicitud",
                    e
            );
        }
    }

    @Override
    public List<SolicitudRadicacion> findAll() {

        final String SQL = " SELECT * FROM SolicitudRadicacion ";

        List<SolicitudRadicacion> solicitudes =
                new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            var rs = st.executeQuery();

            while (rs.next()) {

                RepresentanteEmpresa representante = new ReprecentanteEmpresaDAOJDBC().find(rs.getString("dni_representante"));

                ProyectoProductivo proyecto = new ProyectoProductivoDAOJDBC().find(rs.getInt("id_proyecto"));

                SolicitudRadicacion solicitud =
                        new SolicitudRadicacion(

                                rs.getInt("id"),
                                rs.getString("numero_tramite"),
                                representante,
                                proyecto,
                                rs.getDate("fecha_creacion").toLocalDate(),
                                rs.getDate("fecha_actualizacion").toLocalDate(),
                                rs.getString("estado_solicitud")
                        );

                solicitudes.add(solicitud);
            }

            return solicitudes;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al obtener solicitudes",
                    e
            );
        }
    }
}