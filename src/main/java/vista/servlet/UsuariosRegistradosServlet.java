package vista.servlet;


import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuariosRegistrados")
public class UsuariosRegistradosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            if (!usuarioLogueado.nombreRol().equals("administrador")) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }
            SistemaParqueIndustrial sistema = new ParqueIndustrial();
            List<Usuario> usuarios = sistema.obtenerUsuarios();

            request.setAttribute("usuarios", usuarios);

            request.getRequestDispatcher("/usuariosRegistrados.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/usuariosRegistrados.jsp")
                    .forward(request, response);
        }
    }
}