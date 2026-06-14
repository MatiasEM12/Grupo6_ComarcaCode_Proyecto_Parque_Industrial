package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.ProyectoProductivo;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
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


            List<ProyectoProductivo> proyectos = sistema.obtenerProyectosProductivosDe(usuario.UserName());

            request.setAttribute("proyectos", proyectos);

            request.getRequestDispatcher("/representanteProyectos.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/representanteProyectos.jsp")
                    .forward(request, response);
        }
    }
}