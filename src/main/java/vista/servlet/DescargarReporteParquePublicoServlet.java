package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.DTO.ReporteParqueDTO;
import model.ProyectoProductivo;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/descargarReporteParquePublico")
public class DescargarReporteParquePublicoServlet extends HttpServlet {

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

            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte-parque-industrial.txt\"");

            try (PrintWriter out = response.getWriter()) {

                out.println("REPORTE DEL PARQUE INDUSTRIAL DE VIEDMA");
                out.println("======================================");
                out.println("Fecha de generación: " + reporte.fechaGeneracion());
                out.println();

                out.println("RESUMEN GENERAL");
                out.println("---------------");
                out.println("Total de proyectos: " + reporte.totalProyectos());
                out.println("Proyectos en ejecución: " + reporte.proyectosEnEjecucion());
                out.println("Lotes ocupados: " + reporte.lotesOcupados());
                out.println();

            }
        } catch (Exception e) {

            e.printStackTrace();

            request.getSession().setAttribute("error", "Error al generar reporte: " + e.getMessage());

            response.sendRedirect(request.getContextPath() + "/reportes");
        }
    }
}
