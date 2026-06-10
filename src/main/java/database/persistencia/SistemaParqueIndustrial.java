package database.persistencia;

import model.*;
import model.DTO.*;

import java.util.List;

public interface SistemaParqueIndustrial {

    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorUsername(String username);
    void agregarSolicitud(SolicitudRadicacionDTO solicitud);
    List<SolicitudRadicacion> obtenerSolicitudes();

    List<Lote> obtenerLotesDisponibles();
    void aprobarSolicitudFinal(int idSolicitud, int idLote);
    void aprobarSolicitudPrimeraInstancia(int idSolicitud);
    void rechazarSolicitud(int idSolicitud);

    //observaciones
    void observarSolicitud(int idSolicitud, String descripcion, String dniAdmin);

    List<SolicitudRadicacion> obtenerSolicitudesDe(String userName);
    ProyectoProductivo obtenerProyectoProductivo(int idProyecto);
    List<ProyectoProductivo> obtenerProyectosProductivos();

    List<Lote> ObtenerLotes();
    Lote obtenerLote(int id);

    void actualizarLote(Lote lote);
    void agregarLote(Lote lote);

    AdministradorDelParque obtenerAdm(String s);


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

    void agregarEvaluacionTecnica(int idProyecto, EvaluacionTecnica evaluacion);
    List<EvaluacionTecnicaDTO> obtenerEvaluacionesTecnicasPorProyecto(int idProyecto);

    EvaluacionTecnicaDTO obtenerEvaluacionTecnica(int idEvaluacion);

    Usuario login(String username, String password);

    void registrarAdmin(String username, String password, Rol admin, String gmail, String dniAdmin, String nombreAdmin);

    void registrarOrganismoPrublico(String username, String password, String gmail, int saf, String nombreOrg, TipoOrganismo tipoOrganismo);

    void registrarRepresentanteEmpresa(String cuit, String razonSocial, String contacto, String contactoRep, boolean b, String username, String password, Rol representante, String gmail, String dniRep, boolean b1);

    void crearObservacionSolicitud(int idSolicitud, String observacion, Usuario usuario);
    ReporteParqueDTO generarReporteParque();
}
