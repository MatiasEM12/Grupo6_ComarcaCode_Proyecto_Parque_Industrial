package database.JDBCs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import database.ConnectionManager;
import database.DAOs.ReporteDAO;
import database.DAOs.DocumentoDAO;
import model.Documento;
import model.TipoReporte;
import model.Usuario;
import model.DTO.ReporteDTO;

public class ReporteDAOJDBC implements ReporteDAO{

    @Override
    public int generarReporte(ReporteDTO reporte) {
        
        final String sql = "INSERT INTO Reporte (tipo, descripcion, fecha, userName, cantidadDocumentosAdjuntos)" + 
                  "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            
            st.setString(1, reporte.tipo().name());
            st.setString(2, reporte.descripcion());
            st.setDate(3, Date.valueOf(reporte.fecha()));
            st.setString(4, reporte.usuario().UserName());
            st.setInt(5, reporte.cantidadDocumentosAdjuntos());

            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                return rs.getInt(1);
            }else {
                throw new RuntimeException("No se pudo obtener el ID del reporte generado");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }

    @Override
    public ReporteDTO find(int id) {
        final String sql = "SELECT * FROM Reporte WHERE id = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                    int idReporte = rs.getInt("id");
                    TipoReporte tipo = TipoReporte.valueOf(rs.getString("tipo"));
                    String descripcion = rs.getString("descripcion");
                    Date fecha = rs.getDate("fecha");
                    Usuario usuario = new UsuarioDAOJDBC().find(rs.getString("userName"));
                    DocumentoDAO documentoDAO = new DocumentoDAOJDBC();
                    List<Documento> documentos = documentoDAO.findByReporteId(id);

                    return new ReporteDTO(idReporte, tipo, descripcion, fecha.toLocalDate(), usuario, documentos);

            } else {
                throw new RuntimeException("No se encontró el reporte con ID: " + id);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el reporte", e);
        }
    }

}
