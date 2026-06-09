package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.DocumentoDAO;
import model.Documento;
import model.TipoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAOJDBC implements DocumentoDAO{
    @Override
    public void create(Documento documento, int idReporte) {

        final String SQL = "INSERT INTO Documento " + "(tipo,nombreArchivo,rutaArchivo,tamanio,fechaCarga,idReporte) " +
                "VALUES (?,?,?,?,?,?)";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, documento.tipo().name());

            st.setString(2, documento.nombreArchivo());

            st.setString(3, documento.rutaArchivo());

            st.setLong(4, documento.tamanioBytes());

            st.setDate(5, java.sql.Date.valueOf(documento.fechaCarga()));

            st.setInt(6, idReporte);

            st.executeUpdate();

        } catch(Exception e) {

            throw new RuntimeException("Error al registrar documento", e);
        }
    }
    @Override
    public Documento find(int id) {
        final String SQL = "SELECT * "
                + "FROM Documento "
                + "WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, String.valueOf(id));

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearDocumento(rs);
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException("Error al buscar documento por ruta", e);
        }
    }

    @Override
    public List<Documento> findAll() {
        return null;
    }

    @Override
    public List<Documento> findByReporteId(int reporteId) {
        List<Documento> documentos = new ArrayList<>();
        
        final String sql = "SELECT * FROM documento WHERE idReporte = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

                st.setInt(1, reporteId);

                try(ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        documentos.add(mapearDocumento(rs));
                    }
                }

        }catch (SQLException e) {
            throw new RuntimeException("Error al buscar documentos por reporte", e);
        }
        return documentos;
    }

    @Override
    public void update(model.Documento documento) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Documento findPorRuta(String ruta) {

        final String SQL = "SELECT * "
                            + "FROM Documento "
                            + "WHERE rutaArchivo = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, ruta);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearDocumento(rs);
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al buscar documento por ruta",
                    e
            );
        }
    }
    public static Documento  mapearDocumento(ResultSet rs)
            throws SQLException {

        return new Documento(
                rs.getInt("id"),

                TipoDocumento.valueOf(rs.getString("tipo")),

                rs.getString("nombreArchivo"),

                rs.getString("rutaArchivo"),

                rs.getLong("tamanio"),

                rs.getDate("fechaCarga").toLocalDate()
        );
    }
}
