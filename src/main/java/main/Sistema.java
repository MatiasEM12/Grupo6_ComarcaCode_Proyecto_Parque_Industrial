package main;

import database.persistencia.SistemaParqueIndustrial;
import model.*;

import model.DTO.SolicitudRadicacionDTO;

import java.util.List;
import java.util.ArrayList;

import static java.util.Locale.filter;


public class Sistema  implements SistemaParqueIndustrial {

    private static List<Usuario> usuarios;
    private static List<SolicitudRadicacion> solicitudes;
    private static List<RepresentanteEmpresa> representantes;
    private static List<Empresa> empresas;

    public Sistema() {
        usuarios = List.of(
                new Usuario("juan", "1234",
                        new Rol("administrador",111), "pepe@gmail.com"),

                new Usuario("maria", "5678",
                        new Rol("representante",222), "representante@gmail.com"),

                new Usuario("pedro", "7890",
                        new Rol("organismo_publico",333), "pedro@gmail.com")
        );
        var empresa = new Empresa(
                "11111111",
                "nike",
                "contacto",
                "representante@gmail.com",
                false,
                null,
                null
        );
        var representante = new RepresentanteEmpresa(
                "11111111",
                empresa,
                usuarios.get(1)
        );

        empresa.agregarRepresentante(representante);
        solicitudes = new ArrayList<>();
        representantes=new ArrayList<>();
        empresas=new ArrayList<>();
        representantes.add(representante);
        empresas.add(empresa);
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

    @Override
    public void agregarSolicitud(SolicitudRadicacionDTO dto) {

        if (dto == null) {
            throw new RuntimeException("La solicitud no puede ser nula");
        }

        RepresentanteEmpresa representante =
                obtenerRepresentantePorUsuario(dto.usuario());

        representante.puedeIngresarSolititud();

        SolicitudRadicacion solicitud =
                new SolicitudRadicacion(
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

        solicitudes.add(solicitud);

    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudesDe(
            Usuario usuario) {

        RepresentanteEmpresa representante =
                this.obtenerRepresentantePorUsuario(usuario);

        if (representante == null) {
            return new ArrayList<>();
        }

        return solicitudes.stream()
                .filter(solicitud ->
                        solicitud.representante()
                                .usuario()
                                .UserName()
                                .equals(usuario.UserName())
                )
                .toList();
    }
    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return solicitudes;
    }

    public RepresentanteEmpresa obtenerRepresentantePorUsuario(Usuario usuario) {
        return representantes.stream().filter(representante -> representante.usuario().UserName().equals(usuario.UserName()))
                .findFirst()
                .orElse(null);
    }
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
}