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

@WebServlet("/observarSolicitud")
public class ObservarSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!esAdministrador(request)) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
            String descripcion = request.getParameter("descripcion");

            Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            sistema.observarSolicitud(
                    idSolicitud,
                    descripcion,
                    usuarioLogueado.UserName()
            );

            response.sendRedirect(request.getContextPath() + "/solicitudesAdmin");
        } catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/solicitudesAdmin")
                    .forward(request, response);
        }
    }

    private boolean esAdministrador(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return false;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        return usuario != null && usuario.nombreRol().equals("administrador");
    }
}
