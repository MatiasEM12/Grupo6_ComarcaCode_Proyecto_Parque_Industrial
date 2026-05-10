package database;

import main.ConnectionManager;
import model.ProyectoProductivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProyectoProductivoDAOJDBC implements ProyectoProductivoDAO{

    @Override
    public void registrarProyectoProductivo(ProyectoProductivo proyectoProductivo) {
        final String SQL = "INSERT INTO AdministradorParque( nombre, descripcion, superficie," +
                " necesidades, empleabilidad, materiaPrima, estado, cuit_empresa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, proyectoProductivo.nombre());
            st.setString(2, proyectoProductivo.descripcion());
            st.setDouble(3, proyectoProductivo.superficie());
            st.setString(4, proyectoProductivo.necesidades());
            st.setInt(5, proyectoProductivo.empleabilidad());
            st.setString(6, proyectoProductivo.materiaPrima());
            st.setBoolean(7, proyectoProductivo.enEjecucion());
            st.setString(8, proyectoProductivo.empresa().cuit());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }
}
