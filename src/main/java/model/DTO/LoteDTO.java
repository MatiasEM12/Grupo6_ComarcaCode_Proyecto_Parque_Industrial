package model.DTO;

public record LoteDTO(
        Integer id,
        String ubicacion,
        Double superficie,
        String estado,
        String infraestructura
) {
}
