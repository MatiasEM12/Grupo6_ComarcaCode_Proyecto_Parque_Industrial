
package model.DTO;

public record UsuarioDTO(
        String userName,
        String contrasena,
        RolDTO rol,
        String gmail
) {
    public UsuarioDTO(String userName, String contrasena, RolDTO rol, String gmail) {
        this.userName = userName;
        this.contrasena = contrasena;
        this.rol = rol;
        this.gmail = gmail;
    }

    public String getUserName(){
        return userName;
    }

    public String getConctrasena(){
        return contrasena;
    }

    public RolDTO getRol(){
        return rol;
    }

    public String getGmail(){
        return gmail;
    }

}
