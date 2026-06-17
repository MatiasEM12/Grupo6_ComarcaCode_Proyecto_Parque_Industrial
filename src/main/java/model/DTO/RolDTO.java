
package model.DTO;

public record RolDTO(
        Integer codigo,
        String nombre
) {
    public RolDTO(Integer codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodgicoRol(){
        return codigo;
    }

    public String getNombreRol(){
        return nombre;
    }
}