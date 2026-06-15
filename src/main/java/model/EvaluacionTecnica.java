package model;

public class EvaluacionTecnica extends Informe {

    private final String resultado;
    private final String observaciones;

    public EvaluacionTecnica(String descripcion, Usuario generadoPor, String resultado, String observaciones) {

        super(TipoInforme.EVALUACION_TECNICA, descripcion, generadoPor);

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
