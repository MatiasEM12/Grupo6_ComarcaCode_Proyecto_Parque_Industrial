
package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Documento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/descargarDocumento")
public class DescargarDocumentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int idDocumento = Integer.parseInt(request.getParameter("id"));

            SistemaParqueIndustrial sistema = new ParqueIndustrial();

            Documento documento = sistema.obtenerDocumento(idDocumento);

            if (documento == null) {

                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Documento no encontrado");

                return;
            }

            String basePath = System.getProperty("user.dir");

            File archivo = new File(basePath + File.separator + documento.rutaArchivo());

            if (!archivo.exists()) {

                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado en disco");

                return;
            }

            response.setContentType("application/octet-stream");

            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\"" +
                            documento.nombreArchivo() +
                            "\""
            );

            response.setContentLengthLong(archivo.length());

            try (
                    FileInputStream fis = new FileInputStream(archivo);
                    OutputStream os = response.getOutputStream()
            ) {

                byte[] buffer = new byte[4096];
                int bytesLeidos;

                while ((bytesLeidos = fis.read(buffer)) != -1) {

                    os.write(buffer, 0, bytesLeidos
                    );
                }

                os.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}