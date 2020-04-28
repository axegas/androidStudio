package com.example.institutosql;

public class Curso {
    private String nombre;
    private String centro;

    public Curso(String nombre, String centro) {
        this.nombre = nombre;
        this.centro = centro;
    }

    public String getNombre(){ return nombre;}
    public String getCentro(){ return centro;}



}
