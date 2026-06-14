package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/subirAvance")
@MultipartConfig
public class SubirAvanceServlet extends HttpServlet {


    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcionAvance");
            EstadoProyecto estado = EstadoProyecto.valueOf(request.getParameter("estado"));

            ProyectoProductivo proyecto = sistema.obtenerProyecto(idProyecto);

            if (proyecto == null) {
                response.sendRedirect(request.getContextPath() + "/misProyectos");
                return;
            }

            AvanceDeProyecto avance = new AvanceDeProyecto(proyecto, descripcion, estado);

            int idAvance = sistema.cargarAvanceProyecto(usuario, avance, idProyecto);


            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            List<Documento> documentos = new ArrayList<>();

            String[] tipos = request.getParameterValues("tipoDocumento");

            Collection<Part> parts = request.getParts();

            List<Part> archivos = new ArrayList<>();

            for (Part part : parts) {
                if ("archivoDocumento".equals(part.getName()) && part.getSize() > 0) {
                    archivos.add(part);
                }
            }


            for (int i = 0; i < archivos.size(); i++) {

                Part archivo = archivos.get(i);

                TipoDocumento tipo = TipoDocumento.valueOf(tipos[i]);

                String nombreOriginal = archivo.getSubmittedFileName();

                String nombreFinal = java.util.UUID.randomUUID() + "_" + nombreOriginal;

                String rutaCompleta = UPLOAD_DIR + nombreFinal;

                System.out.println(rutaCompleta);
                archivo.write(rutaCompleta);

                Documento documento = new Documento(
                        tipo,
                        nombreOriginal,
                        rutaCompleta,
                        archivo.getSize()
                );


                documentos.add(sistema.obtenerDocumentoPorRuta(rutaCompleta));
            }

            sistema.cargarDocumentosEnAvance(idAvance, documentos);

            response.sendRedirect(request.getContextPath() + "/representanteProyecto?id=" + idProyecto);
        }catch (Exception e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher("/misProyectos")
                    .forward(request, response);
        }
    }
}