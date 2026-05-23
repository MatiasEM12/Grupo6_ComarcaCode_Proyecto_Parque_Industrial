package model;

import database.DAOs.ProyectoProductivoDAO;
import database.JDBCs.ProyectoProductivoDAOJDBC;

public class ProyectoProductivo {


    private int id;
    private String nombre;
    private String objeto;
    private String descripcionServicio;
    private String emplazamiento;
    private String tipoPersonal;
    private int tiempoRadicacion;
    private double metrosCuadrados;
    private double areaTrabajo;
    private double areaDeposito;
    private double estacionamiento;
    private boolean tienePlanos;
    private int personalOcupar;
    private String materiasPrimas;
    private String destinoProduccion;
    private String tension;
    private double potencia;
    private double agua;
    private boolean necesitaGas;
    private String residuos;
    private boolean realizaTratamiento;
    private boolean necesitaBalanza;
    private boolean necesitaComedor;
    private boolean necesitaCoworking;
    private boolean enEjecucion;
    private RepresentanteEmpresa representanteEmpresa;

    private ProyectoProductivoDAO proyectoProductivoDAO= new ProyectoProductivoDAOJDBC();

    public ProyectoProductivo(
            String nombre,
            String objeto,
            String descripcionServicio,
            String emplazamiento,
            String tipoPersonal,
            int tiempoRadicacion,
            double metrosCuadrados,
            double areaTrabajo,
            double areaDeposito,
            double estacionamiento,
            boolean tienePlanos,
            int personalOcupar,
            String materiasPrimas,
            String destinoProduccion,
            String tension,
            double potencia,
            double agua,
            boolean necesitaGas,
            String residuos,
            boolean realizaTratamiento,
            boolean necesitaBalanza,
            boolean necesitaComedor,
            boolean necesitaCoworking,
            RepresentanteEmpresa representanteEmpresa



    ) {
        this.nombre = nombre;
        this.objeto = objeto;
        this.descripcionServicio = descripcionServicio;
        this.emplazamiento = emplazamiento;
        this.tipoPersonal = tipoPersonal;
        this.tiempoRadicacion = tiempoRadicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.areaTrabajo = areaTrabajo;
        this.areaDeposito = areaDeposito;
        this.estacionamiento = estacionamiento;
        this.tienePlanos = tienePlanos;
        this.personalOcupar = personalOcupar;
        this.materiasPrimas = materiasPrimas;
        this.destinoProduccion = destinoProduccion;
        this.tension = tension;
        this.potencia = potencia;
        this.agua = agua;
        this.necesitaGas = necesitaGas;
        this.residuos = residuos;
        this.realizaTratamiento = realizaTratamiento;
        this.necesitaBalanza = necesitaBalanza;
        this.necesitaComedor = necesitaComedor;
        this.necesitaCoworking = necesitaCoworking;
        this.representanteEmpresa = representanteEmpresa;

        this.enEjecucion = false;
        // this.proyectoProductivoDAO.cargar(this);
    }

    //con id

    public ProyectoProductivo(
            int id,
            String nombre,
            String objeto,
            String descripcionServicio,
            String emplazamiento,
            String tipoPersonal,
            int tiempoRadicacion,
            double metrosCuadrados,
            double areaTrabajo,
            double areaDeposito,
            double estacionamiento,
            boolean tienePlanos,
            int personalOcupar,
            String materiasPrimas,
            String destinoProduccion,
            String tension,
            double potencia,
            double agua,
            boolean necesitaGas,
            String residuos,
            boolean realizaTratamiento,
            boolean necesitaBalanza,
            boolean necesitaComedor,
            boolean necesitaCoworking,
            RepresentanteEmpresa representanteEmpresa,
            boolean enEjecucion

    ) {


        this.id=id;
        this.nombre = nombre;
        this.objeto = objeto;
        this.descripcionServicio = descripcionServicio;
        this.emplazamiento = emplazamiento;
        this.tipoPersonal = tipoPersonal;
        this.tiempoRadicacion = tiempoRadicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.areaTrabajo = areaTrabajo;
        this.areaDeposito = areaDeposito;
        this.estacionamiento = estacionamiento;
        this.tienePlanos = tienePlanos;
        this.personalOcupar = personalOcupar;
        this.materiasPrimas = materiasPrimas;
        this.destinoProduccion = destinoProduccion;
        this.tension = tension;
        this.potencia = potencia;
        this.agua = agua;
        this.necesitaGas = necesitaGas;
        this.residuos = residuos;
        this.realizaTratamiento = realizaTratamiento;
        this.necesitaBalanza = necesitaBalanza;
        this.necesitaComedor = necesitaComedor;
        this.necesitaCoworking = necesitaCoworking;
        this.representanteEmpresa = representanteEmpresa;
        this.enEjecucion = enEjecucion;
    }


    public void actualizarEstado(){

    }

    public String getNombre() {
        return nombre;
    }



    public boolean isEnEjecucion() {
        return enEjecucion;
    }

    public boolean estaEnEjecucion(){
        if (!enEjecucion){
            return false;
        }
        return true;
    }

    public String nombre(){
        return nombre;
    }

    public String descripcion(){
        return descripcionServicio;
    }

    public double superficie(){
        return metrosCuadrados;
    }

    //geters sin get al inicio
    public int id() {
        return id;
    }

        public String objeto() {
            return objeto;
        }

        public String emplazamiento() {
            return emplazamiento;
        }

        public String tipoPersonal() {
            return tipoPersonal;
        }

        public int tiempoRadicacion() {
            return tiempoRadicacion;
        }

        public double metrosCuadrados() {
            return metrosCuadrados;
        }

        public double areaTrabajo() {
            return areaTrabajo;
        }

        public double areaDeposito() {
            return areaDeposito;
        }

        public double estacionamiento() {
            return estacionamiento;
        }

        public boolean tienePlanos() {
            return tienePlanos;
        }

        public int personalOcupar() {
            return personalOcupar;
        }

        public String materiasPrimas() {
            return materiasPrimas;
        }

        public String destinoProduccion() {
            return destinoProduccion;
        }

        public String tension() {
            return tension;
        }

        public double potencia() {
            return potencia;
        }

        public double agua() {
            return agua;
        }

        public boolean necesitaGas() {
            return necesitaGas;
        }

        public String residuos() {
            return residuos;
        }

        public boolean realizaTratamiento() {
            return realizaTratamiento;
        }

        public boolean necesitaBalanza() {
            return necesitaBalanza;
        }

        public boolean necesitaComedor() {
            return necesitaComedor;
        }

        public boolean necesitaCoworking() {
            return necesitaCoworking;
        }

        public boolean enEjecucion(){
            return enEjecucion;
        }

    public RepresentanteEmpresa representanteEmpresa() {
        return representanteEmpresa;
    }

    public Empresa empresa() {
        return representanteEmpresa.Empresa();
    }
}
