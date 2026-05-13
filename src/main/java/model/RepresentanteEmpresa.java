package model;

import database.DAOs.ReprecentanteEmpresaDAO;
import database.JDBCs.ReprecentanteEmpresaDAOJDBC;
import persistencia.PersistenceApi;

import java.util.ArrayList;
import java.util.List;

public class RepresentanteEmpresa extends Usuario{
    //no seria mejor DNI fuera de tipo int?
    private String dni;
    /*yo pondria una variable de tipo Empresa en vez de nombre de empresa, y que de hai recupere el nombre
    Private Empresa empresa;
     */
    private final String nombreEmpresa;
    private ReprecentanteEmpresaDAO reprecentanteEmpresaDAO = new ReprecentanteEmpresaDAOJDBC();

    public RepresentanteEmpresa(String userName, String contrasena,
                                Rol rol, String gmail, String dni,
                                String nombreEmpresa){
        super(userName, contrasena, rol, gmail);
        validarDni(dni);
        validarNombreEmpresa(nombreEmpresa);
        this.dni = dni;
        this.nombreEmpresa = nombreEmpresa;
    }

    public String dni(){
        return dni;
    }

    public String nombreEmpresa(){
        return nombreEmpresa;
    }

    public void validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío");
        }
        if (!dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener entre 7 y 8 números");
        }
    }

    public void validarNombreEmpresa(String nombreEmpresa){
        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la empresa no puede ser vacío");
        }
    }

    public List<SolicitudRadicacion> solicidesDeRadicacion(){
        /*esto seria asi:
        return persistenceApi.solicitudRadicacionDAO().filter(this.userName());
        yo tengo pensado la base de datos de solicitudRadicacion con referencia a nombre de usuuario
        ya que los nombres de los usuarios son unicos
         */
        return new ArrayList<>();
    }

    public void registrarUsuario() {
        reprecentanteEmpresaDAO.registrarReprecentante(this);
    }
}
