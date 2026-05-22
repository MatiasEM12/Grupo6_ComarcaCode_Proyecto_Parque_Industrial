package model;

import database.DAOs.RolDAO;
import database.JDBCs.RolDAOJDBC;

public class Rol {
    private String nombre;
    private Integer codigo;
    private RolDAO rolDAO=new RolDAOJDBC();
    public Rol(String nombre){
        validarNombreRol(nombre);
        this.nombre = nombre;

        rolDAO.create(this);
    }
    public Rol(String nombre, Integer codigo){
        validarNombreRol(nombre);
        validarCodigoRol(codigo);
        this.nombre = nombre;
        this.codigo = codigo;
    }

    private void validarNombreRol(String nombre){
        if(nombre == null )throw new RuntimeException("El nombre del rol no puede ser null");
        if(nombre.trim().isEmpty())throw new RuntimeException("El nombre del rol no puede ser vacio");
        if(nombre.length()<3)throw new RuntimeException("El nombre del rol debe tener al menos 3 caracteres");
        if(nombre.length()>20)throw new RuntimeException("El nombre del rol no puede tener mas de 20 caracteres");
        //if(existe(nombre))throw new RuntimeException("El nombre del rol ya existe");

    }

    private boolean existe(String nombre) {
        return this.rolDAO.existe(nombre);
    }

    private void validarCodigoRol(Integer codigo){
        if(codigo == null)throw new RuntimeException("El codigo del rol no puede ser null");
        if (codigo<0){
            throw new RuntimeException("Codigo de rol invalido");
        }
    }


    public String nombre(){
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    public Integer codigo(){
        return codigo;
    }

    public static Rol fromCodigo(int rol) {

        return switch (rol) {

            case 1 -> new Rol("administrador", 1);
            case 2 -> new Rol("representante", 2);
            case 3 -> new Rol("organismo_publico", 3);

            default ->
                    throw new RuntimeException("Código de rol inválido: " + rol);
        };
    }
}
