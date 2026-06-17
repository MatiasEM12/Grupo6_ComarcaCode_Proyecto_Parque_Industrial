package vista.servlet;

import database.DAOs.InformeDAO;
import database.JDBCs.InformeDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Documento;
import model.Informe;
import model.TipoDocumento;
import model.TipoInforme;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/Informes")
@MultipartConfig
public class InformesServlet extends HttpServlet {

    private InformeDAO informeDAO = new InformeDAOJDBC();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null || !usuario.nombreRol().equals("administrador")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            request.setAttribute("informes", informeDAO.findAll());
            request.getRequestDispatcher("/Informes.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/Informes.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null || !usuario.nombreRol().equals("administrador")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            try {
                String tipoTexto = request.getParameter("tipo");
                String descripcion = request.getParameter("descripcion");

                TipoInforme tipo = TipoInforme.valueOf(tipoTexto);

                Informe informe = new Informe(tipo, descripcion, usuario);

                informeDAO.guardar(informe);

                Part archivo = request.getPart("documento");

                if (archivo != null && archivo.getSize() > 0) {

                    String nombreArchivo = Paths.get(archivo.getSubmittedFileName())
                            .getFileName()
                            .toString();


                    String basePath = System.getProperty("user.dir");
                    String carpetaUploads = basePath + File.separator + "uploads";

                    File carpeta = new File(carpetaUploads);
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }

                    String nombreUnico = java.util.UUID.randomUUID().toString() + "_" + nombreArchivo;

                    String rutaFinal = carpetaUploads + File.separator + nombreUnico;

                    archivo.write(rutaFinal);

                    String rutaCompleta = "uploads/" + nombreUnico;

                    Documento documento = new Documento(
                            TipoDocumento.INFORME,
                            nombreArchivo,
                            rutaCompleta,
                            archivo.getSize()
                    );

                    ParqueIndustrial sistema = new ParqueIndustrial();
                    Documento d = sistema.obtenerDocumentoPorRuta(rutaCompleta);

                    informeDAO.vincularDocumento(informe.id(), d.id());
                }

                request.setAttribute("mensaje", "Informe generado correctamente.");

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }

            request.setAttribute("informes", informeDAO.findAll());
            request.getRequestDispatcher("/Informes.jsp").forward(request, response);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/Informes.jsp")
                    .forward(request, response);
        }
    }
}