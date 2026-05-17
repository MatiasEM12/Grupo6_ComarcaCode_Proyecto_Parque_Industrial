package database.JDBCs;

import database.ConnectionManager;
import model.Empresa;
import model.ProyectoProductivo;
import model.RepresentanteEmpresa;
import model.Rol;
import model.SolicitudRadicacion;
import model.Usuario;

import java.lang.Thread.State;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.DAOs.SolicitudRadicacionDAO;

public class SolicitudRadicacionDAOJDBC implements SolicitudRadicacionDAO {


    @Override
    public void create(SolicitudRadicacion solicitudRadicacion) {
        // Implementación del método para crear una solicitud de radicación en la base de datos
        final String SQL = "INSERT INTO SolicitudRadicacion (id, numeroTramite, estadoSolicitud,fechaCreacion," +
            "fechaActualizacion, nombreProyecto, descripcionServicio, cuitEmpresa, idProyecto, dniRepresentante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
            java.sql.PreparedStatement st = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            st.setInt(1, solicitudRadicacion.id());
            st.setString(2, solicitudRadicacion.numeroTramite());
            st.setString(3, solicitudRadicacion.estadoSolicitud().name());
            st.setDate(4, java.sql.Date.valueOf(solicitudRadicacion.fechaCreacion()));
            st.setDate(5, java.sql.Date.valueOf(solicitudRadicacion.fechaActualizacion()));
            st.setString(6, solicitudRadicacion.nombreProyecto());
            st.setString(7, solicitudRadicacion.descripcionServicio());
            st.setString(8, solicitudRadicacion.empresa().cuit());
            st.setInt(9, solicitudRadicacion.proyecto().idProyecto());
            st.setString(10, solicitudRadicacion.representante().dni());

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("Error al registrar la solicitud de radicación");
            }

        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al registrar la solicitud de radicación", e);
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

        } catch (java.sql.SQLException e) {
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

        } catch (java.sql.SQLException e) {
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

        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al encontrar la solicitud de radicación", e);
        }
        return null;
    }

    @Override
    public List<SolicitudRadicacion> findAll() {
        List<SolicitudRadicacion> solicitudes = new ArrayList<>();
        // Implementación del método para obtener todas las solicitudes de radicación en la base de datos
        final String SQL = "SELECT * FROM SolicitudRadicacion";
        try (Connection conn = ConnectionManager.getConnection();
             java.sql.PreparedStatement st = conn.prepareStatement(SQL)) {

            java.sql.ResultSet rs = st.executeQuery();
            while (rs.next()) {
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

                RepresentanteEmpresa representante = new RepresentanteEmpresa(rs.getString("DNI"), empresa, usuario);

                // Crear un objeto SolicitudRadicacion con los datos de cada fila y agregarlo a la lista
                SolicitudRadicacion solicitud = new SolicitudRadicacion(
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
                solicitudes.add(solicitud);
            }

        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al obtener las solicitudes de radicación", e);
        }

        return solicitudes;
    }

    /* @Override
    public int obtenerCantidadSolicitudes() {
        // Implementación del método para obtener la cantidad total de solicitudes de radicación en la base de datos
        return 0;
    } */
}