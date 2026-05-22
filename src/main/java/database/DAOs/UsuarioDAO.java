package database.DAOs;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
    void registrar(Usuario usuario);

    void update(Usuario usuario);

    void remove(String userName);

    Usuario find(String userName);

    List<Usuario> findAll();

    Boolean existe(String userName);
}
