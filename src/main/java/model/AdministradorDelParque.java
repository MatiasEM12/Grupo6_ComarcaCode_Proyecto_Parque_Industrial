package model;

import java.util.List;

public class AdministradorDelParque extends Usuario{
    private String dni;
    private String nombre;
    private List<Lote> loteList;
    private List<Reporte> reportes;
    private List<Observacion> observaciones;
    public AdministradorDelParque(String userName, String contrasena,
                                  Rol rol, String gmail, String dni, String nombre) {
        super(userName, contrasena, rol, gmail);
        this.dni = dni;
        this.nombre = nombre;
    }

    public void asignarLotes(Empresa empresa){
        for(Lote lote : loteList) {
            loteList.add(lote.asignarEmpresa(empresa));
        }
    }

    public void cambiarEstadoSolicitud(Observacion observacion){
        observacion.agregarRespuesta();
        observacion.actualizarDocumentacionSolicitud();
    }

    public Reporte generarReporte(TipoReporte tipo, String descripcion) {
        Reporte reporte=new Reporte(tipo, descripcion, this);
        adjuntarReporte(reporte);
        return reporte;
    }

    public boolean revisarDocumentacion(Reporte reporte){
        return reporte.tieneDocumentacionValida();
    }
    public void adjuntarReporte(Reporte reporte) {
        if (reporte == null) {
            throw new RuntimeException("El documento no puede ser nulo");
        }
        reportes.add(reporte);
    }
    public void agregarObservacion(Observacion observacion) {
        if (observacion == null) {
            throw new RuntimeException("La observación no puede ser nula");
        }

        observaciones.add(observacion);
    }

    public String dni(){
        return dni;
    }

    public String nombre(){
        return nombre;
    }

    public String usuario(){
        return getUserName();
    }
}
