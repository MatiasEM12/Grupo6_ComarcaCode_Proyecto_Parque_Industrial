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

@WebServlet("/misSolicitudes")
public class MisSolicitudesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);

            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            List<SolicitudRadicacion> solicitudes = sistema.obtenerSolicitudesDe(usuario.UserName());

            request.setAttribute("solicitudes", solicitudes);

        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/misSolicitudes.jsp")
                    .forward(request, response);
        }
    }
}