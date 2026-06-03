package model;

import java.time.LocalDate;

public class Documento {

    private int id;
    private TipoDocumento tipo;
    private String nombreArchivo;
    private String rutaArchivo;
    private long tamanioBytes;
    private LocalDate fechaCarga;


    public Documento(
            TipoDocumento tipo,
            String nombreArchivo,
            String rutaArchivo,
            long tamanioBytes) {


        validarTipo(tipo);
        validarNombreArchivo(nombreArchivo);
        validarRutaArchivo(rutaArchivo);
        validarTamanio(tamanioBytes);


        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tamanioBytes = tamanioBytes;
        this.fechaCarga= LocalDate.now();
    }
    public Documento(
            int id,
            TipoDocumento tipo,
            String nombreArchivo,
            String rutaArchivo,
            long tamanioBytes,
            LocalDate fechaCarga) {

        validarId(id);
        validarTipo(tipo);
        validarNombreArchivo(nombreArchivo);
        validarRutaArchivo(rutaArchivo);
        validarTamanio(tamanioBytes);
        validarFecha(fechaCarga);

        this.id = id;
        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tamanioBytes = tamanioBytes;
        this.fechaCarga= fechaCarga;
    }

    private void validarId(int id) {

        if(id <= 0){
            throw new RuntimeException(
                    "El id del documento debe ser mayor a 0"
            );
        }
    }

    private void validarTipo(TipoDocumento tipo) {

        if(tipo == null){
            throw new RuntimeException(
                    "El tipo de documento es obligatorio"
            );
        }
    }

    private void validarNombreArchivo(String nombreArchivo) {

        if(nombreArchivo == null || nombreArchivo.isBlank()){
            throw new RuntimeException(
                    "El nombre del archivo es obligatorio"
            );
        }
    }

    private void validarRutaArchivo(String rutaArchivo) {

        if(rutaArchivo == null || rutaArchivo.isBlank()){
            throw new RuntimeException(
                    "La ruta del archivo es obligatoria"
            );
        }
    }

    private void validarTamanio(long tamanioBytes) {

        if(tamanioBytes <= 0){
            throw new RuntimeException(
                    "El tamaño del archivo debe ser mayor a 0"
            );
        }
    }

    private void validarFecha(LocalDate fechaCarga) {

        if (fechaCarga == null) {
            throw new RuntimeException(
                    "La fecha de carga es obligatoria"
            );
        }
    }



    public LocalDate fechaCarga() {
        return fechaCarga;
    }

    public int id() {
        return id;
    }

    public TipoDocumento tipo() {
        return tipo;
    }

    public String nombreArchivo() {
        return nombreArchivo;
    }

    public String rutaArchivo() {
        return rutaArchivo;
    }

    public long tamanioBytes() {
        return tamanioBytes;
    }

    public boolean esValido() {

        return tipo != null
                && nombreArchivo != null
                && !nombreArchivo.isBlank()
                && rutaArchivo != null
                && !rutaArchivo.isBlank()
                && tamanioBytes > 0;
    }
}