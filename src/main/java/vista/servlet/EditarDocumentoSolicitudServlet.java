package vista.servlet;

import database.JDBCs.DocumentoDAOJDBC;
import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/editarDocumentoSolicitud")
@MultipartConfig
public class EditarDocumentoSolicitudServlet extends HttpServlet {

    private SistemaParqueIndustrial sistema = new ParqueIndustrial();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        int idDocumento = Integer.parseInt(request.getParameter("idDocumento"));

        Part filePart = request.getPart("archivo");

        if (filePart == null || filePart.getSize() == 0) {
            throw new RuntimeException("Archivo vacío");
        }

        String fileName = filePart.getSubmittedFileName();
        String uniqueId = java.util.UUID.randomUUID().toString();
        String name = uniqueId + "_" + fileName;

        String basePath = System.getProperty("user.dir");
        String uploadDir = basePath + "/uploads";

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fullPath = uploadDir + File.separator + name;

        filePart.write(fullPath);


        sistema.actualizarDocumento(
                idDocumento,
                fileName,
                "uploads/" + name,
                filePart.getSize()
        );

        response.sendRedirect(
                request.getContextPath() + "/miSolicitudDetalle?id=" + idSolicitud
        );
    }
}