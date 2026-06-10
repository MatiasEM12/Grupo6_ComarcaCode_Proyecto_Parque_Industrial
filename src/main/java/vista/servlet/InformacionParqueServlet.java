package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.DTO.ReporteParqueDTO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/informacionParque")
public class InformacionParqueServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;

    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        if (!usuario.nombreRol().equals("organismo_publico")
                && !usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        ReporteParqueDTO reporte = sistema.generarReporteParque();
        request.setAttribute("reporte", reporte);

        request.getRequestDispatcher("/informacionParque.jsp")
                .forward(request, response);
    }
}
