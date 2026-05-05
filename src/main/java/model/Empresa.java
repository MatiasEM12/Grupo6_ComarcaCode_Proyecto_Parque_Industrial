package model;

public class Empresa extends Usuario{
    private String razonSocial;
    private String cuit;
    private String contacto;
    private String contactoRepresentante;
    private Boolean radicada;

    public Empresa(String username, String contraseña, String gmail,
                   String razonSocial, String cuit, String contacto, String contactoRepresentante) {
        super(username, contraseña,new Rol("Empresa",1), gmail);
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.contacto = contacto;
        this.contactoRepresentante = contactoRepresentante;
    }

    public void guardarEmpresa(EmpresaCargar empresa){
        empresa.guardar(this);
    }

    public void modificarDato(EmpresaCargar empresa){
        if (!this.radicada){
            throw new RuntimeException("No se pueden modificar los datos de una empresa no Radicada");
        }
        empresa.aplicarModificacion(this);
    }
    public boolean radicada(boolean esRadicada){ //lo debe confimar el sistema una vez validada la empresa
        return radicada=esRadicada;
    }



}
