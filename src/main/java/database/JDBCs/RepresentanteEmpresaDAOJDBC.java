package database.JDBCs;

import database.ConnectionManager;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DAOs.RepresentanteEmpresaDAO;

public class RepresentanteEmpresaDAOJDBC implements RepresentanteEmpresaDAO {
    // tendriamos que combiar de la tabla del reprecentante el nombre por nombre de empresa para que quede claro
    @Override
    public void registrarRepresentante(
            RepresentanteEmpresa representante) {

        final String SQL =
                "INSERT INTO RepresentanteEmpresa " +
                        "(DNI, userName, cuit_empresa) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, representante.dni());
            st.setString(2, representante.usuario().UserName());
            st.setString(3, representante.cuitEmpresa());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "Error al registrar representante"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al registrar representante",
                    e
            );
        }
    }

    @Override
    public void update(RepresentanteEmpresa representanteEmpresa) {
        final String SQL = "UPDATE RepresentanteEmpresa SET nombre = ?, userName = ? WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, representanteEmpresa.nombreEmpresa());
            st.setString(2, representanteEmpresa.usuario().UserName());
            st.setString(3, representanteEmpresa.dni());

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al actualizar representante de empresa");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar representante de empresa", e);
        }
    }

    @Override
    public void remove(String dni) {
        final String SQL = "DELETE FROM RepresentanteEmpresa WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al eliminar representante de empresa");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar representante de empresa", e);
        }
    }

    @Override
    public RepresentanteEmpresa find(String dni) {

        final String SQL =
                "SELECT " +
                        "re.DNI, " +

                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, " +
                        "r.nombre AS rol_nombre, " +

                        "e.cuit, e.razonSocial, e.contacto, " +
                        "e.contactoRepresentante, e.radicada, " +

                        "l.id AS lote_id, l.latitud, l.longitud, " +
                        "l.altitud, l.superficie, l.estado, " +
                        "l.infraestructura " +

                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit " +
                        "LEFT JOIN lotes l ON e.id_lote = l.id " +
                        "WHERE re.DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearRepresentante(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al buscar representante de empresa",
                    e
            );
        }
    }
    public RepresentanteEmpresa findByUserName(String userName) {

        final String SQL =
                "SELECT " +
                        "re.DNI, " +

                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, " +
                        "r.nombre AS rol_nombre, " +

                        "e.cuit, e.razonSocial, e.contacto, " +
                        "e.contactoRepresentante, e.radicada, " +

                        "l.id AS lote_id, l.latitud, l.longitud, " +
                        "l.altitud, l.superficie, l.estado, " +
                        "l.infraestructura " +

                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit " +
                        "LEFT JOIN lotes l ON e.id_lote = l.id " +
                        "WHERE re.userName = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, userName);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearRepresentante(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al buscar representante por usuario",
                    e
            );
        }
    }
    @Override
    public boolean existe(String dni) {

        final String SQL =
                "SELECT 1 FROM RepresentanteEmpresa WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al verificar representante",
                    e
            );
        }
    }
    @Override
    public List<RepresentanteEmpresa> findAll() {

        final String SQL =
                "SELECT " +
                        "re.DNI, " +

                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, " +
                        "r.nombre AS rol_nombre, " +

                        "e.cuit, e.razonSocial, e.contacto, " +
                        "e.contactoRepresentante, e.radicada, " +

                        "l.id AS lote_id, l.latitud, l.longitud, " +
                        "l.altitud, l.superficie, l.estado, " +
                        "l.infraestructura " +

                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit " +
                        "LEFT JOIN lotes l ON e.id_lote = l.id";

        List<RepresentanteEmpresa> representantes =
                new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                representantes.add(mapearRepresentante(rs));
            }

            return representantes;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al obtener representantes",
                    e
            );
        }
    }
    private RepresentanteEmpresa mapearRepresentante(ResultSet rs)
            throws SQLException {

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

        Lote lote = null;

        int idLote = rs.getInt("lote_id");

        if (!rs.wasNull()) {

            Ubicacion ubicacion = new Ubicacion(
                    rs.getLong("latitud"),
                    rs.getLong("longitud"),
                    rs.getLong("altitud")
            );

            lote = new Lote(
                    idLote,
                    ubicacion,
                    rs.getDouble("superficie"),
                    rs.getString("estado"),
                    rs.getString("infraestructura")
            );
        }

        Empresa empresa = null;

        String cuit = rs.getString("cuit");

        if (cuit != null) {
            empresa = new Empresa(
                    cuit,
                    rs.getString("razonSocial"),
                    rs.getString("contacto"),
                    rs.getString("contactoRepresentante"),
                    rs.getBoolean("radicada"),
                    lote
            );
        }

        return new RepresentanteEmpresa(
                rs.getString("DNI"),
                empresa,
                usuario
        );
    }
}