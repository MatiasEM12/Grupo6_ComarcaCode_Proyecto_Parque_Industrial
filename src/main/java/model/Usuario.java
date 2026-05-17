package model;

import database.DAOs.UsuarioDAO;
import database.JDBCs.UsuarioDAOJDBC;

public class Usuario {
    private String userName;
    private String contrasena;
    private Rol rol;
    private String gmail;
   private UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();

    public Usuario(String userName, String contrasena, Rol rol, String gmail){
        validarName(userName);
        validarContracena(contrasena);
        validarGmail(gmail);
        validarRol(rol);
        this.userName=userName;
        this.contrasena = contrasena;
        this.rol = rol;
        this.gmail = gmail;

        autenticar(this);
    }


    private void validarName(String userName){
        if(userName == null)throw new RuntimeException("El nombre de usuario no puede ser null");
        if(userName.trim().isEmpty())throw new RuntimeException("El nombre de usuario no puede ser vacio");
        if(userName.length()<3)throw new RuntimeException("El nombre de usuario debe tener al menos 3 caracteres");
        if(userName.length()>20)throw new RuntimeException("El nombre de usuario no puede tener mas de 20 caracteres");
       // if(existe(userName))throw new RuntimeException("El nombre de usuario ya existe");
    }

    private void validarContracena(String contrasena){
        if(contrasena == null )throw new RuntimeException("La contraseña no puede ser null");
        if(contrasena.trim().isEmpty())throw new RuntimeException("La contraseña no puede ser vacia");
        if(contrasena.length()<6)throw new RuntimeException("La contraseña debe tener al menos 6 caracteres");
        if(contrasena.length()>20)throw new RuntimeException("La contraseña no puede tener mas de 20 caracteres");

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

    public String contrasena() {
        return contrasena;
    }

    public Rol rol(){
        return rol;
    }

    public String gmail(){
        return gmail;
    }

    public void autenticar(Usuario usuario){
        if(!existe(usuario.userName)){
            this.usuarioDAO.registrar(this);
        }else{
            //throw new RuntimeException("El usuario ya existe");
        }
    }

    private Boolean existe(String userName){
        return usuarioDAO.existe(userName);
    }

    public String nombreRol() {
        return rol.toString();
    }
}
