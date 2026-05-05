package model;

import java.util.List;

public class AdministradorDelParque extends Usuario{
    private String dni;
    private String nombre;
    private List<Lote> loteList;
    private List<Reporte> reportes;
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
        return new Reporte(tipo, descripcion, this);
    }

    public boolean revisarDocumentacion(Reporte reporte){
        return reporte.tieneDocumentacionValida();
    }

    public void registrarObservacion(Observacion observacion){

    }
}
