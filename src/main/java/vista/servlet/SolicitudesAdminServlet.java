package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.SolicitudRadicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/solicitudesAdmin")
public class SolicitudesAdminServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);

            if (session == null) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuario == null || !usuario.nombreRol().equals("administrador")) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            List<SolicitudRadicacion> solicitudes = sistema.obtenerSolicitudes();

            request.setAttribute("solicitudes", solicitudes);
            List<Lote> lotes = sistema.obtenerLotesDisponibles();

            request.setAttribute("lotes", lotes);

            request.getRequestDispatcher("/solicitudesAdmin.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/solicitudesAdmin.jsp")
                    .forward(request, response);
        }
    }
}
