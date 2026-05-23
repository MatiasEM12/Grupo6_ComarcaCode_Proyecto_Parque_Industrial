package model.DTO;



import model.Usuario;

public record ProyectoDTO(

        String nombre,

        String objeto,

        String descripcionServicio,

        String emplazamiento,

        String tipoPersonal,

        int tiempoRadicacion,

        double metrosCuadrados,

        double areaTrabajo,

        double areaDeposito,

        double estacionamiento,

        boolean tienePlanos,

        int personalOcupar,

        String materiasPrimas,

        String destinoProduccion,

        String tension,

        double potencia,

        double agua,

        boolean necesitaGas,

        String residuos,

        boolean realizaTratamiento,

        boolean necesitaBalanza,

        boolean necesitaComedor,

        boolean necesitaCoworking,


        Usuario usuario
){}

