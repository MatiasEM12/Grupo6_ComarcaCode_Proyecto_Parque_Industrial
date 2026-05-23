package model.DTO;

public record RepresentanteEmpresaDTO(
        String dni,
        EmpresaDTO empresa,
        UsuarioDTO usuario,
        Boolean puedeIngresarSolicitud
) {
}