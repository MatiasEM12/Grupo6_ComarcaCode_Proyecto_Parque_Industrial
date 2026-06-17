package database.DAOs;

import model.DTO.LoteDTO;
import model.Lote;
import java.util.List;

public interface LoteDAO {

    void create(Lote lote);

    Lote find(int id);

    List<Lote> findAll();

    List<LoteDTO> findAllLoteDTO();

    Lote findLoteProyecto(int idProyecto);

    List<Lote> findDisponibles();

    void registrarProyectoLote(int id, int idProyecto);
    void update(Lote lote);
    void actualizarEstado(int id, String estado);
}