package database;

import model.AdministradorDelParque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDelParqueDAOJDBC implements AdministradorDelParqueDAO{
    @Override
    public void registrarAdministrador(AdministradorDelParque administradorDelParque) {
        final String SQL = "INSERT INTO AdministradorParque(DNI, nombre, userName) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, administradorDelParque.dni());
            st.setString(2, administradorDelParque.nombre());
            st.setString(3, administradorDelParque.usuario());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }
}
