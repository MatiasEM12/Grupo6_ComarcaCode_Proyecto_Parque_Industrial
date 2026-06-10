package vista.servlet;



import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.ProyectoProductivo;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/detalleProyecto")
public class DetalleProyectoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idProyecto = Integer.parseInt(
                    request.getParameter("idProyecto")
            );

            ParqueIndustrial sistema = new ParqueIndustrial();

            ProyectoProductivo proyecto = sistema.buscarProyectoPorId(idProyecto);

            if (proyecto == null) {

                response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");

                return;
            }

            request.setAttribute("proyecto", proyecto);

            request.getRequestDispatcher("/detalleProyecto.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            throw new ServletException("Error al mostrar proyecto", e);
        }
    }
}