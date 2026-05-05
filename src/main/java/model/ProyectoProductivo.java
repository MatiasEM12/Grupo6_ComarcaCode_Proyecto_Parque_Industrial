package model;

public class ProyectoProductivo {
    private String nombre;
    private String descripcion;
    private double superficie;
    private String necesidades;
    private int empleabilidad;
    private String materiaPrima;
    private boolean enEjecucion;
    public ProyectoProductivo(String nombre, String descripcion,
                              double superficie, String necesidades,
                              int empleabilidad, String materiaPrima) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
    }
    public void actualizarEstado(){

    }
    public boolean estaEnEjecucion(){
        if (!enEjecucion){
            return false;
        }
        return true;
    }
}
