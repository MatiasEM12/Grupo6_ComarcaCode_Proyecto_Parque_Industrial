package database.JDBCs;

import database.ConnectionManager;
import model.AdministradorDelParque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DAOs.AdministradorDelParqueDAO;
import model.DTO.AdministradorDelParqueDTO;
import model.Rol;

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
        }catch (Exception e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public AdministradorDelParque obtenerAdministradorPorUsername(String username) {

        final String SQL =
                "SELECT u.userName, u.contrasena, u.gmail, " +
                        "r.codigo, r.nombre AS rolNombre, " +
                        "a.dni, a.nombre " +
                        "FROM AdministradorDelParque a " +
                        "INNER JOIN usuario u ON a.userName = u.userName " +
                        "INNER JOIN roles r ON u.rol = r.codigo " +
                        "WHERE u.userName = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                String userName = rs.getString("userName");
                String contrasena = rs.getString("contrasena");
                String gmail = rs.getString("gmail");

                int codigoRol = rs.getInt("codigo");
                String nombreRol = rs.getString("rolNombre");

                Rol rol = new Rol( nombreRol,codigoRol);

                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");

                return new AdministradorDelParque(
                        userName,
                        contrasena,
                        rol,
                        gmail,
                        dni,
                        nombre
                );
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al obtener administrador por username",
                    e
            );
        }
    }

    @Override
    public void actualizarDatosAdministrador(AdministradorDelParqueDTO adm) {

        String sql = "UPDATE usuario u JOIN AdministradorDelParque a ON u.userName = a.userName " +
                "SET u.gmail = ?, u.contrasena = ?, a.nombre = ? WHERE a.dni = ? ";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, adm.usuario().getGmail());
            st.setString(2, adm.usuario().contrasena());
            st.setString(3, adm.nombre());
            st.setString(4, adm.dni());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException(
                        "no se encontro al administrador"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("no se pudo actualizar los datos del administrador");
        }
    }
}
