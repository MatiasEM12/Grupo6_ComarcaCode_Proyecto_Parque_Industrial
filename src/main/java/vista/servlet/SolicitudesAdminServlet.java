package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/solicitudesAdmin")
public class SolicitudesAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null || !usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        SistemaParqueIndustrial sistema =
                new ParqueIndustrial();

        request.setAttribute(
                "solicitudes",
                sistema.obtenerSolicitudes()
        );

        request.getRequestDispatcher(
                "/solicitudesAdmin.jsp"
        ).forward(request, response);
    }
}
