package vista.servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import database.DAOs.DocumentoDAO;
import database.DAOs.ReporteDAO;
import database.JDBCs.DocumentoDAOJDBC;
import database.JDBCs.ReporteDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Documento;
import model.Reporte;
import model.TipoDocumento;
import model.TipoReporte;
import model.Usuario;
import model.DTO.ReporteDTO;

@WebServlet("/crearReporte")
@MultipartConfig
public class CrearReporteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        if (!usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        try {
            TipoReporte tipo = TipoReporte.valueOf(request.getParameter("tipo"));

            String descripcion = request.getParameter("descripcion");

            Reporte reporte = new Reporte(tipo, descripcion, usuario);

            ReporteDTO reporteDTO = new ReporteDTO(tipo, descripcion, reporte.fecha(), usuario, new ArrayList<>());
            
            int idReporte = sistema.generarReporte(reporteDTO);

            Part archivo = request.getPart("documento");

            if (archivo != null && archivo.getSize() > 0) {
                String nombreArchivo = archivo.getSubmittedFileName();
                String id = UUID.randomUUID().toString();
                String nombreUnico = id + "_" + nombreArchivo;

                String basePath = System.getProperty("user.dir");
                String uploadDir =basePath + "/uploads/reportes";

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String FullPath = uploadDir + File.separator + nombreUnico;

                archivo.write(FullPath);

                Documento documento = new Documento(0, TipoDocumento.PDF, nombreArchivo, "uploads/reportes/" + nombreUnico, archivo.getSize(), LocalDate.now());

                sistema.cargarDocumentoPorReporte(documento, idReporte);
            }

            response.sendRedirect(request.getContextPath() + "/perfiles"); //modificar para redirigir a la pagina que se desee

        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
        }
    }

}
