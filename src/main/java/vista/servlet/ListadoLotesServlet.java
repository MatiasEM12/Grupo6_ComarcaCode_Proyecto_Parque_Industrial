package vista.servlet;


import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.Lote;
import model.SolicitudRadicacion;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/listadoLotes")
public class ListadoLotesServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(
                    request.getContextPath() + "/perfiles"
            );
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");



        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/perfiles");
            return;
        }
        SistemaParqueIndustrial sistema = new ParqueIndustrial();
        /*Sistema sistema =
                (Sistema) getServletContext()
                        .getAttribute("sistema");

         */



        List<Lote> lotes= sistema.ObtenerLotes();


        request.setAttribute("lotes",lotes);

        /*
         * Redirecciona al JSP
         */
        request.getRequestDispatcher("/listadoLotes.jsp").forward(request, response);
    }

}
