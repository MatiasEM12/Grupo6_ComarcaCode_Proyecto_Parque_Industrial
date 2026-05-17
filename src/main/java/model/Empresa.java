package model;

import java.util.ArrayList;

public class Empresa {

    private final String cuit;
    private String razonSocial;
    private String contacto;
    private String contactoRepresentante;
    private Boolean radicada;
    private Lote lote;
    private ArrayList<SolicitudRadicacion> solicitudes=new ArrayList<>();;
    private ArrayList<ProyectoProductivo> proyectos=new ArrayList<>();;
    private ArrayList<RepresentanteEmpresa> representantes= new ArrayList<>();

    public Empresa(String cuit, String razonSocial, String contacto, String contactoRepresentante, Boolean radicada, Lote lote,RepresentanteEmpresa representante) {

        validarObligatorio(cuit, "El CUIT es obligatorio");

        validarObligatorio(razonSocial, "La razón social es obligatoria");

        validarObligatorio(contacto, "El contacto es obligatorio");

        validarObligatorio(contactoRepresentante, "El contacto del representante es obligatorio");

        this.agregarRepresentante(representante);
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.contactoRepresentante = contactoRepresentante;
        this.radicada = radicada;
        this.lote = lote;
    }

    public void agregarRepresentante(RepresentanteEmpresa representante) {

        if (representante == null) {
           // throw new RuntimeException("El representante no puede ser nulo");
        }

        if (representantes == null) {
            representantes = new ArrayList<>();
        }

        representantes.add(representante);
    }

    public void asignarLote(Lote lote) {

        if (lote == null) {
            throw new RuntimeException(
                    "El lote no puede ser nulo"
            );
        }

        this.lote = lote;
    }

    public void guardarEmpresa(EmpresaCargar empresa) {

        if (empresa == null) {
            throw new RuntimeException(
                    "La estrategia de guardado no puede ser nula"
            );
        }

        empresa.guardar(this);
    }

    public void modificarDato(EmpresaCargar empresa) {

        if (!this.radicada) {
            throw new RuntimeException(
                    "No se pueden modificar los datos de una empresa no radicada"
            );
        }

        empresa.aplicarModificacion(this);
    }

    public void cambiarEstadoRadicada(boolean esRadicada) {
        this.radicada = esRadicada;
    }

    private void validarObligatorio(
            String valor,
            String mensaje
    ) {

        if (valor == null || valor.isBlank()) {
            throw new RuntimeException(mensaje);
        }
    }

    public String cuit() {
        return cuit;
    }

    public String razonSocial() {
        return razonSocial;
    }

    public String contacto() {
        return contacto;
    }

    public String contactoRepresentante() {
        return contactoRepresentante;
    }

    public Boolean esRadicada() {
        return radicada;
    }

    public Lote lote() {
        return lote;
    }

    public RepresentanteEmpresa representante() {
        return representante();
    }

    public String nombre() {
        return this.nombre();
    }
}