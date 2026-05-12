package model;

public class Usuario {
    private String userName;
    private String contrasena;
    private Rol rol;
    //no se cual de los 2 conviene ya que usamos mailTrap.
    private String gmail;
    /*
    private Gmail gmail;
     */

    public Usuario(String userName, String contrasena, Rol rol, String gmail){
        validarName(userName);
        validarContracena(contrasena);
        validarGmail(gmail);
        validarRol(rol);
        this.userName=userName;
        this.contrasena = contrasena;
        this.rol = rol;
        this.gmail = gmail;
    }

    private void validarName(String userName){
        if(userName == null || userName.trim().isEmpty()){
            throw new RuntimeException("el nombre del usuario es invalido");
        }
    }

    private void validarContracena(String contrasena){
        if(contrasena == null || contrasena.trim().isEmpty()){
            throw new RuntimeException("contrasena es invalida");
        }
    }

    private void validarRol(Rol rol){
        if(rol == null){
            throw new RuntimeException("Rol invalido");
        }
    }

    private void validarGmail(String gmail) {
        if (!checkGmail(gmail)) {
            throw new RuntimeException("El Gmail ingresado es invalido");
        }
    }

    private boolean checkGmail(String gmail) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return gmail.matches(regex);
    }

    public String UserName() {
        return userName;
    }
    public String gmail() {
        return gmail;
    }

    public String rol(){
        return rol.nombre();
    }
}
