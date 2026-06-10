package vista.servlet;

import database.DAOs.ReporteDAO;
import database.JDBCs.ReporteDAOJDBC;
import model.Documento;
import model.Reporte;
import model.TipoDocumento;
import model.TipoReporte;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/reportes")
@MultipartConfig
public class ReportesServlet extends HttpServlet {

    private ReporteDAO reporteDAO = new ReporteDAOJDBC();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null || !usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        request.setAttribute("reportes", reporteDAO.findAll());
        request.getRequestDispatcher("/reportes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null || !usuario.nombreRol().equals("administrador")) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }

        try {
            String tipoTexto = request.getParameter("tipo");
            String descripcion = request.getParameter("descripcion");

            TipoReporte tipo = TipoReporte.valueOf(tipoTexto);

            Reporte reporte = new Reporte(tipo, descripcion, usuario);

            reporteDAO.guardar(reporte);

            Part archivo = request.getPart("documento");

            if (archivo != null && archivo.getSize() > 0) {

                String nombreArchivo = Paths.get(archivo.getSubmittedFileName())
                        .getFileName()
                        .toString();

                String carpetaUploads = getServletContext().getRealPath("/uploads");

                File carpeta = new File(carpetaUploads);

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                String rutaFinal = carpetaUploads + File.separator + nombreArchivo;

                archivo.write(rutaFinal);

                Documento documento = new Documento(
                        TipoDocumento.REPORTE,
                        nombreArchivo,
                        "uploads/" + nombreArchivo,
                        archivo.getSize()
                );

                reporteDAO.vincularDocumento(reporte.id(), documento.id());
            }

            request.setAttribute("mensaje", "Reporte generado correctamente.");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("reportes", reporteDAO.findAll());
        request.getRequestDispatcher("/reportes.jsp").forward(request, response);
    }
}