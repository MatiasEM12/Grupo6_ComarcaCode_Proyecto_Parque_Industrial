package vista.servlet;

import database.DAOs.UsuarioDAO;
import database.JDBCs.UsuarioDAOJDBC;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/actualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAOJDBC();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        try {
            String gmail = request.getParameter("gmail");
            String contrasena = request.getParameter("contrasena");

            usuarioDAO.actualizarCredenciales(usuario.id(), gmail, contrasena);

            Usuario usuarioActualizado = new Usuario(
                    usuario.id(),
                    usuario.UserName(),
                    contrasena,
                    usuario.rol(),
                    gmail
            );

            session.setAttribute("usuarioLogueado", usuarioActualizado);

            response.sendRedirect(request.getHeader("Referer"));

        } catch (Exception e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}