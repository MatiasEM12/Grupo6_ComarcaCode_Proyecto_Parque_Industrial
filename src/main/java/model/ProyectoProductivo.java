package model;

public class ProyectoProductivo {
    private String nombre;
    private String descripcion;
    private double superficie;
    private String necesidades;
    private int empleabilidad;
    private String materiaPrima;
    private boolean enEjecucion;
    private Empresa empresa;
    public ProyectoProductivo(String nombre, String descripcion,
                              double superficie, String necesidades,
                              int empleabilidad, String materiaPrima, Empresa empresa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
        this.empresa = empresa;
    }
    public void actualizarEstado(){

    }
    public boolean estaEnEjecucion(){
        if (!enEjecucion){
            return false;
        }
        return true;
    }

    public String nombre(){
        return nombre;
    }

    public String descripcion(){
        return descripcion;
    }

    public double superficie(){
        return superficie;
    }

    public String necesidades(){
        return necesidades;
    }

    public int empleabilidad(){
        return empleabilidad;
    }

    public String materiaPrima(){
        return materiaPrima;
    }

    public boolean enEjecucion(){
        return enEjecucion;
    }
    public Empresa empresa(){
        return empresa;
    }
}
