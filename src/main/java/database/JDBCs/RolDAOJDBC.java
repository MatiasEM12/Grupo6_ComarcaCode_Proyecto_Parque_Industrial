package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.RolDAO;
import model.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RolDAOJDBC implements RolDAO {

    @Override
    public void create(Rol rol) {
        final String SQL = "INSERT INTO roles(nombre) VALUES (?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, rol.nombre());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("Error al registrar rol");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar rol", e);
        }
    }

    @Override
    //funciona
    public void update(Rol rol) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("UPDATE roles SET nombre = ? WHERE codigo = ?");
            statement.setString(1, rol.nombre());
            statement.setInt(2, rol.codigo());
            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                System.out.println("El Rol se ha actualizado correctamente");
            } else {
                throw new RuntimeException("Error al actualizar");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar consulta" + e.getMessage());
        }
    }

    public void remove(Integer codigo) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM roles WHERE codigo = ?"
            );

            statement.setInt(1, codigo);

            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                System.out.println("Rol eliminado correctamente.");
            } else {
                throw new RuntimeException("No se encontró el rol con ese código.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al Eliminar rol" + e.getMessage());
        }
    }

    @Override
    public void remove(Rol rol) {
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM roles WHERE codigo = ?"
            );

            statement.setInt(1, rol.codigo());

            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                System.out.println("Rol eliminado correctamente.");
            } else {
                throw new RuntimeException("No se encontró el rol con ese código.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al Eliminar rol" + e.getMessage());
        }
    }

    public Rol find(Integer codigo) {
        Rol rol = null;

        String sql = "SELECT r.codigo, r.nombre FROM roles r WHERE r.codigo = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, codigo);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {

                    rol = new Rol(
                            rs.getString("nombre"),
                            rs.getInt("codigo"));
                }
            }
        } catch (Exception e){
            throw new RuntimeException("Error al procesar consulta: " + e.getMessage());
        }

        return rol;
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> listado = new ArrayList<>();

        String sql = "SELECT r.codigo, r.nombre FROM roles r";

        try (Connection conn = ConnectionManager.getConnection();
             Statement sentencia = conn.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                String nombre = resultado.getString("nombre");

                Rol rol = new Rol(nombre, codigo);

                listado.add(rol);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de MySQL\n" + e);
        }

        return listado;
    }

    public int obtenerCantidadRoles() {
        String sql = "SELECT COUNT(*) FROM roles";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);  // devuelve el COUNT(*)
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Boolean existe(String nombre) {
        return null;
    }
}
