package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Documento;
import model.TipoDocumento;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cargarArchivosSolicitud")
@MultipartConfig
public class CargarArchivosSolicitudServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");



        String id = request.getParameter("idSolicitud");

        System.out.println("idSolicitud = [" + id + "]");

        int idSolicitud = Integer.parseInt(id);
        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        List<Documento> documentos = new ArrayList<>();

        documentos.add(armarDocumento(request, "PLANO_IMPLANTACION", TipoDocumento.PLANO_IMPLANTACION));

        documentos.add(armarDocumento(request, "IMPACTO_AMBIENTAL", TipoDocumento.IMPACTO_AMBIENTAL));

        documentos.add(armarDocumento(request, "ESTUDIO_MERCADO", TipoDocumento.ESTUDIO_MERCADO));

        documentos.add(armarDocumento(request, "MEMORIA_DESCRIPTIVA", TipoDocumento.MEMORIA_DESCRIPTIVA));

        documentos.add(armarDocumento(request, "REQUERIMIENTOS_INFRAESTRUCTURA", TipoDocumento.REQUERIMIENTOS_INFRAESTRUCTURA));

        for (Documento d : documentos) {
            if (d != null) {
                sistema.agregarDocumentoSolicitud(idSolicitud, d);
            }
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/miSolicitudDetalle?id="
                        + idSolicitud
        );
    }

    private Documento armarDocumento(HttpServletRequest request, String inputName, TipoDocumento tipo) throws IOException, ServletException {

        Part filePart = request.getPart(inputName);

        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        String fileName = filePart.getSubmittedFileName();

        String uniqueId = java.util.UUID.randomUUID().toString();
        String name = uniqueId + "_" + fileName;

        String basePath = System.getProperty("user.dir");
        String uploadDir = basePath + "/uploads";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fullPath = uploadDir + File.separator + name;

        filePart.write(fullPath);

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        sistema.cargarDocumento(
                tipo,
                fileName,
                "uploads/" + name,
                filePart.getSize()
        );

        Documento documento = sistema.obtenerDocumentoPorRuta("uploads/" + name);

        if (documento == null) {
            throw new RuntimeException(
                    "No se pudo recuperar el documento recién guardado"
            );
        }

        return documento;
    }
}