package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.OrganismoPublico;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/perfilOrganismo")
public class PerfilOrganismoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("usuarioLogueado") == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (!usuario.nombreRol().equals("organismo_publico")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        OrganismoPublico organismo = sistema.obtenerOrganismo(usuario.UserName());

        request.setAttribute("usuario", usuario);
        request.setAttribute("organismo", organismo);

        request.getRequestDispatcher("/perfilOrganismo.jsp")
                .forward(request, response);
    }
}