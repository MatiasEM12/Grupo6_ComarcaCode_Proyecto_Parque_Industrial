package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.EvaluacionTecnica;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/guardarEvaluacionTecnica")
public class GuardarEvaluacionTecnicaServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;

    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null || !usuario.nombreRol().equals("organismo_publico")) {
                response.sendRedirect(request.getContextPath() + "/perfiles");
                return;
            }

            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            String resultado = request.getParameter("resultado");
            String observaciones = request.getParameter("observaciones");

            EvaluacionTecnica evaluacion = new EvaluacionTecnica(
                    descripcion,
                    usuario,
                    resultado,
                    observaciones
            );


            sistema.agregarEvaluacionTecnica(idProyecto, evaluacion);

            response.sendRedirect(request.getContextPath()
                    + "/evaluacionTecnica?idProyecto=" + idProyecto);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/proyectosEnEjecucion")
                    .forward(request, response);
        }
    }
}