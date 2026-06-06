package database.DAOs;

import model.AvanceDeProyecto;

import java.util.List;

public interface AvanceDeProyectoDAO {

    public void create(AvanceDeProyecto avanceDeProyecto);

    AvanceDeProyecto find(int id);

    List<AvanceDeProyecto> findAllBy(int idProyecto);
}
