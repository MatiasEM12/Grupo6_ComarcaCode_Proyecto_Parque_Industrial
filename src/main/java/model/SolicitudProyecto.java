package model;

public class SolicitudProyecto extends ProyectoProductivo{
    private String objetoProyecto;
    // Traslado total o parcial
    // Elaborar nuevos productos
    // Incrementar la producción total
    private String emplazamientoActual;
    private String personalOcupado;
    // Jerárquico | Producción | Administrativo
    private String tiempoRadicacion;
    //6 Meses | 12 Meses | 24 Meses | 36 o Más
    private String descripcionServicioBien;
    // Propio | Alquilado
    private double necesidadM2;
    private double superficieAreaTrabajo;
    private double superficieAreaDeposito;
    private double superficieEstacionamiento;
    private boolean tienePlanos;
    // este no lo pomgo porque ya esta en proyecto productivo personalAOcupar : Integer
    private String materiasPrimas;
    private String destinoProduccion;
    private String tensionAlimentacion;
    // Media | Baja
    private Double potenciaInstaladaKW;
    //gasto de electrisidad estimado creo que es
    private Double aguaLtsMes;
    private boolean tieneGas;
    private String residuosEfluentes;
    private boolean tratamientoPlanta;
    private boolean necesidadBalanzaPublic;
    private boolean necesidadComedorComunitario;
    private boolean necesidadCoworking;
    private String cuitEmpresa;

    public SolicitudProyecto(String nombre, String descripcion, double superficie,
            String necesidades, int empleabilidad, String materiaPrima,
            String objetoProyecto, String emplazamientoActual, String personalOcupado,
            String tiempoRadicacion, String descripcionServicioBien, double necesidadM2,
            double superficieAreaTrabajo, double superficieAreaDeposito, double superficieEstacionamiento,
            boolean tienePlanos, String materiasPrimas, String destinoProduccion,
            String tensionAlimentacion, Double potenciaInstaladaKW, Double aguaLtsMes, boolean tieneGas,
            String residuosEfluentes, boolean tratamientoPlanta,
            boolean necesidadBalanzaPublic, boolean necesidadComedorComunitario,
            boolean necesidadCoworking, String cuitEmpresa) {

        super(nombre, descripcion, superficie, necesidades, empleabilidad, materiaPrima);

        this.objetoProyecto = objetoProyecto;
        this.emplazamientoActual = emplazamientoActual;
        this.personalOcupado = personalOcupado;
        this.tiempoRadicacion = tiempoRadicacion;
        this.descripcionServicioBien = descripcionServicioBien;
        this.necesidadM2 = necesidadM2;
        this.superficieAreaTrabajo = superficieAreaTrabajo;
        this.superficieAreaDeposito = superficieAreaDeposito;
        this.superficieEstacionamiento = superficieEstacionamiento;
        this.tienePlanos = tienePlanos;
        this.materiasPrimas = materiasPrimas;
        this.destinoProduccion = destinoProduccion;
        this.tensionAlimentacion = tensionAlimentacion;
        this.potenciaInstaladaKW = potenciaInstaladaKW;
        this.aguaLtsMes = aguaLtsMes;
        this.tieneGas = tieneGas;
        this.residuosEfluentes = residuosEfluentes;
        this.tratamientoPlanta = tratamientoPlanta;
        this.necesidadBalanzaPublic = necesidadBalanzaPublic;
        this.necesidadComedorComunitario = necesidadComedorComunitario;
        this.necesidadCoworking = necesidadCoworking;
        this.cuitEmpresa = cuitEmpresa;
    }

}
