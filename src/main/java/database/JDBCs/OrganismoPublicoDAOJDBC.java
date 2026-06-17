package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.OrganismoPublicoDAO;
import model.OrganismoPublico;
import model.Rol;
import model.TipoOrganismo;
import model.DTO.OrganismoPublicoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrganismoPublicoDAOJDBC implements OrganismoPublicoDAO {

    @Override
    public void registrarOrganismoPublico(OrganismoPublico organismoPublico) {
        final String SQL = "INSERT INTO organismopublico(saf, nombre, tipoOrganismo, userName) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, organismoPublico.saf());
            st.setString(2, organismoPublico.nombre());
            st.setString(3, organismoPublico.tipoOrganismo().name());
            st.setString(4, organismoPublico.usuario());

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("Error al registrar organismo público");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar organismo público", e);
        }
    }

    @Override
    public OrganismoPublico obtenerOrganismoPorUsername(String username) {
        final String SQL = """
                SELECT u.codigo AS codigoUsuario,
                       u.userName,
                       u.contrasena,
                       u.gmail,
                       r.codigo AS codigoRol,
                       r.nombre AS rolNombre,
                       o.saf,
                       o.nombre,
                       o.tipoOrganismo
                FROM organismopublico o
                INNER JOIN usuario u ON o.userName = u.userName
                INNER JOIN roles r ON u.rol = r.codigo
                WHERE u.userName = ?
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, username);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol(
                            rs.getString("rolNombre"),
                            rs.getInt("codigoRol")
                    );

                    return new OrganismoPublico(
                            rs.getString("userName"),
                            rs.getString("contrasena"),
                            rs.getString("gmail"),
                            rs.getInt("saf"),
                            rs.getString("nombre"),
                            TipoOrganismo.valueOf(rs.getString("tipoOrganismo")),
                            rol,
                            rs.getInt("codigoUsuario")
                    );
                }
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener organismo público por username", e);
        }
    }

    @Override
    public void actualizarDatosOrganismo(OrganismoPublicoDTO organismoPublicoDTO) {
        final String SQL = """
                UPDATE usuario u
                INNER JOIN organismopublico o ON u.userName = o.userName
                SET u.gmail = ?,
                    u.contrasena = ?,
                    o.nombre = ?,
                    o.tipoOrganismo = ?
                WHERE o.saf = ?
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, organismoPublicoDTO.usuario().gmail());
            st.setString(2, organismoPublicoDTO.usuario().contrasena());
            st.setString(3, organismoPublicoDTO.nombre());
            st.setString(4, organismoPublicoDTO.tipoOrganismo().name());
            st.setInt(5, organismoPublicoDTO.saf());

            int fila = st.executeUpdate();
            if (fila <= 0) {
                throw new RuntimeException("No se encontró el organismo público");
            }

        } catch (Exception e) {
            throw new RuntimeException("No se pudieron actualizar los datos del organismo público", e);
        }
    }
    @Override
    public OrganismoPublico findByUserName(String userName) {
        String sql = """
            SELECT o.saf,
                   o.nombre,
                   o.tipoOrganismo,
                   u.codigo,
                   u.userName,
                   u.contrasena,
                   u.gmail,
                   r.codigo AS codigo_rol,
                   r.nombre AS nombre_rol
            FROM organismopublico o
            INNER JOIN usuario u ON o.userName = u.userName
            INNER JOIN roles r ON u.rol = r.codigo
            WHERE o.userName = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Rol rol = new Rol(
                        rs.getString("nombre_rol"),
                        rs.getInt("codigo_rol")
                );

                return new OrganismoPublico(
                        rs.getString("userName"),
                        rs.getString("contrasena"),
                        rs.getString("gmail"),
                        rs.getInt("saf"),
                        rs.getString("nombre"),
                        TipoOrganismo.valueOf(rs.getString("tipoOrganismo")),
                        rol,
                        rs.getInt("codigo")
                );
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar organismo público por username", e);
        }
    }
}

