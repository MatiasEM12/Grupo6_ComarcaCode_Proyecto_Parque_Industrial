package model.DTO;

public record ProyectoProductivoDTO(
        int idProyecto,
        String nombre,
        String descripcion,
        double superficie,
        String necesidades,
        int empleabilidad,
        String materiaPrima,
        boolean enEjecucion,
        EmpresaDTO empresa
) {
}