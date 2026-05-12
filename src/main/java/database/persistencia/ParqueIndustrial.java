package database.persistencia;



import database.DAOs.*;
import database.JDBCs.*;
import model.DTO.*;
import model.ProyectoProductivo;
import model.RepresentanteEmpresa;
import model.SolicitudRadicacion;
import model.Usuario;

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

        //proyecto y solicitud se cargan en la base de datos al crear el objeto, por lo que no es necesario hacer un insert adicional
        ProyectoProductivo proyectoProductivo = this.toProyecto( solicitud.proyecto());
        SolicitudRadicacion solicitudRadicacion=new SolicitudRadicacion(representante,proyectoProductivo);
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
    }
}
