package database.JDBCs;


import database.ConnectionManager;
import model.Lote;
import model.RepresentanteEmpresa;
import model.Ubicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DAOs.LoteDAO;
import model.Usuario;

public class LoteDAOJDBC implements LoteDAO{

    @Override
    public void registrarLote(Lote lote) {
        final String SQL = "INSERT INTO Empresa(ubicacion, superficie, estado, infraestructura, " +
                "id_proyecto, dni_admin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
          /*  Ubicacion ubicacion = lote.ubicacion();
            //en la base de datos dise que id va se incremental entonces tendriamo que sacar id de lote.
            //porque sino no van a coicidir o enves de que sea incremental por la base de datos que lo se por la misma clase
            st.setString(1, "Latitud: " + ubicacion.latitud + ", Longitud: " +
                    ubicacion.longitud + ", Altitud: " + ubicacion.altitud);
            st.setDouble(2, lote.superficie());
            st.setString(3, lote.estado());
            st.setString(4, lote.infraestructura());
            st.setInt(5, lote.id());
            st.setString(6, "444");
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }

           */
        }catch (Exception e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    public List<Lote> lotesDisponibles(){
        //recordar modificar ubicacion de la base de estado, cambiarlo por latitud, longitud y
        // altitud para poder costruir la ubicacion
        final String SQL = "SELECT * FROM Lote WHERE estado = ?";
        List <Lote> lotes = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            // como el estado del lote es un String le puse disponible
            st.setString(1, "DISPONIBLE");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                //por ahora le puse una ubicacion de ejemplo
                Lote lote = new Lote(new Ubicacion(23,23,23),
                        rs.getDouble("superficie"),
                        rs.getString("estado"),
                        rs.getString("infraestructura"));
                lotes.add(lote);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles", e);
        }
        return lotes;
    }

    public List<Lote> lotesDelUsuario(RepresentanteEmpresa usuario){
        //recordar modificar ubicacion de la base de estado, cambiarlo por latitud, longitud y
        // altitud para poder costruir la ubicacion
        final String SQL = "SELECT * FROM Lote WHERE dni_admin = ?";
        List <Lote> lotes = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, usuario.dni());
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                //por ahora le puse una ubicacion de ejemplo
                //despues revisar el costructor de Lote por el tema del id para recuperarlo
                Lote lote = new Lote(new Ubicacion(23,23,23),
                        rs.getDouble("superficie"),
                        rs.getString("estado"),
                        rs.getString("infraestructura"));
                lotes.add(lote);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles", e);
        }
        return lotes;
    }

    //este es para recuperar todos los lotes
    public List<Lote> lotesManegadosPorElPaque(){

        final String SQL = "SELECT * FROM Lote";
        List <Lote> lotes = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Lote lote = new Lote(new Ubicacion(23,23,23),
                        rs.getDouble("superficie"),
                        rs.getString("estado"),
                        rs.getString("infraestructura"));
                lotes.add(lote);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles" + e);
        }
        return lotes;
    }

}
