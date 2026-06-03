package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.SolicitudRadicacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/miSolicitudDetalle")
public class MiSolicitudDetalleServlet  extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        SolicitudRadicacion solicitud = sistema.obtenerSolicitudes().stream()
                .filter(s -> s.id() == id)
                .findFirst()
                .orElse(null);


        request.setAttribute("solicitud", solicitud);
        request.getRequestDispatcher("/MiDetalleSolicitud.jsp").forward(request, response);
    }
}
