package main;

import model.EstadoSolicitud;
import model.Observacion;
import model.ProyectoProductivo;
import model.Rol;
import model.SolicitudRadicacion;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private static List<Usuario> usuarios;
    private final List<SolicitudRadicacion> solicitudes;
    private final List<ProyectoProductivo> proyectos;

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
        proyectos = new ArrayList<>();
        cargarSolicitudesPrueba();
        cargarProyectosPrueba();
    }
    private void cargarSolicitudesPrueba() {

        Usuario representante =
                obtenerUsuarioPorUsername("maria");

        solicitudes.add(
                new SolicitudRadicacion(
                        representante,
                        "Radicación industrial",
                        "Fábrica Metalúrgica Patagónica",
                        "Fabricación de estructuras metálicas industriales",
                        "Parque Industrial Viedma - Lote 12",
                        "35 empleados",
                        "12 meses",
                        "2500",
                        "1800",
                        "400",
                        "300",
                        "Planos aprobados",
                        "35 personas",
                        "Acero y aluminio",
                        "Mercado provincial y nacional",
                        "380V",
                        "120 kW",
                        "Sí",
                        "Sí",
                        "Residuos metálicos",
                        "Separación y reciclado",
                        "Sí",
                        "Sí",
                        "No",
                        "Documentación inicial del proyecto metalúrgico",
                        "metalurgica_patagónica.pdf"
                )
        );

        solicitudes.add(
                new SolicitudRadicacion(
                        representante,
                        "Radicación productiva",
                        "Planta de Alimentos Regionales",
                        "Producción y envasado de alimentos regionales",
                        "Parque Industrial Viedma - Lote 8",
                        "20 empleados",
                        "8 meses",
                        "1800",
                        "1200",
                        "300",
                        "300",
                        "Planos en revisión",
                        "20 personas",
                        "Frutas regionales",
                        "Distribución provincial",
                        "220V",
                        "80 kW",
                        "Sí",
                        "No",
                        "Residuos orgánicos",
                        "Compostaje y retiro autorizado",
                        "No",
                        "Sí",
                        "Sí",
                        "Documentación inicial de la planta alimenticia",
                        "alimentos_regionales.pdf"
                )
        );

        solicitudes.add(
                new SolicitudRadicacion(
                        representante,
                        "Ampliación industrial",
                        "Centro Logístico Patagónico",
                        "Depósito, distribución y logística regional",
                        "Parque Industrial Viedma - Lote 20",
                        "15 empleados",
                        "6 meses",
                        "3000",
                        "2000",
                        "600",
                        "400",
                        "Planos pendientes",
                        "15 personas",
                        "Mercadería general",
                        "Distribución regional",
                        "380V",
                        "100 kW",
                        "Sí",
                        "No",
                        "Residuos comunes",
                        "Retiro municipal",
                        "Sí",
                        "No",
                        "Sí",
                        "Documentación inicial del centro logístico",
                        "centro_logistico.pdf"
                )
        );
    }

    private void cargarProyectosPrueba() {

        ProyectoProductivo p1 =
                new ProyectoProductivo(
                        "Metalúrgica Patagónica",
                        "Fabricación de estructuras metálicas",
                        2500,
                        "Maquinaria industrial",
                        35,
                        "Acero"
                );

        ProyectoProductivo p2 =
                new ProyectoProductivo(
                        "Alimentos Regionales",
                        "Producción de alimentos regionales",
                        1800,
                        "Cámaras frigoríficas",
                        20,
                        "Frutas regionales"
                );

        proyectos.add(p1);
        proyectos.add(p2);
    }
    public static List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Usuario obtenerUsuarioPorUsername(String username) {
        if (username == null) {
            return null;
        }

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

    public SolicitudRadicacion obtenerSolicitudPorId(int id) {
        return solicitudes.stream()
                .filter(solicitud -> solicitud.id() == id)
                .findFirst()
                .orElse(null);
    }

    public void observarSolicitud(int idSolicitud, String descripcion) {
        SolicitudRadicacion solicitud = obtenerSolicitudPorId(idSolicitud);

        if (solicitud == null) {
            throw new RuntimeException("No existe la solicitud indicada");
        }

        solicitud.agregarObservacion(new Observacion(descripcion));
    }

    public ProyectoProductivo aprobarSolicitud(int idSolicitud) {
        SolicitudRadicacion solicitud = obtenerSolicitudPorId(idSolicitud);

        if (solicitud == null) {
            throw new RuntimeException("No existe la solicitud indicada");
        }

        if (solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA) {
            return null;
        }

        solicitud.aprobar();
        ProyectoProductivo proyecto = solicitud.crearProyectoProductivo();
        agregarProyectoProductivo(proyecto);
        return proyecto;
    }

    public void agregarProyectoProductivo(ProyectoProductivo proyecto) {
        if (proyecto == null) {
            throw new RuntimeException("El proyecto productivo no puede ser nulo");
        }

        proyectos.add(proyecto);
    }

    public List<ProyectoProductivo> obtenerProyectosProductivos() {
        return proyectos;
    }
}

