package database.persistencia;

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

    void aprobarSolicitud(int idSolicitud);

    void observarSolicitud(int idSolicitud, String descripcion);

    List<ProyectoProductivo> obtenerProyectosProductivos();
}
