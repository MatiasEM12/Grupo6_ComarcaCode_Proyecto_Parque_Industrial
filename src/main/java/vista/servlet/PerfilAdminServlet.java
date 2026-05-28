package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.AdministradorDelParque;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/perfilAdmin")
public class PerfilAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        SistemaParqueIndustrial sistema = new ParqueIndustrial();
        request.setAttribute("usuario", usuario);

        // Si es admin, se separa  delegarlo a sistema

            AdministradorDelParque admin = sistema.obtenerAdm(usuario.UserName());
            request.setAttribute("admin", admin);


        request.getRequestDispatcher("/PerfilAdmin.jsp").forward(request, response);
    }
}
