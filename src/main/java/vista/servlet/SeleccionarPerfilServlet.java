package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/seleccionarPerfil")
public class SeleccionarPerfilServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            try {
                SistemaParqueIndustrial sistema = new ParqueIndustrial();


                String usuarioUsername = request.getParameter("username");

                Usuario usuario = sistema.obtenerUsuarioPorUsername(usuarioUsername);

                HttpSession session = request.getSession();

                session.setAttribute("usuarioLogueado", usuario);

                String pagina = paginaSegunRol(usuario.nombreRol());

                response.sendRedirect(request.getContextPath() + pagina);
            }catch (Exception e) {

                request.setAttribute("error", e.getMessage());

                request.getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            }
        }

        private String paginaSegunRol(String rol) {
            Map<String, String> paginasPorRol = new HashMap<>();

            paginasPorRol.put("representante", "/mainRepresentante.jsp");
            paginasPorRol.put("organismo_publico", "/mainOrganismoPublico.jsp");
            paginasPorRol.put("administrador", "/mainAdm.jsp");

            return paginasPorRol.getOrDefault(rol, "/login.jsp");
        }
}

