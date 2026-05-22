package model;

import model.DTO.SolicitudRadicacionDTO;

import java.util.List;

public interface SistemaParqueIndustrial {

    public List<Usuario> obtenerUsuarios();
    public Usuario obtenerUsuarioPorUsername(String username);
    public void agregarSolicitud(SolicitudRadicacionDTO solicitud);
    public List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario);
    public List<SolicitudRadicacion> obtenerSolicitudes();
}
