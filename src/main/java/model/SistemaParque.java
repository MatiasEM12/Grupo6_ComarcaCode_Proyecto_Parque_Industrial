package model;

import java.util.List;

public class SistemaParque {
    private List<Empresa> empresas;
    private List<ProyectoProductivo> proyectos;
    private List<Lote> lotes;

    public List<ProyectoProductivo> obtenerProyectosEnEjecucion() {
        return proyectos.stream()
                .filter(p -> p.estaEnEjecucion())
                .toList();
    }

    public List<Empresa> obtenerEmpresas() {
        return empresas;
    }

    public int nivelActividadIndustrial() {
        return (int) proyectos.stream()
                .filter(p -> p.estaEnEjecucion())
                .count();
    }
}