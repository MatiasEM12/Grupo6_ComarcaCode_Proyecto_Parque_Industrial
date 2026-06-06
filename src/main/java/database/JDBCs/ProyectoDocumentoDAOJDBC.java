package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ProyectoDocumentoDAO;
import database.DAOs.ProyectoProductivoDAO;
import model.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCs.DocumentoDAOJDBC.mapearDocumento;

public class ProyectoDocumentoDAOJDBC implements ProyectoDocumentoDAO {
    @Override
    public void registrarDocumentos(int idProyecto, int idDocumento) {
        final String SQL = "INSERT INTO PrpyectpDocumento " + "(idProyecto,idDocumento) " +
                "VALUES (?,?)";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idProyecto);

            st.setInt(2,idDocumento);

            st.executeUpdate();

        } catch(Exception e) {

            throw new RuntimeException("Error al vincular documento", e);
        }
    }

    @Override
    public List<Documento> findAllBy(int idProyecto) {
        List<Documento> documentos = new ArrayList<>();

        final String SQL = "SELECT d.* " +
                "FROM Documento d " +
                "JOIN ProyectoDocumento sd " +
                "ON d.id = sd.idDocumento " +
                "WHERE sd.idProyecto = ?";

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1,idProyecto);

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
