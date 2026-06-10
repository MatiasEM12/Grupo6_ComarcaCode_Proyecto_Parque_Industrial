package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ReporteDAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAOJDBC implements ReporteDAO {

    @Override
    public void guardar(Reporte reporte) {
        String sql = """
                INSERT INTO reporte
                (tipo, descripcion, fecha, usuario_generador)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, reporte.tipo().name());
            ps.setString(2, reporte.descripcion());
            ps.setDate(3, Date.valueOf(reporte.fecha()));
            ps.setInt(4, reporte.usuario().id());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                reporte.asignarId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el reporte", e);
        }
    }

    @Override
    public List<Reporte> findAll() {
        List<Reporte> reportes = new ArrayList<>();

        String sql = """
        SELECT r.id_reporte,
               r.tipo,
               r.descripcion,
               r.fecha,
               u.codigo,
               u.userName,
               u.contrasena,
               u.gmail,
               rol.codigo AS codigo_rol,
               rol.nombre AS nombre_rol
        FROM reporte r
        INNER JOIN usuario u ON r.usuario_generador = u.codigo
        INNER JOIN roles rol ON u.rol = rol.codigo
        ORDER BY r.fecha DESC
        """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                cargarDocumentos(reporte);
                reportes.add(reporte);
            }

            return reportes;

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los reportes", e);
        }
    }

    @Override
    public Reporte findById(int id) {
        String sql = """
            SELECT r.id_reporte,
                   r.tipo,
                   r.descripcion,
                   r.fecha,
                   u.codigo,
                   u.userName,
                   u.contrasena,
                   u.gmail,
                   rol.codigo AS codigo_rol,
                   rol.nombre AS nombre_rol
            FROM reporte r
            INNER JOIN usuario u ON r.usuario_generador = u.codigo
            INNER JOIN roles rol ON u.rol = rol.codigo
            WHERE r.id_reporte = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                cargarDocumentos(reporte);
                return reporte;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el reporte", e);
        }
    }

    private Reporte mapearReporte(ResultSet rs) throws SQLException {
        Rol rol = new Rol(
                rs.getString("nombre_rol"),
                rs.getInt("codigo_rol")
        );

        Usuario usuario = new Usuario(
                rs.getInt("codigo"),
                rs.getString("userName"),
                rs.getString("contrasena"),
                rol,
                rs.getString("gmail")
        );

        Reporte reporte = new Reporte(
                TipoReporte.valueOf(rs.getString("tipo")),
                rs.getString("descripcion"),
                usuario
        );

        reporte.asignarId(rs.getInt("id_reporte"));

        return reporte;
    }

    @Override
    public void vincularDocumento(int idReporte, int idDocumento) {
        String sql = """
            INSERT INTO reportedocumento
            (id_reporte, id_documento)
            VALUES (?, ?)
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idReporte);
            ps.setInt(2, idDocumento);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al vincular reporte con documento", e);
        }
    }
    private void cargarDocumentos(Reporte reporte) {
        String sql = """
        SELECT d.id, d.tipo, d.nombreArchivo, d.rutaArchivo, d.tamanio, d.fechaCarga
        FROM reportedocumento rd
        INNER JOIN documento d ON rd.id_documento = d.id
        WHERE rd.id_reporte = ?
    """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reporte.id());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Documento documento = DocumentoDAOJDBC.mapearDocumento(rs);
                reporte.adjuntarDocumento(documento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar documentos del reporte", e);
        }
    }
}