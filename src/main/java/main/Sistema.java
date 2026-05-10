package main;

import model.Rol;
import model.Usuario;

import java.util.List;

public class Sistema {

    private static List<Usuario> usuarios;

    public Sistema() {
        this.usuarios = List.of(
                new Usuario("juan", "1234",
                        new Rol("administrador",111),"pepe@gmail.com"),
                new Usuario("maria", "5678",
                        new Rol("representante",222 ),"representante@gmail.com"),
                new Usuario("pedro", "7890",
                        new Rol("organismo_publico",333 ),"pedro@gmail.com")
        );
    }


    public static List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public Usuario obtenerUsuarioPorUsername(String username) {

        return usuarios.stream().filter(usuario -> usuario.UserName().equals(username)).findFirst().orElse(null);

    }
}
