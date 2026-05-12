package model.DTO;


import model.Usuario;

public record SolicitudRadicacionDTO(

        Usuario usuario,
        String objeto,
        String nombreProyecto,
        String descripcionServicio,
        String emplazamiento,
        String personal,
        String tiempoRadicacion,
        String m2,
        String areaTrabajo,
        String areaDeposito,
        String estacionamiento,
        String planos,
        String empleabilidad,
        String materiasPrimas,
        String destinoProduccion,
        String tension,
        String potencia,
        String agua,
        String gas,
        String residuos,
        String tratamiento,
        String balanza,
        String comedor,
        String coworking,
        String descripcionArchivo,
        String nombreArchivoPDF

) {
}