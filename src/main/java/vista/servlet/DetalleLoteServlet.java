package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.ProyectoProductivo;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/detalleLote")
public class DetalleLoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
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

            int id = Integer.parseInt(request.getParameter("id"));

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            Lote lote = sistema.obtenerLote(id);

            request.setAttribute("lote", lote);
            ProyectoProductivo proyecto = null;

            if (lote != null && lote.estado().equals("OCUPADO")) {
                proyecto = sistema.obtenerProyectoPorLote(lote.id());
            }

            request.setAttribute("proyecto", proyecto);

            request.getRequestDispatcher("/detalleLote.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("error", "Error al mostrar lote: " + e.getMessage());

            response.sendRedirect(request.getContextPath() + "/listadoLotes");
        }
    }
}