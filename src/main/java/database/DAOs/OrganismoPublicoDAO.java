package database.DAOs;

import model.DTO.OrganismoPublicoDTO;
import model.OrganismoPublico;

public interface OrganismoPublicoDAO {
    void registrarOrganismoPublico(OrganismoPublico organismoPublico);

    OrganismoPublico obtenerOrganismoPorUsername(String username);

    void actualizarDatosOrganismo(OrganismoPublicoDTO organismoPublicoDTO);
}
