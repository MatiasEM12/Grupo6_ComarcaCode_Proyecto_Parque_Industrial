package main;

import database.persistencia.SistemaParqueIndustrial;
import model.*;
import model.DTO.ProyectoProductivoDTO;
import model.DTO.SolicitudRadicacionDTO;

import java.util.List;
import java.util.ArrayList;

import static java.util.Locale.filter;


public class Sistema  implements SistemaParqueIndustrial {

    private static List<Usuario> usuarios;
    private static List<SolicitudRadicacion> solicitudes;
    private static List<RepresentanteEmpresa> representantes;

    public Sistema() {
        usuarios = List.of(
                new Usuario("juan", "1234",
                        new Rol("administrador",111), "pepe@gmail.com"),

                new Usuario("maria", "5678",
                        new Rol("representante",222), "representante@gmail.com"),

                new Usuario("pedro", "7890",
                        new Rol("organismo_publico",333), "pedro@gmail.com")
        );

        solicitudes = new ArrayList<>();
        representantes=new ArrayList<>();
    }


    public  List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.UserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void agregarSolicitud(SolicitudRadicacionDTO solicitud) {
        if (solicitud == null) {
            throw new RuntimeException("La solicitud no puede ser nula");
        }
        ProyectoProductivo proyectoProductivo = toProyecto(solicitud.proyecto());
        SolicitudRadicacion solicitudRadicacion= new SolicitudRadicacion(proyectoProductivo.representanteEmpresa(),proyectoProductivo);
        solicitudes.add(solicitudRadicacion);
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario) {
        var representante=this.obtenerRepresentantePorUsuario(usuario);
        return solicitudes.stream().filter(solicitud -> solicitud.representante().dni().equals(representante.dni())).toList();
    }

    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return solicitudes;
    }

    public RepresentanteEmpresa obtenerRepresentantePorUsuario(Usuario usuario) {
        return representantes.stream().filter(representante -> representante.usuario().UserName().equals(usuario.UserName()))
                .findFirst()
                .orElse(null);
    }

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
    }
}
