package database.persistencia;

import database.DAOs.ReprecentanteEmpresaDAO;
import database.DAOs.SolicitudRadicacionDAO;
import database.DAOs.UsuarioDAO;
import database.JDBCs.ReprecentanteEmpresaDAOJDBC;
import database.JDBCs.SolicitudRadicacionDAOJDBC;
import database.JDBCs.UsuarioDAOJDBC;
import model.EstadoSolicitud;
import model.ProyectoProductivo;
import model.RepresentanteEmpresa;
import model.SolicitudRadicacion;
import model.Usuario;
import model.DTO.SolicitudRadicacionDTO;

import java.util.List;
import java.util.Objects;

public class ParqueIndustrial implements SistemaParqueIndustrial {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();
    private final ReprecentanteEmpresaDAO representanteDAO = new ReprecentanteEmpresaDAOJDBC();
    private final SolicitudRadicacionDAO solicitudRadicacionDAO = new SolicitudRadicacionDAOJDBC();

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioDAO.find(username);
    }

    @Override
    public void agregarSolicitud(SolicitudRadicacionDTO dto) {
        if (dto == null) {
            throw new RuntimeException("La solicitud no puede ser null");
        }

        // Por ahora este flujo necesita que el DAO de representante pueda recuperar
        // al representante desde el usuario logueado. En tu DAO actual todavía está
        // incompleto, por eso conviene terminar ese DAO antes de persistir solicitudes.
        RepresentanteEmpresa representante = representanteDAO.find(dto.usuario().UserName());

        SolicitudRadicacion solicitud = new SolicitudRadicacion(
                representante,
                dto.objeto(),
                dto.nombreProyecto(),
                dto.descripcionServicio(),
                dto.emplazamiento(),
                dto.personal(),
                dto.tiempoRadicacion(),
                dto.m2(),
                dto.areaTrabajo(),
                dto.areaDeposito(),
                dto.estacionamiento(),
                dto.planos(),
                dto.empleabilidad(),
                dto.materiasPrimas(),
                dto.destinoProduccion(),
                dto.tension(),
                dto.potencia(),
                dto.agua(),
                dto.gas(),
                dto.residuos(),
                dto.tratamiento(),
                dto.balanza(),
                dto.comedor(),
                dto.coworking(),
                dto.descripcionArchivo(),
                dto.nombreArchivoPDF()
        );

        representante.cargarSolicitud(solicitud);
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("Debe existir un usuario logueado");
        }

        return obtenerSolicitudes()
                .stream()
                .filter(s -> s.representante() != null)
                .filter(s -> s.representante().usuario() != null)
                .filter(s -> s.representante().usuario().UserName().equals(usuario.UserName()))
                .toList();
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return solicitudRadicacionDAO.findAll();
    }

    @Override
    public void aprobarSolicitud(int idSolicitud) {
        SolicitudRadicacion solicitud = solicitudRadicacionDAO.find(idSolicitud);

        if (solicitud == null) {
            throw new RuntimeException("No existe la solicitud con id: " + idSolicitud);
        }

        solicitud.aprobar();
        solicitudRadicacionDAO.update(solicitud);
    }

    @Override
    public void observarSolicitud(int idSolicitud, String descripcion) {
        SolicitudRadicacion solicitud = solicitudRadicacionDAO.find(idSolicitud);

        if (solicitud == null) {
            throw new RuntimeException("No existe la solicitud con id: " + idSolicitud);
        }

        solicitud.observar();
        solicitudRadicacionDAO.update(solicitud);
    }

    @Override
    public List<ProyectoProductivo> obtenerProyectosProductivos() {
        return obtenerSolicitudes()
                .stream()
                .filter(s -> s.estadoSolicitud() == EstadoSolicitud.APROBADA)
                .map(SolicitudRadicacion::proyecto)
                .filter(Objects::nonNull)
                .toList();
    }
}
