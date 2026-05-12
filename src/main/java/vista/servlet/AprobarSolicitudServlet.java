package vista.servlet;

import main.Sistema;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/aprobarSolicitud")
public class AprobarSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        if (!esAdministrador(request)) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        sistema.aprobarSolicitud(idSolicitud);

        response.sendRedirect(request.getContextPath() + "/solicitudesAdmin");
    }

    private boolean esAdministrador(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return false;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        return usuario != null && usuario.rol().equals("administrador");
    }
}