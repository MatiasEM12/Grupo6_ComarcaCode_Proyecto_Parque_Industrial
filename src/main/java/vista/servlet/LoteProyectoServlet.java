package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.ProyectoProductivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loteProyecto")
public class LoteProyectoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            ProyectoProductivo proyecto = sistema.obtenerProyecto(idProyecto);

            if (proyecto == null) {
                response.sendRedirect(request.getContextPath() + "/misProyectos");
                return;
            }

            Lote lote = proyecto.lote();

            if (lote == null) {
                response.sendRedirect(request.getContextPath() + "/misProyectos");
                return;
            }

            request.setAttribute("proyecto", proyecto);
            request.setAttribute("lote", lote);

            request.getRequestDispatcher("/LoteProyecto.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/misProyectos")
                    .forward(request, response);
        }
    }
}