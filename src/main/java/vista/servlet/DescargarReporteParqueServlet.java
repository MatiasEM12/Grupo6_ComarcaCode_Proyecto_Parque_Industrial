package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.DTO.ReporteParqueDTO;
import model.ProyectoProductivo;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/descargarReporteParque")
public class DescargarReporteParqueServlet extends HttpServlet {

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
                out.println("Proyectos finalizados: " + reporte.proyectosFinalizados());
                out.println("Proyectos suspendidos: " + reporte.proyectosSuspendidos());
                out.println("Proyectos sin iniciar: " + reporte.proyectosSinIniciar());
                out.println("Total de lotes: " + reporte.totalLotes());
                out.println("Lotes disponibles: " + reporte.lotesDisponibles());
                out.println("Lotes ocupados: " + reporte.lotesOcupados());
                out.println("Evaluaciones técnicas registradas: " + reporte.totalEvaluacionesTecnicas());
                out.println("Empleabilidad total estimada: " + reporte.empleabilidadTotal() + " empleados");
                out.println("Superficie total usada por proyectos: " + reporte.superficieTotalProyectos() + " m2");
                out.println();
                out.println("PROYECTOS PRODUCTIVOS");
                out.println("---------------------");

                for (ProyectoProductivo proyecto : reporte.proyectos()) {
                    out.println("Proyecto: " + proyecto.nombre());
                    out.println("Estado: " + proyecto.estado());
                    out.println("Descripción: " + proyecto.descripcion());
                    out.println("Superficie: " + proyecto.superficie() + " m2");
                    out.println("Empleabilidad: " + proyecto.empleabilidad() + " empleados");
                    out.println("Materia prima: " + proyecto.materiaPrima());
                    out.println();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            request.getSession().setAttribute("error", "Error al generar reporte: " + e.getMessage());

            response.sendRedirect(request.getContextPath() + "/reportes");
        }
    }
}
