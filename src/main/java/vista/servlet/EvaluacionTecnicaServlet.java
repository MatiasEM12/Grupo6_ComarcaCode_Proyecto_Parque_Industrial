package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.ProyectoProductivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/evaluacionTecnica")
public class EvaluacionTecnicaServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;

    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idProyectoParam = request.getParameter("idProyecto");

        if (idProyectoParam == null || idProyectoParam.isEmpty()) {
            response.sendRedirect("proyectosEnEjecucion");
            return;
        }

        int idProyecto = Integer.parseInt(idProyectoParam);

        ProyectoProductivo proyecto = sistema.obtenerProyectoProductivo(idProyecto);

        request.setAttribute("proyecto", proyecto);
        request.setAttribute("idProyecto", idProyecto);

        request.getRequestDispatcher("/evaluacionTecnica.jsp").forward(request, response);
    }
}