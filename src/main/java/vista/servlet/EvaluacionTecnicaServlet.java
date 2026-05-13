package vista.servlet;

import main.Sistema;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/evaluacionTecnica")
public class EvaluacionTecnicaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

        request.setAttribute(
                "proyectos",
                sistema.obtenerProyectosProductivos()
        );

        request.getRequestDispatcher("/evaluacionTecnica.jsp")
                .forward(request, response);
    }
}