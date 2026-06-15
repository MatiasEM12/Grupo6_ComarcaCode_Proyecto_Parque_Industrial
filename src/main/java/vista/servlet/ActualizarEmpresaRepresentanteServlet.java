package vista.servlet;

import database.DAOs.EmpresaDAO;
import database.JDBCs.EmpresaDAOJDBC;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/actualizarEmpresaRepresentante")
public class ActualizarEmpresaRepresentanteServlet extends HttpServlet {

    private EmpresaDAO empresaDAO = new EmpresaDAOJDBC();

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

        if (!usuario.nombreRol().equals("representante")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String cuit = request.getParameter("cuit");
            String contacto = request.getParameter("contacto");
            String contactoRepresentante = request.getParameter("contactoRepresentante");

            empresaDAO.actualizarContacto(cuit, contacto, contactoRepresentante);

            response.sendRedirect(request.getContextPath() + "/perfilRepresentante?mensaje=ok");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/perfilRepresentante?error=" + e.getMessage());
        }
    }
}
