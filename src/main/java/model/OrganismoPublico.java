package model;


import main.Sistema;
import persistencia.PersistenceApi;

import java.util.List;

public class OrganismoPublico extends Usuario{
    private String nombre;
    private TipoOrganismo tipoOrganismo;
    private Sistema sistema;

    private List<EvaluacionTecnica> evaluacionTecnicas;
    public OrganismoPublico(String username, String contraseña, String gmail,
                            String nombre, TipoOrganismo tipoOrganismo, Sistema sistema) {
        super(username, contraseña, new Rol("Organismo-Publico",2), gmail);

        //modificar rol de administrador y de reprecentante de parque para que se cree en el el super
        this.nombre = nombre;
        this.tipoOrganismo = tipoOrganismo;
        this.sistema=sistema;
    }/*
    public String consultarProyectoProductivo(ProyectoProductivo proyecto) {
        return """
                INFORMACIÓN DEL PROYECTO PRODUCTIVO
                -----------------------------------
                Nombre: %s
                Descripción: %s
                Superficie: %s
                Necesidades: %s
                Empleabilidad: %s
                Materia prima: %s
                """.formatted(
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getSuperficie(),
                proyecto.getNecesidades(),
                proyecto.getEmpleabilidad(),
                proyecto.getMateriaPrima()
        );
    }
    public Reporte consultarInformacion(TipoReporte tipoReporte) {

        switch (tipoReporte) {

            case DESARROLLO_PRODUCTIVO -> {
                var proyectos = sistema.obtenerProyectosEnEjecucion();

                return new Reporte(
                        tipoReporte,
                        "Cantidad de proyectos en ejecución: " + proyectos.size(),
                        this
                );
            }

            case NIVEL_ACTIVIDAD_INDUSTRIAL -> {
                int nivel = sistema.nivelActividadIndustrial();

                return new Reporte(
                        tipoReporte,
                        "Nivel de actividad industrial: " + nivel,
                        this
                );
            }

            case PROYECTOS_EN_EJECUCION -> {
                var proyectos = sistema.obtenerProyectosEnEjecucion();

                return new Reporte(
                        tipoReporte,
                        "Proyectos activos: " + proyectos.size(),
                        this
                );
            }

            default -> {
                return new Reporte(
                        TipoReporte.GENERAL,
                        "Información general del parque",
                        this
                );
            }
        }
    }

    public Reporte generarReporte(TipoReporte tipoReporte, String descripcion) {
        return new Reporte(
                tipoReporte,
                descripcion,
                this
        );
    }

    public EvaluacionTecnica registrarEvaluacion() {
        return null;
    }*/
/*
    public void registrarUsuario() {
        //seria algo asi para registrar el organismo publico
        //persistenceApi.organismoPublicoDAO().registrarOrgnismopublicDAO(this);
    }

 */
}
