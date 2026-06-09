package vista.servlet;

import database.persistencia.ParqueIndustrial;
import database.persistencia.SistemaParqueIndustrial;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/registro.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        SistemaParqueIndustrial sistema = new ParqueIndustrial();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gmail = request.getParameter("gmail");

        String rol = request.getParameter("rol");

        if (username == null || password == null || rol == null) {
            response.sendRedirect(request.getContextPath() + "/registro.jsp");
            return;
        }



        try {


            switch (rol) {


                case "ADMIN":

                    String dniAdmin = request.getParameter("dni");
                    String nombreAdmin = request.getParameter("nombre");

                    sistema.registrarAdmin(username,
                            password,new Rol("administrador", 1),
                            gmail,
                            dniAdmin,
                            nombreAdmin);

                    break;


                case "ORGANISMO":

                    int saf = Integer.parseInt(request.getParameter("saf"));
                    String nombreOrg = request.getParameter("nombreOrganismo");
                    String tipoOrgStr = request.getParameter("tipoOrganismo");

                    sistema.registrarOrganismoPrublico(
                            username,
                            password,
                            gmail,
                            saf,
                            nombreOrg,
                            TipoOrganismo.valueOf(tipoOrgStr)

                    );


                    break;

                case "REPRESENTANTE":

                    String dniRep = request.getParameter("dni");

                    sistema.registrarRepresentanteEmpresa(
                            request.getParameter("cuit"),
                            request.getParameter("razonSocial"),
                            request.getParameter("contacto"),
                            request.getParameter("contactoRep"),
                            false,
                            username,
                            password,
                            new Rol("REPRESENTANTE", 3),
                            gmail,dniRep,
                            true

                    );


                    break;

                default: throw new RuntimeException("Rol inválido");
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error", "Error al registrar usuario");
            request.getRequestDispatcher("/registro.jsp").forward(request, response);
        }
    }
}