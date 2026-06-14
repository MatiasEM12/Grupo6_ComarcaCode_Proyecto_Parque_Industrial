package database.DAOs;

import model.Documento;

import java.util.List;

public interface DocumentoDAO {

    void create(Documento documento);

    Documento find(int id);

    List<Documento> findAll();

    void update(Documento documento);

    void actualizarDocumento(int idDocumento, String nombre, String ruta, long tamanio);

    void remove(int id);

    Documento findPorRuta(String ruta);


}