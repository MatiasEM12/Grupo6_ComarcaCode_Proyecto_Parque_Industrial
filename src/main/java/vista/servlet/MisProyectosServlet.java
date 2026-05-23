package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.SolicitudRadicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/misProyectos")
public class MisProyectosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(
                    request.getContextPath() + "/perfiles"
            );
            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");



        if (usuario == null) {
            response.sendRedirect(
                    request.getContextPath() + "/perfiles"
            );
            return;
        }
        SistemaParqueIndustrial sistema =
                new ParqueIndustrial();
        /*Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

         */

        /*
         * Obtiene solamente las solicitudes
         * del representante logueado
         */

        List<SolicitudRadicacion> solicitudes =
                sistema.obtenerSolicitudesDe(usuario.UserName())
                        .stream()
                        .filter(s -> s.estadoSolicitud().name().equals("APROBADA"))
                        .toList();

        /*
         * Envia las solicitudes al JSP
         */
        request.setAttribute("solicitudes", solicitudes);

        /*
         * Redirecciona al JSP
         */
        request.getRequestDispatcher(
                "/representanteProyectos.jsp"
        ).forward(request, response);
    }
}