package database.persistencia;



import database.DAOs.*;
import database.JDBCs.*;

import model.*;
import model.DTO.*;

import java.util.ArrayList;
import java.util.List;



public class ParqueIndustrial implements SistemaParqueIndustrial {

    private UsuarioDAO usuarioDAO= new UsuarioDAOJDBC();
    private ReprecentanteEmpresaDAO representanteDAO= new ReprecentanteEmpresaDAOJDBC();
    private SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();

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
    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return this.solicitudRadicacionDAO.findAll();
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


    public void asignarLote(Usuario user, LoteDTO lote, ProyectoDTO proyecto){

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

    public void cargarAvanceProyecto( Usuario user, AvanceDeProyectoDTO avance,ProyectoDTO proyecto){
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