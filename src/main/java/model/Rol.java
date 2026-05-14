package model;

public class Rol {
    private String nombre;
    private Integer codigo;

    public Rol(String nombre, Integer codigo){
        validarNombreRol(nombre);
        validarCodigoRol(codigo);
        this.nombre = nombre;
        this.codigo = codigo;
    }

    private void validarNombreRol(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new RuntimeException("Nombre del rol es invalido");
        }
    }

    private void validarCodigoRol(Integer codigo){
        if (codigo<0){
            throw new RuntimeException("Codigo de rol invalido");
        }
    }
    public String nombre(){
        return toString();
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
