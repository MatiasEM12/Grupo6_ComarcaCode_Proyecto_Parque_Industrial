package vista.servlet;

import main.Sistema;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/perfiles")
public class PerfilesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> usuarios =
                Sistema.obtenerUsuarios();

        request.setAttribute(
                "usuarios",
                usuarios
        );

        request.getRequestDispatcher(
                "/perfiles.jsp"
        ).forward(request, response);
    }
}