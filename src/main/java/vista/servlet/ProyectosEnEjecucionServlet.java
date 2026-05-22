package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/proyectosEnEjecucion")
public class ProyectosEnEjecucionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        SistemaParqueIndustrial sistema =
                new ParqueIndustrial();

        request.setAttribute(
                "proyectos",
                sistema.obtenerProyectosProductivos()
        );

        request.getRequestDispatcher(
                "/proyectosEnEjecucion.jsp"
        ).forward(request, response);
    }
}