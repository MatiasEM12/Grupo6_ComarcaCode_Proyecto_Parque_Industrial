package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ReprecentanteEmpresaDAO;

import model.RepresentanteEmpresa;
import model.Rol;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReprecentanteEmpresaDAOJDBC implements ReprecentanteEmpresaDAO {
    @Override
    // tendriamos que combiar de la tabla del reprecentante el nombre por nombre de empresa para que quede claro
    public void registrarReprecentante(RepresentanteEmpresa representanteEmpresa) {
        final String SQL = "INSERT INTO RepresentanteEmpresa(DNI, nombre, userName) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, representanteEmpresa.dni());
            st.setString(2, representanteEmpresa.nombreEmpresa());
            st.setString(3, representanteEmpresa.usuario().UserName());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public void update(RepresentanteEmpresa representanteEmpresa) {
        final String SQL = "UPDATE RepresentanteEmpresa SET nombre = ?, userName = ? WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, representanteEmpresa.nombreEmpresa());
            st.setString(2, representanteEmpresa.usuario().UserName());
            st.setString(3, representanteEmpresa.dni());

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al actualizar representante de empresa");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar representante de empresa", e);
        }
    }

    @Override
    public void remove(String dni) {
        final String SQL = "DELETE FROM RepresentanteEmpresa WHERE DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al eliminar representante de empresa");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar representante de empresa", e);
        }
    }

    @Override
    public RepresentanteEmpresa find(String dni) {
        // Implementación del método para buscar un representante de empresa por su DNI en la base de datos
        final String SQL = "SELECT * FROM RepresentanteEmpresa JOIN Usuario ON RepresentanteEmpresa.userName = Usuario.userName" +
            "JOIN rol ON Usuario.rol = rol.id WHERE RepresentanteEmpresa.DNI = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, dni);

            try (java.sql.ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol(rs.getString("nombre"), rs.getInt("codigo"));
                    Usuario usuario = new Usuario(rs.getString("userName"), rs.getString("contrasena"), rol, rs.getString("gmail"));
                    String nombreEmpresa = rs.getString("nombre");
                    return new RepresentanteEmpresa(dni, nombreEmpresa, usuario);
                } else {
                    throw new RuntimeException("Representante de empresa no encontrado con DNI: " + dni);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar representante de empresa", e);
        }
    } 
}
