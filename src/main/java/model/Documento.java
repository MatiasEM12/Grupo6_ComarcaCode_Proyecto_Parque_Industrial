package model;
public class Documento {
    private int id;
    private TipoDocumento tipo;
    private double tamanio;
    private String url;

    public Documento(int id, TipoDocumento tipo, double tamanio, String url) {
        validarTipo(tipo);
        validarTamanio(tamanio);
        validarUrl(url);
        this.id = id;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.url = url;
    }

    private static void validarUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new RuntimeException("La URL del documento es obligatoria");
        }
    }

    private static void validarTamanio(double tamanio) {
        if (tamanio <= 0) {
            throw new RuntimeException("El tamaño del documento debe ser mayor a 0");
        }
    }

    private static void validarTipo(TipoDocumento tipo) {
        if (tipo == null) {
            throw new RuntimeException("El tipo de documento es obligatorio");
        }
    }

    public boolean esValido() {
        return tipo != null && tamanio > 0 && url != null && !url.isBlank();
    }
}