package model;


import java.util.List;

public class OrganismoPublico extends Usuario{
    private String nombre;
    private TipoOrganismo tipoOrganismo;
    private SistemaParque sistema;
    private List<EvaluacionTecnica> evaluacionTecnicas;
    public OrganismoPublico(String username, String contraseña, String gmail,
                            String nombre, TipoOrganismo tipoOrganismo,SistemaParque sistema) {
        super(username, contraseña, new Rol("Organismo-Publico",2), gmail);
        this.nombre = nombre;
        this.tipoOrganismo = tipoOrganismo;
        this.sistema=sistema;
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
    }
}
