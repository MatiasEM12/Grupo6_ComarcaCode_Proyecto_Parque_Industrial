package vista.servlet;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfWriter;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Usuario;
import model.DTO.EvaluacionTecnicaDTO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/descargarEvaluacionTecnicaPDF")
public class DescargarEvaluacionTecnicaPDFServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema;

    @Override
    public void init() {
        this.sistema = new ParqueIndustrial();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null || !usuario.nombreRol().equals("organismo_publico")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        int idEvaluacion = Integer.parseInt(request.getParameter("idEvaluacion"));

        EvaluacionTecnicaDTO evaluacion =
                sistema.obtenerEvaluacionTecnica(idEvaluacion);

        if (evaluacion == null) {
            response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=\"evaluacion_tecnica_" + idEvaluacion + ".pdf\""
        );

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Evaluación Técnica", titulo));
            document.add(new Paragraph("Parque Industrial Viedma", normal));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Datos del proyecto", subtitulo));
            document.add(new Paragraph("Proyecto: " + evaluacion.nombreProyecto(), normal));
            document.add(new Paragraph("ID Proyecto: " + evaluacion.idProyecto(), normal));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Datos de la evaluación", subtitulo));
            document.add(new Paragraph("ID Evaluación: " + evaluacion.id(), normal));
            document.add(new Paragraph("Fecha: " + evaluacion.fecha(), normal));
            document.add(new Paragraph("Resultado: " + evaluacion.resultado(), normal));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Descripción:", subtitulo));
            document.add(new Paragraph(evaluacion.descripcion(), normal));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Observaciones:", subtitulo));
            document.add(new Paragraph(
                    evaluacion.observaciones() == null ? "Sin observaciones" : evaluacion.observaciones(),
                    normal
            ));

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF de evaluación técnica", e);
        }
    }
}