package database.DAOs;

import model.AdministradorDelParque;
import model.DTO.AdministradorDelParqueDTO;

public interface AdministradorDelParqueDAO {
    void registrarAdministrador(AdministradorDelParque administradorDelParque);
    public AdministradorDelParque  obtenerAdministradorPorUsername(String username);
    void actualizarDatosAdministrador(AdministradorDelParqueDTO adm);
}
