package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ProyectoProductivoDAO;
import model.ProyectoProductivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoProductivoDAOJDBC implements ProyectoProductivoDAO {

    @Override
    public void registrarProyectoProductivo(ProyectoProductivo proyectoProductivo) {

        final String SQL = "INSERT INTO ProyectoProductivo " +
                "(nombre, descripcion, superficie, necesidades, empleabilidad, materiaPrima, estado, cuit_empresa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, proyectoProductivo.nombre());
            st.setString(2, proyectoProductivo.descripcion());
            st.setDouble(3, proyectoProductivo.superficie());
            st.setString(4, proyectoProductivo.necesidades());
            st.setInt(5, proyectoProductivo.empleabilidad());
            st.setString(6, proyectoProductivo.materiaPrima());
            st.setBoolean(7, proyectoProductivo.enEjecucion());
            st.setString(8, proyectoProductivo.empresa().cuit());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("Error al registrar proyecto productivo");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar proyecto productivo", e);
        }
    }

    @Override
    public ProyectoProductivo find(int idProyecto) {

        final String SQL = "SELECT * FROM ProyectoProductivo WHERE idProyecto = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, idProyecto);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearProyecto(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyecto productivo", e);
        }
    }

    @Override
    public List<ProyectoProductivo> findAll() {

        final String SQL = "SELECT * FROM ProyectoProductivo";

        List<ProyectoProductivo> proyectos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                proyectos.add(mapearProyecto(rs));
            }

            return proyectos;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos productivos", e);
        }
    }

    @Override
    public List<ProyectoProductivo> findByEmpresa(String cuitEmpresa) {

        final String SQL = "SELECT * FROM ProyectoProductivo WHERE cuit_empresa = ?";

        List<ProyectoProductivo> proyectos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, cuitEmpresa);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                proyectos.add(mapearProyecto(rs));
            }

            return proyectos;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener proyectos por empresa", e);
        }
    }

    @Override
    public void actualizarEstado(int idProyecto, boolean estado) {

        final String SQL = "UPDATE ProyectoProductivo SET estado = ? WHERE idProyecto = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setBoolean(1, estado);
            st.setInt(2, idProyecto);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró el proyecto para actualizar");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estado del proyecto", e);
        }
    }

    private ProyectoProductivo mapearProyecto(ResultSet rs) throws SQLException {

        return new ProyectoProductivo(
                rs.getInt("idProyecto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("superficie"),
                rs.getString("necesidades"),
                rs.getInt("empleabilidad"),
                rs.getString("materiaPrima"),
                rs.getBoolean("estado"),
                null
        );
    }
}