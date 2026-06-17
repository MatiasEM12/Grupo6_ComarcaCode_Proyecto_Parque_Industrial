package vista.servlet;

import database.DAOs.ProyectoProductivoDAO;
import database.JDBCs.ProyectoProductivoDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;

import model.ProyectoProductivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/representanteProyecto")
public class RepresentanteProyectoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idProyecto = Integer.parseInt(request.getParameter("id"));

            SistemaParqueIndustrial sistema = new ParqueIndustrial();
            ProyectoProductivo proyecto = sistema.obtenerProyectoPorId(idProyecto);

            request.setAttribute("proyecto", proyecto);

            request.getRequestDispatcher("/representanteProyecto.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/representanteProyecto.jsp")
                    .forward(request, response);
        }
    }
}