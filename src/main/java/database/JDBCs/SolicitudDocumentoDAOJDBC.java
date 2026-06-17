package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.SolicitudDocumentoDAO;
import model.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCs.DocumentoDAOJDBC.mapearDocumento;

public class SolicitudDocumentoDAOJDBC implements SolicitudDocumentoDAO{
    @Override
    public void vincular(int idSolicitud, int idDocumento) {

        final String SQL = "INSERT INTO SolicitudDocumento " + "(idSolicitud,idDocumento) " +
                        "VALUES (?,?)";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idSolicitud);

            st.setInt(2,idDocumento);

            st.executeUpdate();

        } catch(Exception e) {

            throw new RuntimeException(
                    "Error al vincular documento",
                    e
            );
        }
    }

    @Override
    public List<Documento> documentosDe(int idSolicitud) {

        List<Documento> documentos = new ArrayList<>();

        final String SQL = "SELECT d.* " +
                        "FROM Documento d " +
                        "JOIN SolicitudDocumento sd " +
                        "ON d.id = sd.idDocumento " +
                        "WHERE sd.idSolicitud = ?";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idSolicitud);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                documentos.add(mapearDocumento(rs));
            }

            return documentos;

        } catch(Exception e) {

            throw new RuntimeException(
                    "Error al obtener documentos",
                    e
            );
        }
    }
}
