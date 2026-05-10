package vista.servlet;

import main.Sistema;
import model.SolicitudRadicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/subirSolicitud")
@MultipartConfig
public class SubirSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Sistema sistema =
                (Sistema) getServletContext().getAttribute("sistema");

        // NUEVO CAMPO
        String nombreProyecto =
                request.getParameter("nombreProyecto");

        String objeto = request.getParameter("objeto");
        String descripcionServicio = request.getParameter("descripcionServicio");
        String emplazamiento = request.getParameter("emplazamiento");
        String personal = request.getParameter("personal");
        String tiempo = request.getParameter("tiempo");
        String m2 = request.getParameter("m2");
        String areaTrabajo = request.getParameter("areaTrabajo");
        String areaDeposito = request.getParameter("areaDeposito");
        String estacionamiento = request.getParameter("estacionamiento");
        String planos = request.getParameter("planos");
        String personalOcupar = request.getParameter("personalOcupar");
        String materiasPrimas = request.getParameter("materiasPrimas");
        String destinoProduccion = request.getParameter("destinoProduccion");
        String tension = request.getParameter("tension");
        String potencia = request.getParameter("potencia");
        String agua = request.getParameter("agua");
        String gas = request.getParameter("gas");
        String residuos = request.getParameter("residuos");
        String tratamiento = request.getParameter("tratamiento");
        String balanza = request.getParameter("balanza");
        String comedor = request.getParameter("comedor");
        String coworking = request.getParameter("coworking");
        String descripcionArchivo = request.getParameter("descripcionArchivo");

        Part archivoPDF = request.getPart("archivoPDF");

        String nombreArchivoPDF = "";

        if (archivoPDF != null && archivoPDF.getSize() > 0) {
            nombreArchivoPDF = archivoPDF.getSubmittedFileName();
        }

        SolicitudRadicacion solicitud =
                new SolicitudRadicacion(
                        usuario,
                        objeto,
                        nombreProyecto,
                        descripcionServicio,
                        emplazamiento,
                        personal,
                        tiempo,
                        m2,
                        areaTrabajo,
                        areaDeposito,
                        estacionamiento,
                        planos,
                        personalOcupar,
                        materiasPrimas,
                        destinoProduccion,
                        tension,
                        potencia,
                        agua,
                        gas,
                        residuos,
                        tratamiento,
                        balanza,
                        comedor,
                        coworking,
                        descripcionArchivo,
                        nombreArchivoPDF
                );

        sistema.agregarSolicitud(solicitud);

        response.sendRedirect(
                request.getContextPath()
                        + "/misProyectos"
        );
    }
}