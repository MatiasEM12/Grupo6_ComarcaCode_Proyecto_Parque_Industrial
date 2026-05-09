package vista.servlet;

import main.Sistema;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/seleccionarPerfil")
public class SeleccionarPerfilServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException, IOException {

            Sistema sistema =
                    (Sistema) getServletContext()
                            .getAttribute("sistema");

            String usuarioUsername =request.getParameter("username");

            Usuario usuario =
                    sistema.obtenerUsuarioPorUsername(
                            usuarioUsername
                    );

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "usuarioLogueado",
                    usuario
            );

            if(usuario.rol().equals("representante")){

                response.sendRedirect(
                        request.getContextPath()
                                + "/mainRepresentante.jsp"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/mainAdm.jsp"
                );
            }
        }

}
