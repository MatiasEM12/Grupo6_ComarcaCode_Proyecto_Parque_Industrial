package model;

import database.ReprecentanteEmpresaDAO;
import database.ReprecentanteEmpresaDAOJDBC;
import database.UsuarioDAO;
import database.UsuarioDAOJDBC;
import model.DTO.SolicitudRadicacionDTO;

import java.util.ArrayList;
import java.util.List;



public class ParqueIndustrial implements SistemaParqueIndustrial {

    private UsuarioDAO usuarioDAO= new UsuarioDAOJDBC();
    private ReprecentanteEmpresaDAO representanteDAO= new ReprecentanteEmpresaDAOJDBC();

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        ArrayList<Usuario> usuarios = new ArrayList<>(obtenerUsuarios());
       return usuarios.stream()
                .filter(u -> u.UserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregarSolicitud(SolicitudRadicacionDTO solicitud) {
        var representante= representanteDAO.find(solicitud.usuario().UserName());

    }


    @Override
    public List<SolicitudRadicacion> obtenerSolicitudesDe(Usuario usuario) {
        return List.of();
    }

    @Override
    public List<SolicitudRadicacion> obtenerSolicitudes() {
        return List.of();
    }
}
