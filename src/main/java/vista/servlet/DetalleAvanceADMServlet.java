package vista.servlet;



import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.AvanceDeProyecto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/detalleAvanceAdm")
public class DetalleAvanceADMServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String idParam = request.getParameter("idAvance");

            if (idParam == null || idParam.isBlank()) {
                response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
                return;
            }

            int idAvance = Integer.parseInt(idParam);

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            AvanceDeProyecto avance = sistema.obtenerAvance(idAvance);

            if (avance == null) {
                response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
                return;
            }

            request.setAttribute("avance", avance);

            request.getRequestDispatcher("/detalleAvanceADM.jsp").forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.getSession().setAttribute(
                    "error",
                    "Error al mostrar avance: " + e.getMessage()
            );

            response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
        }
    }
}