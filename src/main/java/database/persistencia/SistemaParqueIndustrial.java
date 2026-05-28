package database.persistencia;

import model.*;
import model.DTO.EmpresaDTO;
import model.DTO.LoteDTO;
import model.DTO.SolicitudRadicacionDTO;

import java.util.List;

public interface SistemaParqueIndustrial {

    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorUsername(String username);
    void agregarSolicitud(SolicitudRadicacionDTO solicitud);
    List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario);
    List<SolicitudRadicacion> obtenerSolicitudes();

    List<Lote> obtenerLotesDisponibles();
    void aprobarSolicitud(int idSolicitud, int idLote);
    void rechazarSolicitud(int idSolicitud);
    void observarSolicitud(int idSolicitud, String descripcion);
    List<SolicitudRadicacion> obtenerSolicitudesDe(String userName);
    ProyectoProductivo obtenerProyectoProductivo(int idProyecto);
    List<ProyectoProductivo> obtenerProyectosProductivos();

    List<Lote> ObtenerLotes();
    Lote obtenerLote(int id);

    void actualizarLote(Lote lote);
    void agregarLote(Lote lote);

    AdministradorDelParque obtenerAdm(String s);

    void actualizarEmpresa(EmpresaDTO empresa);

    List<ProyectoProductivo> obtenerProyectosProductivosDe(String s);
}
