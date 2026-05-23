package model.DTO;


import model.Usuario;



public record SolicitudRadicacionDTO(

        Usuario usuario,
        ProyectoDTO proyecto){
}