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

    public void observarSolicitud(SolicitudRadicacion solicitud, String descripcion) {
        Observacion observacion = new Observacion(descripcion);
        solicitud.agregarObservacion(observacion);
    }
    public void aprobarSolicitud(SolicitudRadicacion solicitud) {
        solicitud.aprobar();
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
}

