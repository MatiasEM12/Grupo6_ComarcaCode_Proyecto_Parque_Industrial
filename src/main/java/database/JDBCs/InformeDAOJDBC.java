package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.InformeDAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformeDAOJDBC implements InformeDAO {

    @Override
    public void guardar(Informe informe) {
        String sql = """
                INSERT INTO informe
                (tipo, descripcion, fecha, usuario_generador)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, informe.tipo().name());
            ps.setString(2, informe.descripcion());
            ps.setDate(3, Date.valueOf(informe.fecha()));
            ps.setInt(4, informe.usuario().id());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                informe.asignarId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el informe", e);
        }
    }

    @Override
    public List<Informe> findAll() {
        List<Informe> informes = new ArrayList<>();

        String sql = """
        SELECT r.id_informe,
               r.tipo,
               r.descripcion,
               r.fecha,
               u.codigo,
               u.userName,
               u.contrasena,
               u.gmail,
               rol.codigo AS codigo_rol,
               rol.nombre AS nombre_rol
        FROM informe r
        INNER JOIN usuario u ON r.usuario_generador = u.codigo
        INNER JOIN roles rol ON u.rol = rol.codigo
        ORDER BY r.fecha DESC
        """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Informe informe = mapearInforme(rs);
                cargarDocumentos(informe);
                informes.add(informe);
            }

            return informes;

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los informes", e);
        }
    }

    @Override
    public Informe findById(int id) {
        String sql = """
            SELECT r.id_informe,
                   r.tipo,
                   r.descripcion,
                   r.fecha,
                   u.codigo,
                   u.userName,
                   u.contrasena,
                   u.gmail,
                   rol.codigo AS codigo_rol,
                   rol.nombre AS nombre_rol
            FROM informe r
            INNER JOIN usuario u ON r.usuario_generador = u.codigo
            INNER JOIN roles rol ON u.rol = rol.codigo
            WHERE r.id_informe = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Informe informe = mapearInforme(rs);
                cargarDocumentos(informe);
                return informe;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el informe", e);
        }
    }

    private Informe mapearInforme(ResultSet rs) throws SQLException {
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

        Informe informe = new Informe(
                TipoInforme.valueOf(rs.getString("tipo")),
                rs.getString("descripcion"),
                usuario
        );

        informe.asignarId(rs.getInt("id_informe"));

        return informe;
    }

    @Override
    public void vincularDocumento(int idInforme, int idDocumento) {
        String sql = """
            INSERT INTO informedocumento
            (id_informe, id_documento)
            VALUES (?, ?)
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idInforme);
            ps.setInt(2, idDocumento);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al vincular informe con documento", e);
        }
    }
    private void cargarDocumentos(Informe informe) {
        String sql = """
        SELECT d.id, d.tipo, d.nombreArchivo, d.rutaArchivo, d.tamanio, d.fechaCarga
        FROM informedocumento rd
        INNER JOIN documento d ON rd.id_documento = d.id
        WHERE rd.id_informe = ?
    """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, informe.id());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Documento documento = DocumentoDAOJDBC.mapearDocumento(rs);
                informe.adjuntarDocumento(documento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar documentos del informe", e);
        }
    }

    @Override
    public Documento obtenerDocumento(int idInforme) {

        String sql = """
        SELECT d.*
        FROM informedocumento id
        INNER JOIN documento d
            ON id.id_documento = d.id
        WHERE id.id_informe = ?
        LIMIT 1
        """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idInforme);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return DocumentoDAOJDBC.mapearDocumento(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al recuperar documento del informe",
                    e
            );
        }
    }
}