package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.LoteDAO;
import model.Lote;
import model.Ubicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoteDAOJDBC implements LoteDAO {

    @Override
    public void create(Lote lote) {

        final String SQL =
                "INSERT INTO lotes " +
                        "(latitud, longitud, altitud, superficie, estado, infraestructura) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setLong(1, lote.ubicacion().latitud());
            st.setLong(2, lote.ubicacion().longitud());
            st.setLong(3, lote.ubicacion().altitud());
            st.setDouble(4, lote.superficie());
            st.setString(5, lote.estado());
            st.setString(6, lote.infraestructura());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("Error al registrar lote");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar lote", e);
        }
    }

    @Override
    public Lote find(int id) {

        final String SQL =
                "SELECT * FROM lotes WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearLote(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar lote", e);
        }
    }

    @Override
    public List<Lote> findAll() {

        final String SQL = "SELECT * FROM lotes";

        List<Lote> lotes = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }

            return lotes;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes", e);
        }
    }

    @Override
    public List<Lote> findDisponibles() {

        final String SQL =
                "SELECT * FROM lotes WHERE estado = 'DISPONIBLE'";

        List<Lote> lotes = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lotes.add(mapearLote(rs));
            }

            return lotes;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles", e);
        }
    }

    public Lote findLoteProyecto(int idProyecto){
        final String SQL = "SELECT * FROM lotes WHERE id_proyecto = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setInt(1, idProyecto);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return mapearLote(rs);
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles", e);
        }
    }

    @Override
    public void actualizarEstado(int id, String estado) {

        final String SQL =
                "UPDATE lotes SET estado = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, estado);
            st.setInt(2, id);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró el lote");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar lote", e);
        }
    }

    private Lote mapearLote(ResultSet rs) throws SQLException {

        Ubicacion ubicacion = new Ubicacion(
                rs.getLong("latitud"),
                rs.getLong("longitud"),
                rs.getLong("altitud")
        );

        return new Lote(
                rs.getInt("id"),
                ubicacion,
                rs.getDouble("superficie"),
                rs.getString("estado"),
                rs.getString("infraestructura")
        );
    }
    @Override
    public void update(Lote lote) {

        final String SQL =
                "UPDATE lotes SET latitud = ?, longitud = ?, altitud = ?, " +
                        "superficie = ?, estado = ?, infraestructura = ? " +
                        "WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setLong(1, lote.ubicacion().latitud());
            st.setLong(2, lote.ubicacion().longitud());
            st.setLong(3, lote.ubicacion().altitud());
            st.setDouble(4, lote.superficie());
            st.setString(5, lote.estado());
            st.setString(6, lote.infraestructura());
            st.setInt(7, lote.id());

            if (st.executeUpdate() <= 0) {
                throw new RuntimeException("No se encontró el lote");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar lote", e);
        }
    }
    @Override
    public void RegistrarProyectoLote(int id, int idProyecto) {

        final String SQL =
                "UPDATE lotes SET estado = ?, id_proyecto = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, "OCUPADO");
            st.setInt(2, idProyecto);
            st.setInt(3, id);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró el lote");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar lote", e);
        }
    }
}
