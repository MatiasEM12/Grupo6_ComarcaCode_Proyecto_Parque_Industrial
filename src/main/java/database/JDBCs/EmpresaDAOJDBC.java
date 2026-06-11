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

    public void registrarEmpresa(Empresa empresa) {
        final String SQL = "INSERT INTO Empresa(cuit, razonSocial, contacto, " +
                "contactoRepresentante, radicada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, empresa.cuit());
            st.setString(2, empresa.razonSocial());
            st.setString(3, empresa.contacto());
            st.setString(4, empresa.contactoRepresentante());
            st.setBoolean(5, empresa.esRadicada());
            int fila = st.executeUpdate();
            if (fila<=0){
                throw new RuntimeException("Error al registrar usuario");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al registrar usuario", e);
        }
    }

    @Override
    public void actualizar(EmpresaDTO empresa) {
        final String SQL = "UPDATE Empresa SET razonSocial = ?, contacto = ?, " +
                        "contactoRepresentante = ?, radicada = ? WHERE cuit = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {
            st.setString(1, empresa.getRazonSocial());
            st.setString(2, empresa.getContacto());
            st.setString(3, empresa.getContactoRepresentante());
            st.setBoolean(4, empresa.getRadicada());

            st.setString(5, empresa.getCuit());

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró la empresa");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar empresa", e);
        }
    }
    @Override
    public void actualizarContacto(String cuit, String contacto, String contactoRepresentante) {

        final String SQL = """
            UPDATE empresa
            SET contacto = ?,
                contactoRepresentante = ?
            WHERE cuit = ?
            """;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, contacto);
            st.setString(2, contactoRepresentante);
            st.setString(3, cuit);

            int fila = st.executeUpdate();

            if (fila <= 0) {
                throw new RuntimeException("No se encontró la empresa");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar contacto de empresa", e);
        }
    }
}
