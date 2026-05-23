package database.DAOs;

import model.Lote;
import java.util.List;

public interface LoteDAO {

    void create(Lote lote);

    Lote find(int id);

    List<Lote> findAll();

    List<Lote> findDisponibles();

    void actualizarEstado(int id, String estado);
}