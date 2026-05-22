package model;

public class ProyectoProductivo {
    private int idProyecto;
    private String nombre;
    private String descripcion;
    private double superficie;
    private String necesidades;
    private int empleabilidad;
    private String materiaPrima;
    private boolean enEjecucion;
    private Empresa empresa;
    public ProyectoProductivo(int idProyecto, String nombre, String descripcion,
                              double superficie, String necesidades,
                              int empleabilidad, String materiaPrima,
                              boolean enEjecucion, Empresa empresa) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
        this.enEjecucion = enEjecucion;
        this.empresa = empresa;
    }
    public ProyectoProductivo(String nombre,
                              String descripcion,
                              double superficie,
                              String necesidades,
                              int empleabilidad,
                              String materiaPrima,
                              Empresa empresa) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.superficie = superficie;
        this.necesidades = necesidades;
        this.empleabilidad = empleabilidad;
        this.materiaPrima = materiaPrima;
        this.empresa = empresa;
        this.enEjecucion = false;
    }
    public void actualizarEstado(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getSuperficie() {
        return superficie;
    }

    public String getNecesidades() {
        return necesidades;
    }

    public int getEmpleabilidad() {
        return empleabilidad;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public boolean isEnEjecucion() {
        return enEjecucion;
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

    public int idProyecto() {
        return idProyecto;
    }
}
