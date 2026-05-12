package vista.servlet;

import main.Sistema;
import model.DTO.ProyectoProductivoDTO;
import model.DTO.SolicitudRadicacionDTO;


import model.RepresentanteEmpresa;
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

        String nombreProyecto = request.getParameter("nombreProyecto");

        String objeto = request.getParameter("objeto");

        String descripcionServicio = request.getParameter("descripcionServicio");

        String emplazamiento = request.getParameter("emplazamiento");

        String tipoPersonal = request.getParameter("personal");

        int tiempoRadicacion = Integer.parseInt(
                request.getParameter("tiempo")
        );

        double metrosCuadrados = convertirM2(
                request.getParameter("m2")
        );

        double areaTrabajo = Double.parseDouble(
                request.getParameter("areaTrabajo")
        );

        double areaDeposito = Double.parseDouble(
                request.getParameter("areaDeposito")
        );

        double estacionamiento = Double.parseDouble(
                request.getParameter("estacionamiento")
        );

        boolean tienePlanos = request.getParameter("planos")
                .equalsIgnoreCase("si");

        int personalOcupar = Integer.parseInt(
                request.getParameter("personalOcupar")
        );

        String materiasPrimas = request.getParameter("materiasPrimas");

        String destinoProduccion = request.getParameter("destinoProduccion");

        String tension = request.getParameter("tension");

        double potencia = Double.parseDouble(
                request.getParameter("potencia")
        );

        double agua = Double.parseDouble(
                request.getParameter("agua")
        );

        boolean necesitaGas = request.getParameter("gas")
                .equalsIgnoreCase("si");

        String residuos = request.getParameter("residuos");

        boolean realizaTratamiento = request.getParameter("tratamiento")
                .equalsIgnoreCase("si");

        boolean necesitaBalanza = request.getParameter("balanza")
                .equalsIgnoreCase("si");

        boolean necesitaComedor = request.getParameter("comedor")
                .equalsIgnoreCase("si");

        boolean necesitaCoworking = request.getParameter("coworking")
                .equalsIgnoreCase("si");


        String descripcionArchivo = request.getParameter("descripcionArchivo");
        Part archivoPDF = request.getPart("archivoPDF");

        String nombreArchivoPDF = "";

        if (archivoPDF != null && archivoPDF.getSize() > 0) {
            nombreArchivoPDF = archivoPDF.getSubmittedFileName();
        }

        ProyectoProductivoDTO proyecto = new ProyectoProductivoDTO(
                nombreProyecto,
                objeto,
                descripcionServicio,
                emplazamiento,
                tipoPersonal,
                tiempoRadicacion,
                metrosCuadrados,
                areaTrabajo,
                areaDeposito,
                estacionamiento,
                tienePlanos,
                personalOcupar,
                materiasPrimas,
                destinoProduccion,
                tension,
                potencia,
                agua,
                necesitaGas,
                residuos,
                realizaTratamiento,
                necesitaBalanza,
                necesitaComedor,
                necesitaCoworking,
                usuario
        );

        SolicitudRadicacionDTO solicitud =
                new SolicitudRadicacionDTO(
                        usuario,
                        proyecto
                );

        sistema.agregarSolicitud(solicitud);

        response.sendRedirect(
                request.getContextPath()
                        + "/misSolicitudes"
        );
    }

    private double convertirM2(String m2) {

        if (m2 == null || m2.isBlank()) {
            return 0;
        }

        return Double.parseDouble(
                m2.replace("aprox", "")
                        .trim()
        );
    }
}