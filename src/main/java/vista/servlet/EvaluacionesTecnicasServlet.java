package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;
import model.DTO.EvaluacionTecnicaDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/evaluacionesTecnicas")
public class EvaluacionesTecnicasServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;

    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null || !usuario.nombreRol().equals("organismo_publico")) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

            List<EvaluacionTecnicaDTO> evaluaciones =
                    sistema.obtenerEvaluacionesTecnicasPorProyecto(idProyecto);

            request.setAttribute("evaluaciones", evaluaciones);
            request.setAttribute("idProyecto", idProyecto);

            request.getRequestDispatcher("/evaluacionesTecnicas.jsp")
                    .forward(request, response);
        }catch (Exception e){
            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/proyectosEnEjecucion")
                    .forward(request, response);
        }
    }
}