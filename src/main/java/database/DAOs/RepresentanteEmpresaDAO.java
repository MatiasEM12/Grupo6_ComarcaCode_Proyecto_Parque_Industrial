package database.DAOs;

import model.RepresentanteEmpresa;

import java.util.List;

public interface RepresentanteEmpresaDAO {
    void registrarRepresentante(RepresentanteEmpresa representanteEmpresa);

    void update(RepresentanteEmpresa representanteEmpresa);

    void remove(String dni);

    RepresentanteEmpresa find(String dni);
    RepresentanteEmpresa findByUserName(String userName);
    List<RepresentanteEmpresa> findAll();

    boolean existe(String dni);
}
