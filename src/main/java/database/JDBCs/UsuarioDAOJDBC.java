package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.UsuarioDAO;

import model.Rol;
import model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOJDBC implements UsuarioDAO {

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

    @Override
    public void update(Usuario usuario) {
        // Implementación del método para actualizar un usuario en la base de datos
        final String SQL = "UPDATE Usuario SET contrasena = ?, rol = ?, gmail = ? WHERE userName = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, usuario.contrasena());
            st.setInt(2, usuario.rol().codigo());
            st.setString(3, usuario.gmail());
            st.setString(4, usuario.UserName());

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al actualizar usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }

    @Override
    public void remove(String userName) {
        // Implementación del método para eliminar un usuario por su userName en la base de datos
        final String SQL = "DELETE FROM Usuario WHERE userName = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, userName);

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al eliminar usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    @Override
    public Usuario find(String userName) {
        // Implementación del método para buscar un usuario por su userName en la base de datos
        final String SQL = "SELECT * FROM Usuario WHERE userName = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, userName);

            try (java.sql.ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    String contrasena = rs.getString("contrasena");
                    int rol = rs.getInt("rol");
                    String gmail = rs.getString("gmail");
                    return new Usuario(userName, contrasena, Rol.fromCodigo(rol), gmail);
                } else {
                    return null; // Usuario no encontrado
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        final String SQL = "SELECT * FROM Usuario";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             java.sql.ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String userName = rs.getString("userName");
                String contrasena = rs.getString("contrasena");
                int rol = rs.getInt("rol");
                String gmail = rs.getString("gmail");
                usuarios.add(new Usuario(userName, contrasena, Rol.fromCodigo(rol), gmail));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener usuarios", e);
        }
        return usuarios;
    }
}
