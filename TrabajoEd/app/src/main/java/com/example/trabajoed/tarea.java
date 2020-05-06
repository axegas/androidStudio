package com.example.trabajoed;

public class tarea {
    private int id;
    private String descripcion;
    private String fechaEntrega;
    private String asignatura;
    private String hecha;

    public tarea(int id, String asignatura, String descripcion, String fechaEntrega, String hecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.asignatura = asignatura;
        this.hecha = hecha;
    }

    public void realizar(){
        hecha = "si";
    }
    public void noRealizar() { hecha = "no"; }

    public int getId(){
        return id;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getEstado(){ return hecha; }
    public String getFechaEntrega(){
        return fechaEntrega;
    }
    public String getAsignatura(){
        return asignatura;
    }
}
