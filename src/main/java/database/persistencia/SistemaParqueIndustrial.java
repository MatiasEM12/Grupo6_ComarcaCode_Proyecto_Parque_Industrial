package database.persistencia;

import model.*;
import model.DTO.EmpresaDTO;
import model.DTO.LoteDTO;
import model.DTO.ObservacionDTO;
import model.DTO.SolicitudRadicacionDTO;

import java.util.List;

public interface SistemaParqueIndustrial {

    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorUsername(String username);
    void agregarSolicitud(SolicitudRadicacionDTO solicitud);
    List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario);
    List<SolicitudRadicacion> obtenerSolicitudes();

    List<Lote> obtenerLotesDisponibles();
    void aprobarSolicitudFinal(int idSolicitud, int idLote);
    void aprobarSolicitudPrimeraInstancia(int idSolicitud);
    void rechazarSolicitud(int idSolicitud);

    //observaciones
    void observarSolicitud(int idSolicitud, String descripcion, String dniAdmin);
    List<ObservacionDTO> obstenerObservacionesSolicitud(int idSolicitud);

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

    ProyectoProductivo obtenerProyectoPorId(int id);

    ProyectoProductivo obtenerProyectoPorLote(int id);

    void agregarDocumentoSolicitud(int idSolicitud, Documento d);

    void cargarDocumento(TipoDocumento tipo, String fileName, String s, long size);

    Documento obtenerDocumentoPorRuta(String ruta);

    Documento obtenerDocumento(int idDocumento);

    ProyectoProductivo obtenerProyecto(int idProyecto);

    int cargarAvanceProyecto(Usuario user, AvanceDeProyecto avance, int idProyecto);

    void cargarDocumentosEnAvance(int idAvance, List<Documento> documentos);

    AvanceDeProyecto obtenerAvance(int idAvance);
}
