package database.JDBCs;

import database.ConnectionManager;
import database.DAOs.ProyectoProductivoDAO;


import model.ProyectoProductivo;
import model.RepresentanteEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoProductivoDAOJDBC implements ProyectoProductivoDAO {

    @Override
    public void cargar(ProyectoProductivo proyectoProductivo) {

        final String SQL = """
                
                INSERT INTO ProyectoProductivo(
                
                    nombre,
                    objeto,
                    descripcion_servicio,
                    emplazamiento,
                    tipo_personal,
                    tiempo_radicacion,
                    metros_cuadrados,
                    area_trabajo,
                    area_deposito,
                    estacionamiento,
                    tiene_planos,
                    personal_ocupar,
                    materias_primas,
                    destino_produccion,
                    tension,
                    potencia,
                    agua,
                    necesita_gas,
                    residuos,
                    realiza_tratamiento,
                    necesita_balanza,
                    necesita_comedor,
                    necesita_coworking,
                    en_ejecucion,
                    cuit_representante
                
                )
                
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                
                """;

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setString(1, proyectoProductivo.nombre());

            st.setString(2, proyectoProductivo.objeto());

            st.setString(3, proyectoProductivo.descripcionServicio());

            st.setString(4, proyectoProductivo.emplazamiento());

            st.setString(5, proyectoProductivo.tipoPersonal());

            st.setInt(6, proyectoProductivo.tiempoRadicacion());

            st.setDouble(7, proyectoProductivo.metrosCuadrados());

            st.setDouble(8, proyectoProductivo.areaTrabajo());

            st.setDouble(9, proyectoProductivo.areaDeposito());

            st.setDouble(10, proyectoProductivo.estacionamiento());

            st.setBoolean(11, proyectoProductivo.tienePlanos());

            st.setInt(12, proyectoProductivo.personalOcupar());

            st.setString(13, proyectoProductivo.materiasPrimas());

            st.setString(14, proyectoProductivo.destinoProduccion());

            st.setString(15, proyectoProductivo.tension());

            st.setDouble(16, proyectoProductivo.potencia());

            st.setDouble(17, proyectoProductivo.agua());

            st.setBoolean(18, proyectoProductivo.necesitaGas());

            st.setString(19, proyectoProductivo.residuos());

            st.setBoolean(20, proyectoProductivo.realizaTratamiento());

            st.setBoolean(21, proyectoProductivo.necesitaBalanza());

            st.setBoolean(22, proyectoProductivo.necesitaComedor());

            st.setBoolean(23, proyectoProductivo.necesitaCoworking());

            st.setBoolean(24, proyectoProductivo.enEjecucion());

            st.setString(25,
                    proyectoProductivo
                            .representanteEmpresa()
                            .dni()
            );

            int filas = st.executeUpdate();

            if (filas <= 0) {
                throw new RuntimeException("Error al registrar proyecto productivo");
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al registrar proyecto productivo",
                    e
            );
        }
    }

    @Override
    public ProyectoProductivo find(int idProyecto) {

        final String SQL = "SELECT * FROM ProyectoProductivo WHERE id = ? ";

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setInt(1, idProyecto);

            var rs = st.executeQuery();

            if (rs.next()) {


                RepresentanteEmpresa representante =
                        new database.JDBCs.ReprecentanteEmpresaDAOJDBC()
                                .find(rs.getString("dni_representante"));

                return new ProyectoProductivo(

                        rs.getInt("id"),

                        rs.getString("nombre"),

                        rs.getString("objeto"),

                        rs.getString("descripcion_servicio"),

                        rs.getString("emplazamiento"),

                        rs.getString("tipo_personal"),

                        rs.getInt("tiempo_radicacion"),

                        rs.getDouble("metros_cuadrados"),

                        rs.getDouble("area_trabajo"),

                        rs.getDouble("area_deposito"),

                        rs.getDouble("estacionamiento"),

                        rs.getBoolean("tiene_planos"),

                        rs.getInt("personal_ocupar"),

                        rs.getString("materias_primas"),

                        rs.getString("destino_produccion"),

                        rs.getString("tension"),

                        rs.getDouble("potencia"),

                        rs.getDouble("agua"),

                        rs.getBoolean("necesita_gas"),

                        rs.getString("residuos"),

                        rs.getBoolean("realiza_tratamiento"),

                        rs.getBoolean("necesita_balanza"),

                        rs.getBoolean("necesita_comedor"),

                        rs.getBoolean("necesita_coworking"),

                        representante,

                        rs.getBoolean("en_ejecucion")
                );
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al buscar proyecto",
                    e
            );
        }
    }

    @Override
    public List<ProyectoProductivo> findAll() {

        final String SQL = "SELECT * FROM ProyectoProductivo ";

        List<ProyectoProductivo> proyectos = new ArrayList<>();

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            var rs = st.executeQuery();

            while (rs.next()) {


                RepresentanteEmpresa representante =
                        new database.JDBCs.ReprecentanteEmpresaDAOJDBC()
                                .find(rs.getString("dni_representante"));
                ProyectoProductivo proyecto = new ProyectoProductivo(

                        rs.getInt("id"),

                        rs.getString("nombre"),

                        rs.getString("objeto"),

                        rs.getString("descripcion_servicio"),

                        rs.getString("emplazamiento"),

                        rs.getString("tipo_personal"),

                        rs.getInt("tiempo_radicacion"),

                        rs.getDouble("metros_cuadrados"),

                        rs.getDouble("area_trabajo"),

                        rs.getDouble("area_deposito"),

                        rs.getDouble("estacionamiento"),

                        rs.getBoolean("tiene_planos"),

                        rs.getInt("personal_ocupar"),

                        rs.getString("materias_primas"),

                        rs.getString("destino_produccion"),

                        rs.getString("tension"),

                        rs.getDouble("potencia"),

                        rs.getDouble("agua"),

                        rs.getBoolean("necesita_gas"),

                        rs.getString("residuos"),

                        rs.getBoolean("realiza_tratamiento"),

                        rs.getBoolean("necesita_balanza"),

                        rs.getBoolean("necesita_comedor"),

                        rs.getBoolean("necesita_coworking"),

                        representante,

                        rs.getBoolean("en_ejecucion")
                );

                proyectos.add(proyecto);
            }

            return proyectos;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al obtener proyectos",
                    e
            );
        }
    }

    @Override
    public List<ProyectoProductivo> findByRepresentante(RepresentanteEmpresa representanteEmpresa) {

        final String SQL = " SELECT * FROM ProyectoProductivo WHERE cuit_representante = ? ";

        List<ProyectoProductivo> proyectos = new ArrayList<>();

        try (
                Connection conn = ConnectionManager.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL)
        ) {

            st.setString(1, representanteEmpresa.dni());

            var rs = st.executeQuery();

            while (rs.next()) {

                ProyectoProductivo proyecto = new ProyectoProductivo(

                        rs.getInt("id"),

                        rs.getString("nombre"),

                        rs.getString("objeto"),

                        rs.getString("descripcion_servicio"),

                        rs.getString("emplazamiento"),

                        rs.getString("tipo_personal"),

                        rs.getInt("tiempo_radicacion"),

                        rs.getDouble("metros_cuadrados"),

                        rs.getDouble("area_trabajo"),

                        rs.getDouble("area_deposito"),

                        rs.getDouble("estacionamiento"),

                        rs.getBoolean("tiene_planos"),

                        rs.getInt("personal_ocupar"),

                        rs.getString("materias_primas"),

                        rs.getString("destino_produccion"),

                        rs.getString("tension"),

                        rs.getDouble("potencia"),

                        rs.getDouble("agua"),

                        rs.getBoolean("necesita_gas"),

                        rs.getString("residuos"),

                        rs.getBoolean("realiza_tratamiento"),

                        rs.getBoolean("necesita_balanza"),

                        rs.getBoolean("necesita_comedor"),

                        rs.getBoolean("necesita_coworking"),

                        representanteEmpresa,

                        rs.getBoolean("en_ejecucion")
                );

                proyectos.add(proyecto);
            }

            return proyectos;

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al buscar proyectos del representante",
                    e
            );
        }
    }
}