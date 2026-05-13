package database;

import main.ConnectionManager;
import model.Rol;
import model.Usuario;

import java.sql.*;

public class UsuarioDAOJDBC implements UsuarioDAO{

    @Override
    public void registrar(Usuario usuario) {
        final String SQL = "INSERT INTO Usuario(userName, contrasena, rol, gmail) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, usuario.UserName());
            st.setString(2, usuario.contrasena());
            st.setInt(3, usuario.rol().codigo());
            st.setString(4, usuario.gmail());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    public Usuario recuperarUsuario(String userName){
        final String SQL = """
        SELECT 
            u.userName,
            u.contrasena,
            u.gmail,
            r.codigo,
            r.nombre
        FROM Usuario u
        JOIN roles r ON u.rol = r.codigo
        WHERE u.userName = ?
        """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, userName);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Rol rol = new Rol(rs.getString("nombre"),
                        rs.getInt("codigo"));

                return new Usuario(
                        rs.getString("userName"),
                        rs.getString("contrasena"),
                       rol,
                        rs.getString("gmail")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar usuario" + e);
        }
    }
}
