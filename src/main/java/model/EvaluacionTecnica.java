package model;

public class EvaluacionTecnica extends Reporte {

    private final String resultado;
    private final String observaciones;

    public EvaluacionTecnica(String descripcion,
                             Usuario generadoPor,
                             String resultado,
                             String observaciones) {

        super(TipoReporte.EVALUACION_TECNICA, descripcion, generadoPor);

        this.resultado = resultado;
        this.observaciones = observaciones;
    }


    public String resultado() {
        return resultado;
    }

    public String observaciones() {
        return observaciones;
    }
}
