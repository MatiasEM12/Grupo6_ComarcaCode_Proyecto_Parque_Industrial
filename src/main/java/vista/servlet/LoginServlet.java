package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        Usuario usuario = sistema.login(username, password);

        if (usuario == null) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("usuarioLogueado", usuario);


        switch (usuario.rol().nombre()) {

            case "AdministradorDelParque":
                response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
                break;

            case "Organismo-Publico":
                response.sendRedirect(request.getContextPath() + "/organismo/home.jsp");
                break;

            case "Representante":
                response.sendRedirect(request.getContextPath() + "/representante/home.jsp");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}