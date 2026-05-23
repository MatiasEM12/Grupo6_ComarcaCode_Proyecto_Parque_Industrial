package database.DAOs;

import model.Lote;
import model.RepresentanteEmpresa;

import java.util.List;

public interface LoteDAO {
    void registrarLote(Lote lote);
    List<Lote> lotesDisponibles();
    List<Lote> lotesDelUsuario(RepresentanteEmpresa usuario);
    List<Lote> lotesManegadosPorElPaque();
}
