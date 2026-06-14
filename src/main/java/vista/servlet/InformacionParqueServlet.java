package vista.servlet;

import database.DAOs.InformeDAO;
import database.JDBCs.InformeDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.DTO.InformeParqueDTO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/informacionParque")
public class InformacionParqueServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;
    private InformeDAO informeDAO = new InformeDAOJDBC();
    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            if ( !usuario.nombreRol().equals("administrador")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            InformeParqueDTO reporte = sistema.generarReporteParque();
            request.setAttribute("reporte", reporte);
            request.setAttribute("reportesAdmin", informeDAO.findAll());
            request.getRequestDispatcher("/informacionParque.jsp")
                    .forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/informacionParque.jsp")
                    .forward(request, response);
        }
    }
}
