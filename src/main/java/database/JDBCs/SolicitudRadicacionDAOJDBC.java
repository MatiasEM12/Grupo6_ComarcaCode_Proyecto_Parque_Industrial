package database.JDBCs;

import database.ConnectionManager;
import model.Empresa;
import model.ProyectoProductivo;
import model.RepresentanteEmpresa;
import model.Rol;
import model.SolicitudRadicacion;
import model.Usuario;

import java.sql.Connection;
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
                        "agua, gas, residuos, tratamiento, balanza, comedor, coworking, " +
                        "descripcionArchivo, nombreArchivoPDF" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            st.setString(30, solicitud.descripcionArchivo());
            st.setString(31, solicitud.nombreArchivoPDF());

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
        // Implementación del método para actualizar una solicitud de radicación en la base de datos
        final String SQL = "UPDATE SolicitudRadicacion SET numeroTramite = ?, estadoSolicitud = ?, fechaActualizacion = ?," +
                "nombreProyecto = ?, descripcionServicio = ?, cuitEmpresa = ?, idProyecto = ?, dniRepresentante = ? WHERE id = ?";
                
        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, solicitudRadicacion.numeroTramite());
            st.setString(2, solicitudRadicacion.estadoSolicitud().name());
            st.setDate(3, java.sql.Date.valueOf(solicitudRadicacion.fechaActualizacion()));
            st.setString(4, solicitudRadicacion.nombreProyecto());
            st.setString(5, solicitudRadicacion.descripcionServicio());
            st.setString(6, solicitudRadicacion.empresa().cuit());
            st.setInt(7, solicitudRadicacion.proyecto().idProyecto());
            st.setString(8, solicitudRadicacion.representante().dni());
            st.setInt(9, solicitudRadicacion.id());

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("Error al actualizar la solicitud de radicación");
            }

        } catch (Exception e){
            throw new RuntimeException("Error al actualizar la solicitud de radicación", e);
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
        // Implementación del método para encontrar una solicitud de radicación por su ID en la base de datos
        final String SQL = "SELECT * FROM SolicitudRadicacion s JOIN Empresa e ON s.cuitEmpresa = e.cuit" +
        " JOIN Proyecto p ON s.idProyecto = p.idProyecto" +
        " JOIN RepresentanteEmpresa r ON s.dniRepresentante = r.DNI" +
        " WHERE s.id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, id);

            java.sql.ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // Crear y retornar un objeto SolicitudRadicacion con los datos de la fila
                Rol rol = new Rol(rs.getString("nombre"), rs.getInt("codigo"));

                Usuario usuario = new Usuario(rs.getString("userName"), rs.getString("contrasena"),
                 rol, rs.getString("gmail"));

                Empresa empresa = new Empresa(rs.getString("cuit"), rs.getString("razon_Social"), rs.getString("contacto"),
                        rs.getString("contacto_Representante"), rs.getBoolean("radicada"),
                        null,null);
                ProyectoProductivo proyecto = new ProyectoProductivo(rs.getString("nombreProyecto"),
                     rs.getString("descripcionProyecto"), rs.getDouble("superficie"),
                      rs.getString("necesidades"), rs.getInt("empleabilidad"),
                       rs.getString("materiaPrima"), empresa);

                //buscar la empresa  empresa= eempresaDao.find(rs.getString("nombreEmpresa"))
                RepresentanteEmpresa representante = new RepresentanteEmpresa(rs.getString("DNI"), empresa, usuario);

                return new SolicitudRadicacion(
                        // ... (inicialización de propiedades)
                        rs.getInt("id"),
                        rs.getString("numeroTramite"),
                        rs.getString("estadoSolicitud"),
                        rs.getDate("fechaCreacion").toLocalDate(),
                        rs.getDate("fechaActualizacion").toLocalDate(),
                        rs.getString("nombreProyecto"),
                        rs.getString("descripcionServicio"),
                        proyecto,
                        empresa,
                        representante
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar la solicitud de radicación", e);
        }
        return null;
    }

    @Override
    public List<SolicitudRadicacion> findAll() {

        List<SolicitudRadicacion> solicitudes = new ArrayList<>();

        final String SQL =
                "SELECT s.*, " +
                        "re.DNI AS representante_dni, " +
                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, r.nombre AS rol_nombre, " +
                        "e.cuit, e.razonSocial, e.contacto, e.contactoRepresentante, e.radicada " +
                        "FROM SolicitudRadicacion s " +
                        "JOIN RepresentanteEmpresa re ON s.dniRepresentante = re.DNI " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON e.dni_representante = re.DNI";

        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL);
             java.sql.ResultSet rs = st.executeQuery()) {

            while (rs.next()) {

                Rol rol = new Rol(
                        rs.getString("rol_nombre"),
                        rs.getInt("rol_codigo")
                );

                Usuario usuario = new Usuario(
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
                            rs.getBoolean("radicada"),
                            null
                    );
                }

                RepresentanteEmpresa representante =
                        new RepresentanteEmpresa(
                                rs.getString("representante_dni"),
                                empresa,
                                usuario
                        );

                SolicitudRadicacion solicitud =
                        new SolicitudRadicacion(
                                rs.getInt("id"),
                                rs.getString("numeroTramite"),
                                rs.getString("estadoSolicitud"),
                                rs.getDate("fechaCreacion").toLocalDate(),
                                rs.getDate("fechaActualizacion").toLocalDate(),
                                rs.getString("nombreProyecto"),
                                rs.getString("descripcionServicio"),
                                null,
                                empresa,
                                representante
                        );

                solicitudes.add(solicitud);
            }

            return solicitudes;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al obtener las solicitudes de radicación",
                    e
            );
        }
    }

    /* @Override
    public int obtenerCantidadSolicitudes() {
        // Implementación del método para obtener la cantidad total de solicitudes de radicación en la base de datos
        return 0;
    } */
}