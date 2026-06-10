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

@WebServlet("/crearLote")
public class CrearLoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        // VALIDAR SESIÓN
        if (session == null) {

            response.sendRedirect(request.getContextPath() + "/perfiles");

            return;
        }

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogueado");

        // VALIDAR USUARIO
        if (usuario == null) {

            response.sendRedirect(request.getContextPath() + "/perfiles");

            return;
        }

        // VALIDAR ROL ADMIN
        if (!usuario.nombreRol().equals("administrador")) {

            response.sendRedirect(request.getContextPath() + "/perfiles");

            return;
        }

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        try {

            long latitud = Long.parseLong(request.getParameter("latitud"));

            long longitud = Long.parseLong(request.getParameter("longitud"));

            long altitud = Long.parseLong(request.getParameter("altitud"));

            double superficie = Double.parseDouble(request.getParameter("superficie"));

            String infraestructura = request.getParameter("infraestructura");

            Ubicacion ubicacion = new Ubicacion(latitud, longitud, altitud);



           sistema.agregarLote( ubicacion, superficie, "DISPONIBLE", infraestructura);

            response.sendRedirect(request.getContextPath() + "/listadoLotes");

        }

        catch (RuntimeException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/listadoLotes.jsp").forward(request, response);
        }
    }
}
