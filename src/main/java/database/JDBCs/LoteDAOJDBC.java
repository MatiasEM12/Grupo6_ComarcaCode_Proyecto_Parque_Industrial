package database.JDBCs;


import database.ConnectionManager;
import model.Lote;
import model.Ubicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DAOs.LoteDAO;

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
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }
}
