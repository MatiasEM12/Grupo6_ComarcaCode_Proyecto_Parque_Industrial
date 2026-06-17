package model;

import database.DAOs.OrganismoPublicoDAO;
import database.JDBCs.OrganismoPublicoDAOJDBC;


import java.util.List;

public class OrganismoPublico extends Usuario {
    private int SAF;
    private String nombre;
    private TipoOrganismo tipoOrganismo;

    private OrganismoPublicoDAO organismoPublicoDAO = new OrganismoPublicoDAOJDBC();
    public OrganismoPublico(String username, String contraseña, String gmail,
                            int SAF, String nombre, TipoOrganismo tipoOrganismo,Rol rol) {

        super(username, contraseña, rol, gmail);

        validarSAF(SAF);
        validarTipoOrganismo(tipoOrganismo);
        this.SAF = SAF;
        this.nombre = nombre;
        this.tipoOrganismo = tipoOrganismo;
        organismoPublicoDAO.registrarOrganismoPublico(this);

    }

    public OrganismoPublico(String username, String contraseña, String gmail,
                            int SAF, String nombre, TipoOrganismo tipoOrganismo,
                            Rol rol, int codigoUsuario) {

        super(codigoUsuario, username, contraseña, rol, gmail);
        this.SAF = SAF;
        this.nombre = nombre;
        this.tipoOrganismo = tipoOrganismo;
    }

    private void validarSAF(int saf){
        if (saf <= 0) {
            throw new RuntimeException(
                    "saf obligatorio"
            );
        }
    }

    private void validarTipoOrganismo(TipoOrganismo organismo){
        if(organismo==null) throw  new NullPointerException("tipoOrganismo no puede ser nulo");
    }

    public int saf() {
        return SAF;
    }

    public String nombre() {
        return nombre;
    }

    public TipoOrganismo tipoOrganismo() {
        return tipoOrganismo;
    }

    public String usuario() {
        return UserName();
    }


}