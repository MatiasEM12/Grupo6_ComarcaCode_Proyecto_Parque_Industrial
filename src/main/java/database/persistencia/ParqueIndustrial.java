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
    private ProyectoProductivoDAO proyectoProductivoDAO =
            new ProyectoProductivoDAOJDBC();
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
    public void agregarSolicitud(SolicitudRadicacionDTO solicitud) {
        RepresentanteEmpresa representante= representanteDAO.find(solicitud.usuario().UserName());
/*
        //proyecto y solicitud se cargan en la base de datos al crear el objeto, por lo que no es necesario hacer un insert adicional
        ProyectoProductivo proyectoProductivo = this.toProyecto( solicitud.proyecto());
        SolicitudRadicacion solicitudRadicacion=new SolicitudRadicacion(representante,proyectoProductivo);*/
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

        List<Lote> lotes = new ArrayList<>();

        final String SQL =
                "SELECT id, latitud, longitud, altitud, superficie, estado, infraestructura " +
                        "FROM lotes " +
                        "WHERE estado = 'DISPONIBLE'";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(SQL);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {

                Ubicacion ubicacion = new Ubicacion(
                        rs.getLong("latitud"),
                        rs.getLong("longitud"),
                        rs.getLong("altitud")
                );

                Lote lote = new Lote(
                        rs.getInt("id"),
                        ubicacion,
                        rs.getDouble("superficie"),
                        rs.getString("estado"),
                        rs.getString("infraestructura")
                );

                lotes.add(lote);
            }

            return lotes;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lotes disponibles", e);
        }
    }

    @Override
    public void aprobarSolicitud(int idSolicitud, int idLote) {
        final String ACTUALIZAR_SOLICITUD = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = 'APROBADA', fechaActualizacion = CURRENT_DATE, idLote = ? " +
                "WHERE id = ?";

        final String ACTUALIZAR_LOTE = "UPDATE lotes SET estado = 'ASIGNADO' WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stSolicitud = conn.prepareStatement(ACTUALIZAR_SOLICITUD);
                 PreparedStatement stLote = conn.prepareStatement(ACTUALIZAR_LOTE)) {

                stSolicitud.setInt(1, idLote);
                stSolicitud.setInt(2, idSolicitud);
                int filasSolicitud = stSolicitud.executeUpdate();

                stLote.setInt(1, idLote);
                int filasLote = stLote.executeUpdate();

                if (filasSolicitud <= 0 || filasLote <= 0) {
                    conn.rollback();
                    throw new RuntimeException("No se pudo aprobar la solicitud o asignar el lote");
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al aprobar la solicitud", e);
        }
    }

    @Override
    public void rechazarSolicitud(int idSolicitud) {
        final String SQL = "UPDATE SolicitudRadicacion " +
                "SET estadoSolicitud = 'RECHAZADA', fechaActualizacion = CURRENT_DATE " +
                "WHERE id = ?";

        actualizarEstadoSimple(SQL, idSolicitud, "Error al rechazar la solicitud");
    }

    @Override
    public void observarSolicitud(int idSolicitud, String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
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
        }
    }

    private void actualizarEstadoSimple(String sql, int idSolicitud, String mensajeError) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, idSolicitud);

            if (st.executeUpdate() <= 0) {
                throw new RuntimeException("No se encontró la solicitud");
            }

        } catch (SQLException e) {
            throw new RuntimeException(mensajeError, e);
        }
    }
    @Override
    public ProyectoProductivo obtenerProyectoProductivo(int idProyecto) {
        return proyectoProductivoDAO.find(idProyecto);
    }
    @Override
    public List<ProyectoProductivo> obtenerProyectosProductivos() {
        return proyectoProductivoDAO.findAll();
    }
    public LoteDTO obtenerLotes(){
       /*
       ArrayList   lotes= lotesDao.findAll

       ArrayList lotesDTO= stream.filtrer....toLote(lote)
       */

        return null;//lotesDTO
    }

    public LoteDTO obtenerLote(int id){
        /*
       Lote lote= lotesDao.find(id)

       return toLote(lote)
       */

        return null;//toLote(lote)

    }


    public void asignarLote(Usuario user, LoteDTO lote, ProyectoProductivoDTO proyecto){

        /*
        RepresentanteEmpresa representante= obtenerRepresentantePorUsuario(user);

        Lote lote= loteDao.find(loteDTO.id());
        ProyectoProductivo proyecto= proyectoDao.find(proyectoDTO.id());

        representante.asignarLote(lote, proyecto)

        * */
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
/*
    private ProyectoProductivo toProyecto (ProyectoProductivoDTO dto){
        RepresentanteEmpresa representante  = new RepresentanteEmpresa("11111111","nike",dto.usuario());
        representantes.add(representante);
        return new ProyectoProductivo(dto.nombre(), dto.objeto(), dto.descripcionServicio(), dto.emplazamiento(), dto.tipoPersonal(),
                dto.tiempoRadicacion(), dto.metrosCuadrados(), dto.areaTrabajo(), dto.areaDeposito(),
                dto.estacionamiento(), dto.tienePlanos(), dto.personalOcupar(), dto.materiasPrimas(),
                dto.destinoProduccion(), dto.tension(), dto.potencia(), dto.agua(), dto.necesitaGas(), dto.residuos(),
                dto.realizaTratamiento(), dto.necesitaBalanza(), dto.necesitaComedor(), dto.necesitaCoworking(),
                representante
        );
    }*/
/*
    private ProyectoProductivo toProyecto (ProyectoProductivoDTO dto){
        RepresentanteEmpresa representante =
                representanteDAO.find(dto.usuario().UserName());

        return new ProyectoProductivo(dto.nombre(), dto.objeto(), dto.descripcionServicio(), dto.emplazamiento(), dto.tipoPersonal(),
                dto.tiempoRadicacion(), dto.metrosCuadrados(), dto.areaTrabajo(), dto.areaDeposito(),
                dto.estacionamiento(), dto.tienePlanos(), dto.personalOcupar(), dto.materiasPrimas(),
                dto.destinoProduccion(), dto.tension(), dto.potencia(), dto.agua(), dto.necesitaGas(), dto.residuos(),
                dto.realizaTratamiento(), dto.necesitaBalanza(), dto.necesitaComedor(), dto.necesitaCoworking(),
                representante
        );
    }*/
}