package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/crearObservacionSolicitud")
public class CrearObservacionSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        try {

            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));

            String observacion = request.getParameter("observacion");

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            sistema.crearObservacionSolicitud(idSolicitud, observacion, usuario);

            response.sendRedirect(request.getContextPath() + "/detalleSolicitud?id=" + idSolicitud);

        } catch (Exception e) {

            throw new ServletException(
                    "Error al crear observación",
                    e
            );
        }
    }
}