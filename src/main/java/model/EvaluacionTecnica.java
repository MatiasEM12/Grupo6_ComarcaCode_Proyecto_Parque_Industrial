package model;

public class EvaluacionTecnica extends Reporte {

    private String resultado;
    private String observaciones;

    public EvaluacionTecnica(String descripcion,
                             Usuario generadoPor,
                             String resultado,
                             String observaciones) {

        super(TipoReporte.EVALUACION_TECNICA, descripcion, generadoPor);

        this.resultado = resultado;
        this.observaciones = observaciones;
    }
}
