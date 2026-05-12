package database;

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
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO roles(codigo, nombre) "
                            + "VALUES (?, ?)");

            statement.setInt(1, rol.codigo());
            statement.setString(2, rol.nombre());
            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                // System.out.println("Modificando " + cantidad + " registros");
            } else {
                throw new RuntimeException("Error al actualizar");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al procesar consulta" + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
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

        } catch (SQLException e) {
            throw new RuntimeException("Error al procesar consulta" + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
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

        } catch (SQLException e) {
            throw new RuntimeException("Error al Eliminar rol" + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
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

        } catch (SQLException e) {
            throw new RuntimeException("Error al Eliminar rol" + e.getMessage());
        } finally {
            ConnectionManager.disconnect();
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.disconnect();
        }
        return 0;
    }
}
