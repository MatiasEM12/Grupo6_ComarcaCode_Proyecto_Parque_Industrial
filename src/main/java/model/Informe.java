package model;

import database.DAOs.InformeDAO;
import database.JDBCs.InformeDAOJDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Informe {
    private int id = 0;
    private final TipoInforme tipo;
    private final String descripcion;
    private final LocalDate fecha;
    private final Usuario usuario; //Lo cree porque es necesario para saber quien genero el informe
    private final List<Documento> documentos;
    private InformeDAO informeDAO = new InformeDAOJDBC();

    public Informe(TipoInforme tipo, String descripcion, Usuario generadoPor) {
        validarTipo(tipo);
        validarDescripcion(descripcion);
        validarUsuario(generadoPor);

        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.usuario = generadoPor;
        this.documentos = new ArrayList<>();
    }

    private static void validarUsuario(Usuario generadoPor) {
        if (generadoPor == null) {
            throw new RuntimeException("Debe indicarse quién generó el informe");
        }
    }

    private static void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("La descripción del informe es obligatoria");
        }
    }

    private static void validarTipo(TipoInforme tipo) {
        if (tipo == null) {
            throw new RuntimeException("El tipo de informe es obligatorio");
        }
    }
    public boolean tieneDocumentacionValida() {
        return !documentos.isEmpty()
                && documentos.stream().allMatch(Documento::esValido);
    }
    public void adjuntarDocumento(Documento documento) {
        if (documento == null) {
            throw new RuntimeException("El documento no puede ser nulo");
        }

        documentos.add(documento);
    }


    public String descripcion() {
        return descripcion;
    }
    public int id() {
        return id;
    }

    public TipoInforme tipo() {
        return tipo;
    }

    public LocalDate fecha() {
        return fecha;
    }

    public Usuario usuario() {
        return usuario;
    }

    public List<Documento> documentos() {

        if(documentos.isEmpty()){
            Documento documento =  informeDAO.obtenerDocumento(id);
            if(documento!=null){
                return List.of( documento);
            }

        }
        return documentos;
    }

    public void asignarId(int id) {
        this.id = id;
    }

}
