package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/aprobarSolicitudPrimerInstancia")
public class AprobarSolicitudPrimeraInstanciaServlet extends HttpServlet {

    @Override
    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        try {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession(false);

            // VALIDAR SESIÓN
            if (session == null) {

                response.sendRedirect(request.getContextPath() + "/login");

                return;
            }

            Usuario usuario =
                    (Usuario) session.getAttribute("usuarioLogueado");

            // VALIDAR USUARIO
            if (usuario == null) {

                response.sendRedirect(request.getContextPath() + "/login");

                return;
            }

            // VALIDAR ROL ADMIN
            if (!usuario.nombreRol().equals("administrador")) {

                response.sendRedirect(request.getContextPath() + "/login");

                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));


            sistema.aprobarSolicitudPrimeraInstancia(idSolicitud);

            response.sendRedirect(request.getContextPath() + "/solicitudesAdmin");
        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("error", e.getMessage());

            response.sendRedirect(request.getContextPath() + "/solicitudesAdmin");
        }
    }
}

