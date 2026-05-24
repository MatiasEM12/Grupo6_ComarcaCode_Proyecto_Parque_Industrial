package main;

import model.Rol;
import model.Usuario;
import java.util.List;
import model.SolicitudRadicacion;
import java.util.ArrayList;


public class Sistema {

    private static List<Usuario> usuarios;
    private static List<SolicitudRadicacion> solicitudes;

    public Sistema() {
        usuarios = List.of(
                new Usuario("juan", "1234567",
                        new Rol("administrador",111), "pepe@gmail.com"),

                new Usuario("maria", "56786788",
                        new Rol("representante",222), "representante@gmail.com"),

                new Usuario("pedro", "78906778",
                        new Rol("organismo_publico",333), "pedro@gmail.com")
        );

        solicitudes = new ArrayList<>();
    }

    public static List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.UserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void agregarSolicitud(SolicitudRadicacion solicitud) {
        if (solicitud == null) {
            throw new RuntimeException("La solicitud no puede ser nula");
        }

        solicitudes.add(solicitud);
    }

    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return solicitudes;
    }
}
