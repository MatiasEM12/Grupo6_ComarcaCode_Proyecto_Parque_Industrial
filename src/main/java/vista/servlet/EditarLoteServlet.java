package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.Ubicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editarLote")
public class EditarLoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null || !usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        long latitud = Long.parseLong(request.getParameter("latitud"));
        long longitud = Long.parseLong(request.getParameter("longitud"));
        long altitud = Long.parseLong(request.getParameter("altitud"));
        double superficie = Double.parseDouble(request.getParameter("superficie"));
        String estado = request.getParameter("estado");
        String infraestructura = request.getParameter("infraestructura");

        Ubicacion ubicacion =
                new Ubicacion(latitud, longitud, altitud);

        Lote lote =
                new Lote(id, ubicacion, superficie, estado, infraestructura);

        SistemaParqueIndustrial sistema =
                new ParqueIndustrial();

        sistema.actualizarLote(lote);

        response.sendRedirect(
                request.getContextPath() + "/detalleLote?id=" + id
        );
    }
}