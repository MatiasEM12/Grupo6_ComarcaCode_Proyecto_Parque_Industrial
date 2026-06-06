package database.DAOs;

import model.EstadoProyecto;
import model.ProyectoProductivo;
import java.util.List;

public interface ProyectoProductivoDAO {

    void registrarProyectoProductivo(ProyectoProductivo proyectoProductivo);

    ProyectoProductivo find(int idProyecto);

    List<ProyectoProductivo> findAll();

    List<ProyectoProductivo> findByEmpresa(String cuitEmpresa);

    void actualizarEstado(int idProyecto, boolean estado);

    void actualizarEstadoProyecto(int idProyecto, EstadoProyecto estado);
}