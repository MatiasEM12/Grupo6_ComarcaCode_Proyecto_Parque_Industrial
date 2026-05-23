package database.DAOs;

import model.Lote;
import java.util.List;

public interface LoteDAO {

    void create(Lote lote);

    Lote find(int id);

    List<Lote> findAll();

    Lote findLoteProyecto(int idProyecto);

    List<Lote> findDisponibles();

    void RegistrarProyectoLote(int id, int idProyecto);

    void actualizarEstado(int id, String estado);
}