package database.persistencia;



import database.DAOs.*;
import database.JDBCs.*;

import model.*;
import model.DTO.*;

import java.util.ArrayList;
import java.util.List;


public class ParqueIndustrial implements SistemaParqueIndustrial {

    private final UsuarioDAO usuarioDAO= new UsuarioDAOJDBC();
    private final RepresentanteEmpresaDAO representanteDAO= new RepresentanteEmpresaDAOJDBC();
    private final SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();
    private final ProyectoProductivoDAO proyectoProductivoDAO = new ProyectoProductivoDAOJDBC();
    private final LoteDAO loteDAO = new LoteDAOJDBC();
    private final AdministradorDelParqueDAO administradorDelParqueDAO = new AdministradorDelParqueDAOJDBC();
    private final AvanceDeProyectoDAO avanceDeProyectoDAO = new AvanceDeProyectoDAOJDBC();
    private final DocumentoDAO documentoDAO = new DocumentoDAOJDBC();
    private final ObservacionesDAO observacionesDAO = new ObservacionesDAOJDBC();
    private final EvaluacionTecnicaDAO evaluacionTecnicaDAO = new EvaluacionTecnicaDAOJDBC();
    private final UsuarioDAO userDAO = new UsuarioDAOJDBC();
    private final RolDAO rolDAO = new RolDAOJDBC();
    private final OrganismoPublicoDAO organismoPublicoDAO = new OrganismoPublicoDAOJDBC();
    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        ArrayList<Usuario> usuarios = new ArrayList<>(obtenerUsuarios());
        return usuarios.stream()
                .filter(u -> u.UserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregarSolicitud(
            SolicitudRadicacionDTO solicitud) {

        RepresentanteEmpresa representante = representanteDAO.findByUserName(solicitud.usuario().UserName());

        SolicitudRadicacion solicitudRadicacion = new SolicitudRadicacion(representante, solicitud.objeto(), solicitud.nombreProyecto(),
                solicitud.descripcionServicio(), solicitud.emplazamiento(), solicitud.personal(), solicitud.tiempoRadicacion(), solicitud.m2(), solicitud.areaTrabajo(),
                solicitud.areaDeposito(), solicitud.estacionamiento(), solicitud.planos(), solicitud.empleabilidad(), solicitud.materiasPrimas(),
                solicitud.destinoProduccion(), solicitud.tension(), solicitud.potencia(), solicitud.agua(), solicitud.gas(),
                solicitud.residuos(), solicitud.tratamiento(), solicitud.balanza(), solicitud.comedor(), solicitud.coworking(),
                solicitud.descripcionArchivo(), solicitud.nombreArchivoPDF()
                );
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudesDe(String userName) {

        RepresentanteEmpresa representanteEmpresa =
                representanteDAO.findByUserName(userName);

        if (representanteEmpresa == null) {
            return List.of();
        }

        return obtenerSolicitudes()
                .stream()
                .filter(s -> s.representante() != null)
                .filter(s -> s.representante().dni()
                        .equals(representanteEmpresa.dni()))
                .toList();
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return this.solicitudRadicacionDAO.findAll();
    }

    @Override
    public List<Lote> obtenerLotesDisponibles() {
        return loteDAO.findDisponibles();
    }

    @Override
    public void aprobarSolicitudFinal(int idSolicitud, int idLote) {

        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find( idSolicitud);
        Lote lote = loteDAO.find(idLote);

        solicitudRadicacion.aprobarFinal(lote);
        cargarDocumentosEnProyecto(idSolicitud);

    }

    @Override
    public void aprobarSolicitudPrimeraInstancia(int idSolicitud) {
        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find( idSolicitud);
        solicitudRadicacion.aprobarPrimeraInstancia();


    }

    private void cargarDocumentosEnProyecto(int idSolicitud){
        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find( idSolicitud);
        ProyectoProductivo proyectoProductivo = solicitudRadicacion.proyecto();
        proyectoProductivo.cargarDocumentos(solicitudRadicacion.documentos());
    }




    @Override
    public void rechazarSolicitud(int idSolicitud) {
        solicitudRadicacionDAO.estadoSolicitud(idSolicitud, EstadoSolicitud.RECHAZADA);

    }


    @Override
    public void observarSolicitud(int idSolicitud, String descripcion, String dniAdmin) {
       Observacion observacion = new Observacion(idSolicitud, descripcion, dniAdmin);
       observacionesDAO.crear(observacion);
    }


    @Override
    public ProyectoProductivo obtenerProyectoProductivo(int idProyecto) {
        return proyectoProductivoDAO.find(idProyecto);
    }
    @Override
    public List<ProyectoProductivo> obtenerProyectosProductivos() {
        return proyectoProductivoDAO.findAll();
    }

    @Override
    public List<Lote> ObtenerLotes() {
        return loteDAO.findAll();
    }
    @Override
    public Lote obtenerLote(int id) {
        LoteDAO loteDAO = new LoteDAOJDBC();
        return loteDAO.find(id);
    }

    @Override
    public void actualizarLote(Lote lote) {
        LoteDAO loteDAO = new LoteDAOJDBC();
        loteDAO.update(lote);
    }
    @Override
    public void agregarLote(Ubicacion ubicacion, double superficie, String estado, String infraestructura) {
        Lote lote = new Lote( ubicacion, superficie, estado, infraestructura);
    }

    @Override
    public AdministradorDelParque obtenerAdm(String s) {
        return administradorDelParqueDAO.obtenerAdministradorPorUsername(s);
    }


    @Override
    public List<ProyectoProductivo> obtenerProyectosProductivosDe(String s) {
        RepresentanteEmpresa representanteEmpresa= representanteDAO.findByUserName(s);

        return proyectoProductivoDAO.findByEmpresa(representanteEmpresa.empresa.cuit());
    }

    @Override
    public ProyectoProductivo obtenerProyectoPorId(int id) {
        return proyectoProductivoDAO.find(id);
    }

    @Override
    public ProyectoProductivo obtenerProyectoPorLote(int id) {
        List<ProyectoProductivo> proyectos = proyectoProductivoDAO.findAll();

        return proyectos.stream().filter(proyecto -> proyecto.idLote() == id).findFirst().orElse(null);

    }

    @Override
    public void agregarDocumentoSolicitud(int idSolicitud, Documento d) {


        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find(idSolicitud);
        SolicitudDocumentoDAO solicitudDocumentoDAO = new SolicitudDocumentoDAOJDBC();
        solicitudDocumentoDAO.vincular(idSolicitud, d.id());
        solicitudRadicacion.agregarDocumento(d);
    }

    @Override
    public void cargarDocumento(TipoDocumento tipo, String fileName, String s, long size) {

        Documento documento = new Documento(tipo, fileName, s, size);
    }

    @Override
    public Documento obtenerDocumentoPorRuta(String ruta) {
        return documentoDAO.findPorRuta(ruta);
    }

    @Override
    public Documento obtenerDocumento(int idDocumento) {
        return documentoDAO.find(idDocumento);
    }

    @Override
    public ProyectoProductivo obtenerProyecto(int idProyecto) {
        return proyectoProductivoDAO.find(idProyecto);
    }


    public List<LoteDTO> obtenerLotes(){
        List<LoteDTO> lotes = new ArrayList<>();
        lotes = loteDAO.findAllLoteDTO().stream().toList();
        return lotes;
    }

    public void admActualizarDatosPersonales(AdministradorDelParqueDTO adm){
        administradorDelParqueDAO.actualizarDatosAdministrador(adm);
    }


    public void representanteActualizarDatosPersonales(RepresentanteEmpresaDTO representante){
        representanteDAO.actualizarDatosReprecentante(representante);
    }


    public void actualizarDatosDeUsuario(UsuarioDTO usuarioDTO){
        usuarioDAO.update(new Usuario(usuarioDTO.getUserName(), usuarioDTO.contrasena(),
                new Rol(usuarioDTO.getRol().nombre()),usuarioDTO.gmail()));
    }

    @Override
    public int cargarAvanceProyecto(Usuario user, AvanceDeProyecto avance, int idProyecto) {

        RepresentanteEmpresa representante = representanteDAO.findByUserName(user.UserName());

        ProyectoProductivo proyecto = proyectoProductivoDAO.find(idProyecto);

        if(!proyecto.empresa().cuit().equals(representante.cuitEmpresa())) {

            throw new RuntimeException("El proyecto no pertenece al usuario");
        }
        return  proyecto.cargarAvance(avance);
    }

    @Override
    public void cargarDocumentosEnAvance(int idAvance, List<Documento> documentos) {
        AvanceDeProyectoDAO avanceDeProyectoDAO = new AvanceDeProyectoDAOJDBC();
        AvanceDeProyecto avance = avanceDeProyectoDAO.find(idAvance);
        avance.cargarDocumentos(documentos);
    }

    @Override
    public AvanceDeProyecto obtenerAvance(int idAvance) {
        return avanceDeProyectoDAO.find(idAvance);
    }


    private LoteDTO toLote(Lote lote){
       return new LoteDTO(lote.id(), lote.ubicacion().latitud, lote.ubicacion().longitud, lote.ubicacion().altitud,
       lote.superficie(), lote.estado(), lote.infraestructura());
    }

    @Override
    public void agregarEvaluacionTecnica(int idProyecto, EvaluacionTecnica evaluacion) {
        evaluacionTecnicaDAO.create(idProyecto, evaluacion);
    }

    @Override
    public List<EvaluacionTecnicaDTO> obtenerEvaluacionesTecnicasPorProyecto(int idProyecto) {
        return evaluacionTecnicaDAO.findByProyecto(idProyecto);
    }

    @Override
    public EvaluacionTecnicaDTO obtenerEvaluacionTecnica(int idEvaluacion) {
        return evaluacionTecnicaDAO.findById(idEvaluacion);
    }

    @Override
    public Usuario login(String username, String password) {
        UsuarioDAO dao = new UsuarioDAOJDBC();

        Usuario usuario = obtenerUsuarioPorUsername( username);

        if (usuario == null) return null;

        if (!usuario.contrasena().equals(password)) return null;

        return usuario;
    }

    @Override
    public void registrarAdmin(String username, String password, Rol admin, String gmail, String dniAdmin, String nombreAdmin) {

        var administrador = new AdministradorDelParque(username,password,rolDAO.find(1), gmail, dniAdmin, nombreAdmin);
    }

    @Override
    public void registrarOrganismoPrublico(String username, String password, String gmail, int saf, String nombreOrg, TipoOrganismo tipoOrganismo) {

        var orgPublico = new OrganismoPublico(username, password, gmail, saf,nombreOrg ,tipoOrganismo,rolDAO.find(3) );
    }

    @Override
    public void registrarRepresentanteEmpresa(String cuit, String razonSocial, String contacto, String contactoRep, boolean b, String username, String password, Rol representante, String gmail, String dniRep, boolean b1) {

        Empresa empresa = new Empresa(
                cuit,
                razonSocial,
                contacto,
                contactoRep,
                false,true
        );


        Usuario usuario = new Usuario(
                username,
                password,
                rolDAO.find(2),
                gmail
        );


        RepresentanteEmpresa representanteEmpresa = new RepresentanteEmpresa(
                dniRep,
                empresa,
                usuario,
                true
        );

    }

    @Override
    public void crearObservacionSolicitud(int idSolicitud, String observacion, Usuario usuario) {
        AdministradorDelParque administradorDelParque = administradorDelParqueDAO.obtenerAdministradorPorUsername(usuario.UserName());
        Observacion obs = new Observacion(idSolicitud,observacion, administradorDelParque.dni());
    }
    @Override
    public InformeParqueDTO generarReporteParque() {

        List<ProyectoProductivo> proyectos = proyectoProductivoDAO.findAll();
        List<Lote> lotes = loteDAO.findAll();
        List<EvaluacionTecnicaDTO> evaluaciones = evaluacionTecnicaDAO.findAll();

        int proyectosEnEjecucion = (int) proyectos.stream()
                .filter(p -> "EN_EJECUCION".equalsIgnoreCase(p.estado()))
                .count();

        int proyectosFinalizados = (int) proyectos.stream()
                .filter(p -> "FINALIZADO".equalsIgnoreCase(p.estado()))
                .count();

        int proyectosSuspendidos = (int) proyectos.stream()
                .filter(p -> "SUSPENDIDO".equalsIgnoreCase(p.estado()))
                .count();

        int proyectosSinIniciar = (int) proyectos.stream()
                .filter(p -> "SIN_INICIAR".equalsIgnoreCase(p.estado()))
                .count();

        int lotesDisponibles = (int) lotes.stream()
                .filter(l -> "DISPONIBLE".equalsIgnoreCase(l.estado()))
                .count();

        int lotesOcupados = (int) lotes.stream()
                .filter(l -> "OCUPADO".equalsIgnoreCase(l.estado()))
                .count();

        int empleabilidadTotal = proyectos.stream()
                .mapToInt(ProyectoProductivo::empleabilidad)
                .sum();

        double superficieTotalProyectos = proyectos.stream()
                .mapToDouble(ProyectoProductivo::superficie)
                .sum();

        return new InformeParqueDTO(
                java.time.LocalDateTime.now(),
                proyectos.size(),
                proyectosEnEjecucion,
                proyectosFinalizados,
                proyectosSuspendidos,
                proyectosSinIniciar,
                lotes.size(),
                lotesDisponibles,
                lotesOcupados,
                evaluaciones.size(),
                empleabilidadTotal,
                superficieTotalProyectos,
                proyectos
        );
    }

    @Override
    public ProyectoProductivo buscarProyectoPorId(int idProyecto) {
        return proyectoProductivoDAO.find(idProyecto);
    }

    @Override
    public void actualizarDocumento(int idDocumento, String fileName, String s, long size) {
        documentoDAO.actualizarDocumento(idDocumento,fileName,s,size);
    }
    @Override
    public RepresentanteEmpresa obtenerRepresentante(String userName) {
        return representanteDAO.findByUserName(userName);
    }

    @Override
    public Empresa obtenerEmpresaRepresentante(String userName) {
        RepresentanteEmpresa representante = representanteDAO.findByUserName(userName);

        if (representante == null) {
            return null;
        }

        return representante.empresa;
    }
    @Override
    public OrganismoPublico obtenerOrganismo(String userName) {
        return organismoPublicoDAO.findByUserName(userName);
    }
}