package database.persistencia;

import database.*;
import database.DAOs.AdministradorDelParqueDAO;
import database.DAOs.EmpresaDAO;
import database.DAOs.LoteDAO;
import database.JDBCs.AdministradorDelParqueDAOJDBC;
import database.JDBCs.EmpresaDAOJDBC;
import database.JDBCs.LoteDAOJDBC;
import database.ProyectoProductivoDAO;
import database.ProyectoProductivoDAOJDBC;
import database.ReprecentanteEmpresaDAO;
import database.ReprecentanteEmpresaDAOJDBC;
import database.RolDAO;
import database.RolDAOJDBC;
import database.UsuarioDAO;
import database.UsuarioDAOJDBC;
import model.Rol;
import model.Usuario;

public class PersistenceApi implements IApi{
    private EmpresaDAO empresaDAO;
    private AdministradorDelParqueDAO administradorDelParqueDAO;
    private LoteDAO loteDAO;
    private ProyectoProductivoDAO proyectoProductivoDAO;
    private ReprecentanteEmpresaDAO reprecentanteEmpresaDAO;
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    public PersistenceApi(){
        empresaDAO = new EmpresaDAOJDBC();
        administradorDelParqueDAO = new AdministradorDelParqueDAOJDBC();
        loteDAO = new LoteDAOJDBC();
        proyectoProductivoDAO = new ProyectoProductivoDAOJDBC();
        reprecentanteEmpresaDAO = new ReprecentanteEmpresaDAOJDBC();
        usuarioDAO = new UsuarioDAOJDBC();
        rolDAO = new RolDAOJDBC();
    }

    public void regisTrarUsuario(String userName, String contrasena, Integer rol, String gmail){
        Rol rolN = rolDAO.find(rol);
        Usuario usuario = new Usuario(userName, contrasena, rolN, gmail);
        this.usuarioDAO.registrar(usuario);
    }


}
