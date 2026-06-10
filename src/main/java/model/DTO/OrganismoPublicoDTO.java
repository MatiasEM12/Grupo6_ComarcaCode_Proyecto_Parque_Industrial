package model.DTO;

import model.TipoOrganismo;

public record OrganismoPublicoDTO(
        int saf,
        String nombre,
        TipoOrganismo tipoOrganismo,
        UsuarioDTO usuario
) {
}
