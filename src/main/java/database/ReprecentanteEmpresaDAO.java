package database;

import model.RepresentanteEmpresa;

public interface ReprecentanteEmpresaDAO {
    void registrarReprecentante(RepresentanteEmpresa representanteEmpresa);

    void update(RepresentanteEmpresa representanteEmpresa);

    void remove(String dni);

    RepresentanteEmpresa find(String dni);


}
