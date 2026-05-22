package database.persistencia;

import model.DTO.LoteDTO;
import model.DTO.SolicitudRadicacionDTO;
import model.ProyectoProductivo;
import model.SolicitudRadicacion;
import model.Usuario;

import java.util.List;

public interface SistemaParqueIndustrial {

    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorUsername(String username);
    void agregarSolicitud(SolicitudRadicacionDTO solicitud);
    List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario);
    List<SolicitudRadicacion> obtenerSolicitudes();

    List<LoteDTO> obtenerLotesDisponibles();
    void aprobarSolicitud(int idSolicitud, int idLote);
    void rechazarSolicitud(int idSolicitud);
    void observarSolicitud(int idSolicitud, String descripcion);

    ProyectoProductivo obtenerProyectoProductivo(int idProyecto);
    public List<ProyectoProductivo> obtenerProyectosProductivos();
}
