package com.example.pruebatab;

import java.io.Serializable;

public class tarea implements Serializable {
    private int id;
    private String descripcion;
    private String fechaEntrega;
    private String asignatura;
    private String hecha;
    private String titulo;

    public tarea(int id, String asignatura, String titulo, String descripcion, String fechaEntrega, String hecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.asignatura = asignatura;
        this.hecha = hecha;
        this.titulo = titulo;
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
    public String getTitulo(){ return titulo; }
}
