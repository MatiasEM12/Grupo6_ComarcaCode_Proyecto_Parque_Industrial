package database.DAOs;

import model.AvanceDeProyecto;
import model.EstadoProyecto;

import java.util.List;

public interface AvanceDeProyectoDAO {

    public int create(AvanceDeProyecto avanceDeProyecto);

    void actualizarEstado(int idAvance, EstadoProyecto estado);

    AvanceDeProyecto find(int id);

    List<AvanceDeProyecto> findAllBy(int idProyecto);
}
