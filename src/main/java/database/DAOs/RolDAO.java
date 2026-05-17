package database.DAOs;

import model.Rol;

import java.util.List;

public interface RolDAO {
    void create(Rol rol);

    void update(Rol rol);

    void remove(Integer codigo);

    void remove(Rol rol);

    Rol find(Integer codigo);

    List<Rol> findAll();

    int obtenerCantidadRoles();

    Boolean existe(String nombre);
}
