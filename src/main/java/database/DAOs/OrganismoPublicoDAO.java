package database.DAOs;

import model.OrganismoPublico;
import model.DTO.OrganismoPublicoDTO;

public interface OrganismoPublicoDAO {
    void registrarOrganismoPublico(OrganismoPublico organismoPublico);

    OrganismoPublico obtenerOrganismoPorUsername(String username);

    void actualizarDatosOrganismo(OrganismoPublicoDTO organismoPublicoDTO);
}
