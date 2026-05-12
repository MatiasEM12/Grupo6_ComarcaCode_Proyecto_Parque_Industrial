package model.DTO;


import model.ProyectoProductivo;
import model.Usuario;

public record SolicitudRadicacionDTO(

        Usuario usuario,
        ProyectoProductivoDTO proyecto){
}