package database;

import main.ConnectionManager;
import model.Rol;
import model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAOJDBC implements UsuarioDAO{

    @Override
    public void registrar(Usuario usuario) {
        final String SQL = "INSERT INTO Usuario(userName, contrasena, rol, gmail) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, usuario.UserName());
            st.setString(2, usuario.contrasena());
            st.setString(3, usuario.rol());
            st.setString(4, usuario.gmail());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }
}
