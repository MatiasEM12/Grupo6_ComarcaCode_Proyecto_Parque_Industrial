package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.RepresentanteEmpresaDAO;
import model.*;
import model.DTO.AdministradorDelParqueDTO;
import model.DTO.RepresentanteEmpresaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepresentanteEmpresaDAOJDBC implements RepresentanteEmpresaDAO {

    @Override
    public void registrarRepresentante(RepresentanteEmpresa representante) {
        final String SQL =
                "INSERT INTO RepresentanteEmpresa (DNI, userName, cuit_empresa) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, representante.dni());
            st.setString(2, representante.usuario().UserName());
            st.setString(3, representante.empresa().cuit());

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("Error al registrar representante");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar representante", e);
        }
    }

    @Override
    public void update(RepresentanteEmpresa representanteEmpresa) {
        final String SQL =
                "UPDATE RepresentanteEmpresa SET userName = ?, cuit_empresa = ? WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, representanteEmpresa.usuario().UserName());
            st.setString(2, representanteEmpresa.empresa().cuit());
            st.setString(3, representanteEmpresa.dni());

            int fila = st.executeUpdate();
            if (fila <= 0) {
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
            if (fila <= 0) {
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
                        "r.codigo AS rol_codigo, r.nombre AS rol_nombre, " +
                        "e.cuit, e.razonSocial, e.contacto, e.contactoRepresentante, e.radicada " +
                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit " +
                        "WHERE re.DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapearRepresentante(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar representante de empresa", e);
        }
    }

    public RepresentanteEmpresa findByUserName(String userName) {
        final String SQL =
                "SELECT " +
                        "re.DNI, " +
                        "u.codigo, u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, r.nombre AS rol_nombre, " +
                        "e.cuit, e.razonSocial, e.contacto, e.contactoRepresentante, e.radicada " +
                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit " +
                        "WHERE re.userName = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, userName);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapearRepresentante(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar representante por usuario", e);
        }
    }

    @Override
    public boolean existe(String dni) {
        final String SQL = "SELECT 1 FROM RepresentanteEmpresa WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar representante", e);
        }
    }

    @Override
    public List<RepresentanteEmpresa> findAll() {
        final String SQL =
                "SELECT " +
                        "re.DNI, " +
                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS rol_codigo, r.nombre AS rol_nombre, " +
                        "e.cuit, e.razonSocial, e.contacto, e.contactoRepresentante, e.radicada " +
                        "FROM RepresentanteEmpresa re " +
                        "JOIN usuario u ON re.userName = u.userName " +
                        "JOIN roles r ON u.rol = r.codigo " +
                        "LEFT JOIN Empresa e ON re.cuit_empresa = e.cuit";

        List<RepresentanteEmpresa> representantes = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                representantes.add(mapearRepresentante(rs));
            }

            return representantes;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener representantes", e);
        }
    }

    private RepresentanteEmpresa mapearRepresentante(ResultSet rs) throws SQLException {
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
                    cuit,
                    rs.getString("razonSocial"),
                    rs.getString("contacto"),
                    rs.getString("contactoRepresentante"),
                    rs.getBoolean("radicada"),false
            );
        }

        RepresentanteEmpresa representanteEmpresa = new RepresentanteEmpresa(
                rs.getString("DNI"),
                empresa,
                usuario
        );

        empresa.agregarRepresentante(representanteEmpresa);

        return representanteEmpresa;
    }

    @Override
    public void actualizarDatosReprecentante(RepresentanteEmpresaDTO reprecentante) {

        String sql = "UPDATE usuario u JOIN RepresentanteEmpresa r ON u.userName = r.userName " +
                "SET u.gmail = ?, u.contrasena = ?, r.cuit_empresa = ? WHERE r.DNI = ? ";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, reprecentante.getUsuario().getGmail());
            st.setString(2, reprecentante.getUsuario().contrasena());
            st.setString(3, reprecentante.getEmpresa().cuit());
            st.setString(4, reprecentante.getDni());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "no se encontro al reprecemtamte"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("no se pudo actualizar los datos del reprecentante");
        }
    }
}