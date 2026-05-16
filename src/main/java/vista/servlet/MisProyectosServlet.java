package vista.servlet;

import main.Sistema;
import model.SolicitudRadicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

        Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

        /*
         * Obtiene solamente las solicitudes
         * del representante logueado
         */
        List<SolicitudRadicacion> solicitudesUsuario =
                sistema.obtenerSolicitudesDe(usuario);

        /*
         * Envia las solicitudes al JSP
         */
        request.setAttribute(
                "solicitudes",
                solicitudesUsuario
        );

        /*
         * Redirecciona al JSP
         */
        request.getRequestDispatcher(
                "/representanteProyectos.jsp"
        ).forward(request, response);
    }
}