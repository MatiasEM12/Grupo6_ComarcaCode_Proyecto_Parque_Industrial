package vista.servlet;

import database.DAOs.ReporteDAO;
import database.JDBCs.ReporteDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.DTO.ReporteParqueDTO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/informacionParquePublico")
public class  InformacionDelParquePublicoServelt extends HttpServlet {

    private SistemaParqueIndustrial sistema;
    private ReporteDAO reporteDAO = new ReporteDAOJDBC();
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

            if (!usuario.nombreRol().equals("organismo_publico") ){
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            ReporteParqueDTO reporte = sistema.generarReporteParque();
            request.setAttribute("reporte", reporte);
            request.setAttribute("reportesAdmin", reporteDAO.findAll());
            request.getRequestDispatcher("/InformacionParqueOrganismoPublico.jsp")
                    .forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/InformacionParqueOrganismoPublico.jsp")
                    .forward(request, response);
        }
    }
}
