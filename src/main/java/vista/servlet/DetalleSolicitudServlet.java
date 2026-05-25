package vista.servlet;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.SolicitudRadicacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/solicitudDetalle")
public class DetalleSolicitudServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(request.getParameter("id"));

        SistemaParqueIndustrial sistema =
                new ParqueIndustrial();

        SolicitudRadicacion solicitud = sistema.obtenerSolicitudes().stream()
                .filter(s -> s.id() == id)
                .findFirst()
                .orElse(null);

        List<Lote> lotes = sistema.obtenerLotesDisponibles();

        request.setAttribute("solicitud", solicitud);
        request.setAttribute("lotes", lotes);

        request.getRequestDispatcher("/detalleSolicitud.jsp")
                .forward(request, response);
    }
}