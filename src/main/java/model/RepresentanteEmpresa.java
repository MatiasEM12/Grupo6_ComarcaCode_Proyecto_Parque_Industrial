package model;

import database.DAOs.RepresentanteEmpresaDAO;
import database.JDBCs.RepresentanteEmpresaDAOJDBC;
import model.DTO.EmpresaDTO;

public class RepresentanteEmpresa {

    private String dni;
    public Empresa empresa;
    private Usuario usuario;
    private boolean  puedeIngresarSolicitud=true;
    private RepresentanteEmpresaDAO representanteEmpresaDAO = new RepresentanteEmpresaDAOJDBC();

    public RepresentanteEmpresa(String dni, Empresa empresa, Usuario usuario, boolean puedeIngresarSolicitud){
        validarEmpresa(empresa);
        validarUsuario(usuario);
        this.dni = dni;
        this.empresa = empresa;
        this.usuario = usuario;
        this.puedeIngresarSolicitud=puedeIngresarSolicitud;

        representanteEmpresaDAO.registrarRepresentante(this);
    }

    public RepresentanteEmpresa(String dni, Empresa empresa, Usuario usuario) {
        this.dni = dni;
        this.empresa = empresa;
        this.usuario = usuario;
    }





    public String dni(){
        return dni;
    }

    public String nombreEmpresa(){
        return empresa.nombre();
    }

    public String cuitEmpresa(){
        return empresa.cuit();
    }

    public Usuario usuario(){
        return usuario;
    }
    public void SipuedeIngresarSolicitud(){
        this.puedeIngresarSolicitud=true;
    }

    public void NopuedeIngresarSolicitud(){
        this.puedeIngresarSolicitud=false;
    }

    public Boolean puedeIngresarSolititud(){
        return this.puedeIngresarSolicitud;
    }



    public void actualizarEmpresa(EmpresaDTO empresa){
        validarEmpresaDTO(empresa);
        this.empresa.actualizar(empresa);
    }


// - tengo un poblema, el proyecto actual deberia de ser atributo del representante?
// -  o empresa tiene como atributo el proyecto en desarrollo? this.empresa.proyectoEnDesarrollo()

    //entonces seria this.proyecto.cargarAvance(avance)
    public void cargarAvance(AvanceDeProyecto avance){
        validarAvance(avance);
        avance.cargate();
    }

    public void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío");
        }

        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener entre 7 y 8 números");
        }
    }

    public void validarEmpresa(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
    }

    public void validarUsuario(Usuario usuario){
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
    }

    private void validarSolicitud(SolicitudRadicacion solicitudRadicacion) {
        if (!puedeIngresarSolicitud){
            throw new RuntimeException("No puede ingresar una nueva solicitud");
        }
        if(solicitudRadicacion == null){
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
    }


    private void validarAvance(AvanceDeProyecto avance) {
        if(avance == null){
            throw new IllegalArgumentException("El avance no puede ser nulo");
        }
    }
    private void validarEmpresaDTO(EmpresaDTO empresa) {
        if(empresa == null){
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
    }


    public Empresa empresa() {
        return empresa;
    }
}
