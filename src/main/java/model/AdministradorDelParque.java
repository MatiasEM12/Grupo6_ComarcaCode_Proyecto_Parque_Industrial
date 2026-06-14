package model;

import database.DAOs.AdministradorDelParqueDAO;
import database.JDBCs.AdministradorDelParqueDAOJDBC;

import java.util.List;

public class AdministradorDelParque extends Usuario{

    private String dni;
    private String nombre;
    private List<Lote> loteList;
    private List<Informe> informes;
    private List<Observacion> observaciones;
    private AdministradorDelParqueDAO administradorDelParqueDAO = new AdministradorDelParqueDAOJDBC();
    public AdministradorDelParque(String userName, String contrasena, Rol rol, String gmail, String dni, String nombre) {
        super(userName, contrasena, rol, gmail);
        this.dni = dni;
        this.nombre = nombre;
        administradorDelParqueDAO.registrarAdministrador(this);
    }

    public AdministradorDelParque(String userName, String contrasena, Rol rol, String gmail, String dni, String nombre, int codigoUsuario) {
        super(codigoUsuario,userName, contrasena, rol, gmail);
        this.dni = dni;
        this.nombre = nombre;


    }

    public void asignarLotes(Empresa empresa){

    }

    public void cambiarEstadoSolicitud(Observacion observacion){
       /* observacion.agregarRespuesta();
        observacion.actualizarDocumentacionSolicitud();*/  //???
    }

    public Informe generarReporte(TipoInforme tipo, String descripcion) {
        Informe informe =new Informe(tipo, descripcion, this);
        adjuntarReporte(informe);
        return informe;
    }

    public boolean revisarDocumentacion(Informe informe){
        return informe.tieneDocumentacionValida();
    }
    public void adjuntarReporte(Informe informe) {
        if (informe == null) {
            throw new RuntimeException("El documento no puede ser nulo");
        }
        informes.add(informe);
    }
    public void agregarObservacion(Observacion observacion) {
        if (observacion == null) {
            throw new RuntimeException("La observación no puede ser nula");
        }

        observaciones.add(observacion);
    }

    public String dni(){
        return dni;
    }

    public String nombre(){
        return nombre;
    }

    public String usuario(){
        return UserName();
    }
}
