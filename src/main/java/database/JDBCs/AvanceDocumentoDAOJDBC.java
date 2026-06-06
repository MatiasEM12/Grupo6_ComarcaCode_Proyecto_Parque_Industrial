package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.AvanceDocumentoDAO;
import model.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCs.DocumentoDAOJDBC.mapearDocumento;

public class AvanceDocumentoDAOJDBC implements AvanceDocumentoDAO {

    @Override
    public void vincular(int idAvance, int idDocumento) {
        final String SQL = "INSERT INTO AvanceDocumento " + "(idAvanceProyecto,idDocumento) " +
                "VALUES (?,?)";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idAvance);

            st.setInt(2,idDocumento);

            st.executeUpdate();

        } catch(Exception e) {

            throw new RuntimeException("Error al vincular documento", e);
        }
    }

    @Override
    public List<Documento> documentosDe(int idAvance) {
        List<Documento> documentos = new ArrayList<>();

        final String SQL = "SELECT d.* " +
                "FROM Documento d " +
                "JOIN AvanceDocumento sd " +
                "ON d.id = sd.idDocumento " +
                "WHERE sd.idAvanceProyecto = ?";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idAvance);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                documentos.add(mapearDocumento(rs));
            }

            return documentos;

        } catch(Exception e) {

            throw new RuntimeException("Error al obtener documentos", e);
        }
    }
}
