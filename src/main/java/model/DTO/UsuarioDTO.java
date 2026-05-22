
package model.DTO;

public record UsuarioDTO(
        String userName,
        String contrasena,
        RolDTO rol,
        String gmail
) {
}
