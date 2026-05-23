package database.DAOs;

import model.ProyectoProductivo;
import model.RepresentanteEmpresa;

import java.util.List;

public interface ProyectoProductivoDAO {
    void cargar(ProyectoProductivo proyectoProductivo);
    ProyectoProductivo find(int idProyecto);
    List<ProyectoProductivo> findAll();
    List<ProyectoProductivo> findByRepresentante(RepresentanteEmpresa representanteEmpresa);
}