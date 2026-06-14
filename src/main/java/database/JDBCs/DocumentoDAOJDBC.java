package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.DocumentoDAO;
import model.Documento;
import model.TipoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DocumentoDAOJDBC implements DocumentoDAO{
    @Override
    public void create(Documento documento) {

        final String SQL = """
            INSERT INTO documento
            (tipo, nombreArchivo, rutaArchivo, tamanio, fechaCarga)
            VALUES (?, ?, ?, ?, ?)
            """;

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            st.setString(1, documento.tipo().name());
            st.setString(2, documento.nombreArchivo());
            st.setString(3, documento.rutaArchivo());
            st.setLong(4, documento.tamanioBytes());
            st.setDate(5, java.sql.Date.valueOf(documento.fechaCarga()));

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                documento.asignarId(rs.getInt(1));
            }

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

            throw new RuntimeException(
                    "Error al buscar documento por ruta",
                    e
            );
        }
    }

    @Override
    public List<Documento> findAll() {
        return null;
    }

    @Override
    public void update(model.Documento documento) {

    }

    @Override
    public void actualizarDocumento(int idDocumento, String nombre, String ruta, long tamanio) {

        String SQL = "UPDATE documento SET nombreArchivo=?, rutaArchivo=?, tamanio=? WHERE id=?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, nombre);
            st.setString(2, ruta);
            st.setLong(3, tamanio);
            st.setInt(4, idDocumento);

            int filas = st.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se pudo actualizar el documento");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error actualizando documento", e);
        }
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
