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

    }

    @Override
    public void aprobarSolicitudPrimeraInstancia(int idSolicitud) {
        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find( idSolicitud);
        solicitudRadicacion.aprobarPrimeraInstancia();
    }

    public void asignarLote( int idLote,int idProyecto){
        //no pude usarlo , las tablas me tiraban error lote con idProyecto y Proyecto con idLote
        //al estar como "cruzados" no me dejaba crear las tablas asi que Lote no tiene idProyecto
        //el solicitud.aprobar lote cambia a Ocupado y llama a lote.update
        loteDAO.registrarProyectoLote(idLote,idProyecto);

    }



    @Override
    public void rechazarSolicitud(int idSolicitud) {
        solicitudRadicacionDAO.estadoSolicitud(idSolicitud, EstadoSolicitud.RECHAZADA);

       /* final String SQL = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = 'RECHAZADA', fechaActualizacion = CURRENT_DATE " +
                "WHERE id = ?";

        actualizarEstadoSimple(SQL, idSolicitud, "Error al rechazar la solicitud");*/
    }

    //creo que esto es para hacer las observaciones acia el proyecto
    @Override
    public void observarSolicitud(int idSolicitud, String descripcion, String dniAdmin) {
       ObservacionDTO observacion = new ObservacionDTO(idSolicitud, descripcion, dniAdmin);
       observacionesDAO.crear(observacion);
    }

    //private void actualizarEstadoSimple(String sql, int idSolicitud, String mensajeError) {
      /*  try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idSolicitud);

            if (st.executeUpdate() <= 0) {
                throw new RuntimeException("No se encontró la solicitud");
            }

        } catch (SQLException e) {
            throw new RuntimeException(mensajeError, e);
        }*/
   // }
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
    public void agregarLote(Lote lote) {
        loteDAO.create(lote);
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

    /*
    public void estadoSolicitud(Usuario user, SolicitudRadicacionDTO solicitud, EstadoSolicitud estado){

        /*
        admin = AdminParqueDAO.obtenerUsuarioPorUsername(user.UserName())
        solicitud = SolicitudRadicacionDAO.find(solicitudDTO.id()/numeroTramite)

        admin.modificarEstadoSolicitud( soliciud,estado)  //acá dentro filtra si es aprobada llama a solicidud.aprobar si es otro es solicitud.Algo

        * */
    //}
    //yo lo aria asi
    public void estadoSolicitud(int idSolicitud, EstadoSolicitud estado){
        solicitudRadicacionDAO.estadoSolicitud(idSolicitud, estado);
    }



/*
    public void admActualizarDatosPersonales( Usuario user , AdministradorDelParqueDTO adm){

         *  admin = AdminParqueDAO.obtenerUsuarioPorUsername(user.UserName())
         *  admin.ActualizarDatos(adm)
         *
         *
    }

 */

    public void admActualizarDatosPersonales(AdministradorDelParqueDTO adm){
        administradorDelParqueDAO.actualizarDatosAdministrador(adm);
    }

/*
    public void representanteActualizarDatosPersonales( Usuario user , RepresentanteEmpresaDTO representante){

         *  representante = RepresentanteEmpresaDAO.obtenerUsuarioPorUsername(user.UserName())
         *  representante.ActualizarDatos(representante)
         *
         *
    }

 */

    public void representanteActualizarDatosPersonales(RepresentanteEmpresaDTO representante){
        representanteDAO.actualizarDatosReprecentante(representante);
    }

/*
        public void actualizarDatosDeUsuario(Usuario user, UsuarioDTO usuarioDTO){

         *  usuario = UsuarioDAO.obtenerUsuarioPorUsername(user.UserName())
         *  usuario.ActualizarDatos(usuarioDTO)
         *
         * de modificar el username, tambien se debe actualizar la tabla del (adm/representante)
         *
         *
    }

 */
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
}