package model.DTO;


public record EmpresaDTO(
        String cuit,
        String razonSocial,
        String contacto,
        String contactoRepresentante,
        Boolean radicada,
        LoteDTO lote
) {
}