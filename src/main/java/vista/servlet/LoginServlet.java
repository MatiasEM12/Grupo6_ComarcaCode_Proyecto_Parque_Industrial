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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
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

            case "administrador":
                response.sendRedirect(request.getContextPath() +  "/mainAdm.jsp");
                break;

            case "organismo_publico":
                response.sendRedirect(request.getContextPath() + "/mainOrganismoPublico.jsp");
                break;

            case "representante":
                response.sendRedirect(request.getContextPath() + "/mainRepresentante.jsp");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}