package persistencia;

import database.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

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

    public void regisTrarUsuario(Usuario usuario){
        this.usuarioDAO.registrar(usuario);
        usuario.registrarUsuario(this);
    }

    public void inicialisarUsuarios(){
        Usuario usuario1 = new AdministradorDelParque("juan", "1234",
                new Rol("administrador",111),
                "pepe@gmail.com", "43212233", "Juan");
        Usuario usuario2 = new RepresentanteEmpresa("maria", "5678",
                new Rol("representante",222), "representante@gmail.com",
                "20022222", "binbo");

        Usuario usuario3 = new OrganismoPublico("pedro", "7890",
                "pedro@gmail.com", "Pedro", new Rol("organismo_publico",333),
                TipoOrganismo.MUNICIPAL, new SistemaParque());

        this.regisTrarUsuario(usuario1);
        this.regisTrarUsuario(usuario2);
        this.regisTrarUsuario(usuario3);
    }

    public AdministradorDelParqueDAO administradorDelParqueDAO(){
        return administradorDelParqueDAO;
    }

    public ReprecentanteEmpresaDAO reprecentanteEmpresaDAO(){
        return reprecentanteEmpresaDAO;
    }

    public UsuarioDAO usuarioDAO(){
        return usuarioDAO;
    }


    /*para cuando se cree el organismo publico dao
    public OrganismoPublicoDAO organismoPublicoDAO(){
        return OrganismoPublicoDAO;
    }
     */



}
