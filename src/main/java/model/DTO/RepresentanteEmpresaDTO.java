package model.DTO;

public record RepresentanteEmpresaDTO(
        String dni,
        EmpresaDTO empresa,
        UsuarioDTO usuario,
        Boolean puedeIngresarSolicitud
) {
    public RepresentanteEmpresaDTO(String dni, EmpresaDTO empresa,
                                   UsuarioDTO usuario, Boolean puedeIngresarSolicitud) {
        this.dni = dni;
        this.empresa = empresa;
        this.usuario = usuario;
        this.puedeIngresarSolicitud = puedeIngresarSolicitud;
    }

    public String getDni() {
        return dni;
    }

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public Boolean getPuedeIngresarSolicitud() {
        return puedeIngresarSolicitud;
    }

}