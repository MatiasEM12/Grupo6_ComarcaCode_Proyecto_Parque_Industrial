package database.JDBCs;

import database.ConnectionManager;
import model.AdministradorDelParque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DAOs.AdministradorDelParqueDAO;
import model.DTO.AdministradorDelParqueDTO;
import model.Rol;

public class AdministradorDelParqueDAOJDBC implements AdministradorDelParqueDAO{
    @Override
    public void registrarAdministrador(AdministradorDelParque administradorDelParque) {
        final String SQL = "INSERT INTO administradordelparque(DNI, nombre, userName) VALUES (?, ?, ?)";
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
                "SELECT u.codigo AS codigoUsuario, " +
                        "u.userName, u.contrasena, u.gmail, " +
                        "r.codigo AS codigoRol, r.nombre AS rolNombre, " +
                        "a.dni, a.nombre " +
                        "FROM administradordelparque a " +
                        "INNER JOIN usuario u ON a.userName = u.userName " +
                        "INNER JOIN roles r ON u.rol = r.codigo " +
                        "WHERE u.userName = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, username);

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {

                    String userName = rs.getString("userName");
                    String contrasena = rs.getString("contrasena");
                    String gmail = rs.getString("gmail");

                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");

                    int codigoUsuario = rs.getInt("codigoUsuario");

                    Rol rol = new Rol(
                            rs.getString("rolNombre"),
                            rs.getInt("codigoRol")
                    );

                    return new AdministradorDelParque(
                            userName,
                            contrasena,
                            rol,
                            gmail,
                            dni,
                            nombre,
                            codigoUsuario
                    );
                }

                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al obtener administrador por username", e
            );
        }
    }

    @Override
    public void actualizarDatosAdministrador(AdministradorDelParqueDTO adm) {

        String sql = "UPDATE usuario u JOIN administradordelparque a ON u.userName = a.userName " +
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
