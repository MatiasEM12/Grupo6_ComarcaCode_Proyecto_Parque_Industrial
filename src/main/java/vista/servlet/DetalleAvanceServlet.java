package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.AvanceDeProyecto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/detalleAvance")
public class DetalleAvanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idAvance");

        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/misProyectos");
            return;
        }

        int idAvance = Integer.parseInt(idParam);

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        AvanceDeProyecto avance = sistema.obtenerAvance(idAvance);

        if (avance == null) {
            response.sendRedirect(request.getContextPath() + "/misProyectos");
            return;
        }

        request.setAttribute("avance", avance);

        request.getRequestDispatcher(
                "/RepresentanteAvanceProyecto.jsp"
        ).forward(request, response);
    }
}