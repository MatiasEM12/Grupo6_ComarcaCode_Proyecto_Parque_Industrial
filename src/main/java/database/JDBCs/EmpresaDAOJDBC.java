package database.JDBCs;


import database.ConnectionManager;
import model.DTO.EmpresaDTO;
import model.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DAOs.EmpresaDAO;

public class EmpresaDAOJDBC implements EmpresaDAO{

    @Override
    //le quitaria o agregaria rubro a la clase empresa porque no existe
    public void registrarEmpresa(Empresa empresa) {
        final String SQL = "INSERT INTO Empresa(cuit, razonSocial, rubro, contacto, " +
                "contactoRepresentante, esRadicada, dni_representante) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, empresa.cuit());
            st.setString(2, empresa.razonSocial());
            st.setString(3, "todabia no existe rubro");
            st.setString(4, empresa.contacto());
            st.setString(5, empresa.contactoRepresentante());
            st.setBoolean(6, empresa.esRadicada());
            //abria que ponerle al usuario dni o que se estienda enves de usuario a reprecentanteEmpresa
            st.setString(7, empresa.representante().dni());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public void actualizar(EmpresaDTO empresa) {

    }
}
