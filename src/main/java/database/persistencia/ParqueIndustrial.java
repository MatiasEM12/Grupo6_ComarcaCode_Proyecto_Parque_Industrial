package database.persistencia;



import database.DAOs.*;
import database.JDBCs.*;

import model.*;
import model.DTO.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.ConnectionManager;



public class ParqueIndustrial implements SistemaParqueIndustrial {

    private UsuarioDAO usuarioDAO= new UsuarioDAOJDBC();
    private RepresentanteEmpresaDAO representanteDAO= new RepresentanteEmpresaDAOJDBC();
    private SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();
    private ProyectoProductivoDAO proyectoProductivoDAO = new ProyectoProductivoDAOJDBC();
    private LoteDAO loteDAO = new LoteDAOJDBC();
    private AdministradorDelParqueDAO administradorDelParqueDAO = new AdministradorDelParqueDAOJDBC();
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
    public List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario) {
        List<SolicitudRadicacion> solicitudes = new ArrayList<>(obtenerSolicitudes());
        RepresentanteEmpresa representanteEmpresa= representanteDAO.find(usuario.UserName());

        return solicitudes.stream().filter(s -> s.representante().dni().equals(representanteEmpresa.dni())).toList();
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
    public void aprobarSolicitud(int idSolicitud, int idLote) {

        SolicitudRadicacion solicitudRadicacion = solicitudRadicacionDAO.find( idSolicitud);
        Lote lote = loteDAO.find(idLote);

        solicitudRadicacion.aprobar(lote);

    }

    @Override
    public void rechazarSolicitud(int idSolicitud) {
       /* final String SQL = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = 'RECHAZADA', fechaActualizacion = CURRENT_DATE " +
                "WHERE id = ?";

        actualizarEstadoSimple(SQL, idSolicitud, "Error al rechazar la solicitud");*/
    }

    @Override
    public void observarSolicitud(int idSolicitud, String descripcion) {
       /* if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("La observación no puede estar vacía");
        }

        final String SQL = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = 'OBSERVADA', fechaActualizacion = CURRENT_DATE, observacion = ? " +
                "WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL)) {

            st.setString(1, descripcion);
            st.setInt(2, idSolicitud);

            if (st.executeUpdate() <= 0) {
                throw new RuntimeException("No se encontró la solicitud a observar");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al observar la solicitud", e);
        }*/
    }

    private void actualizarEstadoSimple(String sql, int idSolicitud, String mensajeError) {
      /*  try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idSolicitud);

            if (st.executeUpdate() <= 0) {
                throw new RuntimeException("No se encontró la solicitud");
            }

        } catch (SQLException e) {
            throw new RuntimeException(mensajeError, e);
        }*/
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
    public void agregarLote(Lote lote) {
        loteDAO.create(lote);
    }

    @Override
    public AdministradorDelParque obtenerAdm(String s) {
        return administradorDelParqueDAO.obtenerAdministradorPorUsername(s);
    }

    @Override
    public void actualizarEmpresa(EmpresaDTO empresa) {
        EmpresaDAO empresaDAO = new EmpresaDAOJDBC();
        empresaDAO.actualizar(empresa);
    }

    @Override
    public List<ProyectoProductivo> obtenerProyectosProductivosDe(String s) {
        RepresentanteEmpresa representanteEmpresa= representanteDAO.findByUserName(s);

        return proyectoProductivoDAO.findByEmpresa(representanteEmpresa.empresa.cuit());
    }





    public List<LoteDTO> obtenerLotes(){
        List<LoteDTO> lotes = new ArrayList<>();
        lotes = loteDAO.findAllLoteDTO().stream().toList();
        return lotes;
    }



    public void asignarLote( LoteDTO lote, ProyectoProductivoDTO proyecto){
        loteDAO.registrarProyectoLote(lote.getId(), proyecto.idProyecto());

    }

    public void estadoSolicitud(Usuario user, SolicitudRadicacionDTO solicitud, EstadoSolicitud estado){
        /*
        admin = AdminParqueDAO.obtenerUsuarioPorUsername(user.UserName())
        solicitud = SolicitudRadicacionDAO.find(solicitudDTO.id()/numeroTramite)

        admin.modificarEstadoSolicitud( soliciud,estado)  //acá dentro filtra si es aprobada llama a solicidud.aprobar si es otro es solicitud.Algo

        * */
    }

    public void admActualizarDatosPersonales( Usuario user , AdministradorDelParqueDTO adm){
        /*
         *  admin = AdminParqueDAO.obtenerUsuarioPorUsername(user.UserName())
         *  admin.ActualizarDatos(adm)
         *
         * */
    }

    public void representanteActualizarDatosPersonales( Usuario user , RepresentanteEmpresaDTO representante){
        /*
         *  representante = RepresentanteEmpresaDAO.obtenerUsuarioPorUsername(user.UserName())
         *  representante.ActualizarDatos(representante)
         *
         * */
    }

    public void actualizarDatosDeUsuario(Usuario user, UsuarioDTO usuarioDTO){
        /*
         *  usuario = UsuarioDAO.obtenerUsuarioPorUsername(user.UserName())
         *  usuario.ActualizarDatos(usuarioDTO)
         *
         * de modificar el username, tambien se debe actualizar la tabla del (adm/representante)
         *
         * */
    }

    public void cargarAvanceProyecto( Usuario user, AvanceDeProyectoDTO avance,ProyectoProductivoDTO proyecto){
        /*
         * RepresentanteEmpresa representante= obtenerRepresentantePorUsuario(user);
         *
         * representante.cargarAvance(toAvance(avance), toProyecto(proyecto))
         *                             pasar avanseDTO a avanceProyecto
         * */
        //en estas cosas de dto no se si convertirlo a clase en el caso de Proyecto
        //o hacer proyectoDTO.id  y que representante dentro suyo lo recupere con un proyectoDAO.find(id)
    }




    /*
    private loteDTO toLote(Lote lote){
       return // transformar lote en loteDTO
    }

    * */


}