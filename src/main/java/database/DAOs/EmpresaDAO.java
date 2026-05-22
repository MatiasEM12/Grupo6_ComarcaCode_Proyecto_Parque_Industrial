package database.DAOs;

import model.DTO.EmpresaDTO;
import model.Empresa;

public interface EmpresaDAO {
    void registrarEmpresa(Empresa empresa);

    void actualizar(EmpresaDTO empresa);
}
