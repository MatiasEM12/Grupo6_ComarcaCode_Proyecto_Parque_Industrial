package model;

public class RepresentanteEmpresa {
    //no seria mejor DNI fuera de tipo int?
    private String dni;
    /*yo pondria una variable de tipo Empresa en vez de nombre de empresa, y que de hai recupere el nombre
    Private Empresa empresa;
     */
    private final String nombreEmpresa;
    private final Usuario usuario;

    public RepresentanteEmpresa(String dni, String nombreEmpresa, Usuario usuario){
        validarDni(dni);
        validarNombreEmpresa(nombreEmpresa);
        validarUsuario(usuario);
        this.dni = dni;
        this.nombreEmpresa = nombreEmpresa;
        this.usuario = usuario;
    }

    public String dni(){
        return dni;
    }

    public String nombreEmpresa(){
        return nombreEmpresa;
    }

    public Usuario usuario(){
        return usuario;
    }

    public void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío");
        }
        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener entre 7 y 8 números");
        }
    }

    public void validarNombreEmpresa(String nombreEmpresa){
        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la empresa no puede ser vacío");
        }
    }

    public void validarUsuario(Usuario usuario){
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
    }
}
