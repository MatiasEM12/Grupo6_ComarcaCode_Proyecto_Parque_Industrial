package vista.servlet;

import main.Sistema;

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

        Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

        request.setAttribute(
                "proyectos",
                sistema.obtenerProyectosProductivos()
        );

        request.getRequestDispatcher(
                "/proyectosEnEjecucion.jsp"
        ).forward(request, response);
    }
}