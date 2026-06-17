package database.JDBCs;

import database.ConnectionManager;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DAOs.SolicitudRadicacionDAO;

public class SolicitudRadicacionDAOJDBC implements SolicitudRadicacionDAO {


    @Override
    public void create(SolicitudRadicacion solicitud) {

        final String SQL =
                "INSERT INTO SolicitudRadicacion (" +
                        "id, numeroTramite, estadoSolicitud, fechaCreacion, fechaActualizacion, " +
                        "dniRepresentante, objeto, nombreProyecto, descripcionServicio, emplazamiento, " +
                        "personal, tiempoRadicacion, m2, areaTrabajo, areaDeposito, estacionamiento, " +
                        "planos, empleabilidad, materiasPrimas, destinoProduccion, tension, potencia, " +
                        "agua, gas, residuos, tratamiento, balanza, comedor, coworking " +

                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, solicitud.id());
            st.setString(2, solicitud.numeroTramite());
            st.setString(3, solicitud.estadoSolicitud().name());
            st.setDate(4, java.sql.Date.valueOf(solicitud.fechaCreacion()));
            st.setDate(5, java.sql.Date.valueOf(solicitud.fechaActualizacion()));

            st.setString(6, solicitud.representante().dni());
            st.setString(7, solicitud.objeto());
            st.setString(8, solicitud.nombreProyecto());
            st.setString(9, solicitud.descripcionServicio());
            st.setString(10, solicitud.emplazamiento());
            st.setString(11, solicitud.personal());
            st.setString(12, solicitud.tiempoRadicacion());
            st.setString(13, solicitud.m2());
            st.setString(14, solicitud.areaTrabajo());
            st.setString(15, solicitud.areaDeposito());
            st.setString(16, solicitud.estacionamiento());
            st.setString(17, solicitud.planos());
            st.setString(18, solicitud.empleabilidad());
            st.setString(19, solicitud.materiasPrimas());
            st.setString(20, solicitud.destinoProduccion());
            st.setString(21, solicitud.tension());
            st.setString(22, solicitud.potencia());
            st.setString(23, solicitud.agua());
            st.setString(24, solicitud.gas());
            st.setString(25, solicitud.residuos());
            st.setString(26, solicitud.tratamiento());
            st.setString(27, solicitud.balanza());
            st.setString(28, solicitud.comedor());
            st.setString(29, solicitud.coworking());


            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("Error al registrar la solicitud");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar la solicitud", e);
        }
    }

    @Override
    public void update(SolicitudRadicacion solicitudRadicacion) {

        final String SQL =
                "UPDATE SolicitudRadicacion " +
                        "SET estadoSolicitud = ?, fechaActualizacion = ? " +
                        "WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, solicitudRadicacion.estadoSolicitud().name());

            st.setDate(2, java.sql.Date.valueOf(solicitudRadicacion.fechaActualizacion()));

            st.setInt(3, solicitudRadicacion.id());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "Error al actualizar estado de la solicitud"
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al actualizar estado de la solicitud",
                    e
            );
        }
    }

    @Override
    public void remove(Integer id) {
        // Implementación del método para eliminar una solicitud de radicación por su ID en la base de datos
        final String SQL = "DELETE FROM SolicitudRadicacion WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, id);

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("Error al eliminar la solicitud de radicación");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la solicitud de radicación", e);
        }
    }

    @Override
    public void remove(SolicitudRadicacion solicitudRadicacion) {
        // Implementación del método para eliminar una solicitud de radicación utilizando el objeto en la base de datos
        remove(solicitudRadicacion.id());
    }

    @Override
    public SolicitudRadicacion find(Integer id) {

        final String SQL =
                "SELECT s.*, " +

                        "re.DNI AS representante_dni, " +

                        "u.codigo,u.userName, u.contrasena, u.gmail, " +

                        "r.codigo AS rol_codigo, " +
                        "r.nombre AS rol_nombre, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada " +

                        "FROM SolicitudRadicacion s " +

                        "JOIN RepresentanteEmpresa re " +
                        "ON s.dniRepresentante = re.DNI " +

                        "JOIN usuario u " +
                        "ON re.userName = u.userName " +

                        "JOIN roles r " +
                        "ON u.rol = r.codigo " +

                        "LEFT JOIN Empresa e " +
                        "ON re.cuit_empresa = e.cuit " +

                        "WHERE s.id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearSolicitud(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al buscar solicitud",
                    e
            );
        }
    }

    @Override
    public List<SolicitudRadicacion> findAll() {

        List<SolicitudRadicacion> solicitudes =
                new ArrayList<>();

        final String SQL =
                "SELECT s.*, " +

                        "re.DNI AS representante_dni, " +

                        "u.codigo,u.userName, u.contrasena, u.gmail, " +

                        "r.codigo AS rol_codigo, " +
                        "r.nombre AS rol_nombre, " +

                        "e.cuit, e.razonSocial, " +
                        "e.contacto, e.contactoRepresentante, " +
                        "e.radicada " +

                        "FROM SolicitudRadicacion s " +

                        "JOIN RepresentanteEmpresa re " +
                        "ON s.dniRepresentante = re.DNI " +

                        "JOIN usuario u " +
                        "ON re.userName = u.userName " +

                        "JOIN roles r " +
                        "ON u.rol = r.codigo " +

                        "LEFT JOIN Empresa e " +
                        "ON re.cuit_empresa = e.cuit";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                solicitudes.add(mapearSolicitud(rs));
            }

            return solicitudes;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al obtener solicitudes",
                    e
            );
        }
    }

    private SolicitudRadicacion mapearSolicitud(ResultSet rs)
            throws SQLException {

        Rol rol = new Rol(
                rs.getString("rol_nombre"),
                rs.getInt("rol_codigo")
        );

        Usuario usuario = new Usuario(
                rs.getInt("codigo"),
                rs.getString("userName"),
                rs.getString("contrasena"),
                rol,
                rs.getString("gmail")
        );

        Empresa empresa = null;

        String cuit = rs.getString("cuit");

        if (cuit != null) {

            empresa = new Empresa(
                    rs.getString("cuit"),
                    rs.getString("razonSocial"),
                    rs.getString("contacto"),
                    rs.getString("contactoRepresentante"),
                    rs.getBoolean("radicada"),false
            );
        }

        RepresentanteEmpresa representante =
                new RepresentanteEmpresa(
                        rs.getString("representante_dni"),
                        empresa,
                        usuario
                );

        SolicitudRadicacion solicitud = new SolicitudRadicacion(

                rs.getInt("id"),

                rs.getString("numeroTramite"),

                rs.getString("estadoSolicitud"),

                rs.getDate("fechaCreacion").toLocalDate(),

                rs.getDate("fechaActualizacion").toLocalDate(),

                representante,

                rs.getString("objeto"),

                rs.getString("nombreProyecto"),

                rs.getString("descripcionServicio"),

                rs.getString("emplazamiento"),

                rs.getString("personal"),

                rs.getString("tiempoRadicacion"),

                rs.getString("m2"),

                rs.getString("areaTrabajo"),

                rs.getString("areaDeposito"),

                rs.getString("estacionamiento"),

                rs.getString("planos"),

                rs.getString("empleabilidad"),

                rs.getString("materiasPrimas"),

                rs.getString("destinoProduccion"),

                rs.getString("tension"),

                rs.getString("potencia"),

                rs.getString("agua"),

                rs.getString("gas"),

                rs.getString("residuos"),

                rs.getString("tratamiento"),

                rs.getString("balanza"),

                rs.getString("comedor"),

                rs.getString("coworking")
        );

        solicitud.setDocumentos(new SolicitudDocumentoDAOJDBC().documentosDe(solicitud.id()));
        String nombreProyecto = rs.getString("nombreProyecto");

        if(nombreProyecto != null && !nombreProyecto.isBlank()){

            ProyectoProductivo proyecto = new ProyectoProductivoDAOJDBC().findByNombre(nombreProyecto);

            if(proyecto != null){
                solicitud.setProyectoProductivo(proyecto);
            }
        }

        return solicitud;

    }

    @Override
    public void estadoSolicitud(int idSolicitud, EstadoSolicitud estado){
        final String SQL = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = ?, fechaActualizacion = ? " +
                "WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, String.valueOf(estado));
            st.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            st.setInt(3, idSolicitud);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "no se encontro la solicitud"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al actualizar el estado de la solicitud", e
            );
        }
    }
}



