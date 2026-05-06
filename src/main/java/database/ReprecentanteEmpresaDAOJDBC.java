package database;

import model.RepresentanteEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReprecentanteEmpresaDAOJDBC implements ReprecentanteEmpresaDAO{
    @Override
    // tendriamos que combiar de la tabla del reprecentante el nombre por nombre de empresa para que quede claro
    public void registrarReprecentante(RepresentanteEmpresa representanteEmpresa) {
        final String SQL = "INSERT INTO RepresentanteEmpresa(DNI, nombre, userName) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, representanteEmpresa.dni());
            st.setString(2, representanteEmpresa.nombreEmpresa());
            st.setString(3, representanteEmpresa.usuario().getUserName());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }
}
