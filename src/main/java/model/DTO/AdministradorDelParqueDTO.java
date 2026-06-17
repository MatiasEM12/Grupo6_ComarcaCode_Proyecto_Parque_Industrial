package model.DTO;


public class AdministradorDelParqueDTO {
    String dni;
    String nombre;
    UsuarioDTO usuario;

    public AdministradorDelParqueDTO(UsuarioDTO usuario, String dni, String nombre){
        this.nombre = nombre;
        this.usuario = usuario;
        this.dni = dni;
    }
    public String dni(){
        return dni;
    }

    public String nombre(){
        return nombre;
    }

    public UsuarioDTO usuario(){
        return usuario;
    }
}
