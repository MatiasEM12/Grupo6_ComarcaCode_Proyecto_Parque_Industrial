package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Empresa;
import model.RepresentanteEmpresa;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/perfilRepresentante")
public class PerfilRepresentanteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            if (!usuario.nombreRol().equals("representante")) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            RepresentanteEmpresa representante = sistema.obtenerRepresentante(usuario.UserName());
            Empresa empresa = sistema.obtenerEmpresaRepresentante(usuario.UserName());

            request.setAttribute("usuario", usuario);
            request.setAttribute("representante", representante);
            request.setAttribute("empresa", empresa);

            request.getRequestDispatcher("/PerfilRepresentante.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/PerfilRepresentante.jsp")
                    .forward(request, response);
        }
    }
}