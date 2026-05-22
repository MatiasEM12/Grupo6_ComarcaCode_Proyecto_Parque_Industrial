package database.DAOs;

import java.util.List;

import model.SolicitudRadicacion;

public interface SolicitudRadicacionDAO {
    void create(SolicitudRadicacion solicitudRadicacion);

    void update(SolicitudRadicacion solicitudRadicacion);

    void remove(Integer id);

    void remove(SolicitudRadicacion solicitudRadicacion);

    SolicitudRadicacion find(Integer id);

    List<SolicitudRadicacion> findAll();

    //int obtenerCantidadSolicitudes();
}