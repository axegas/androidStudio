package com.example.proyinstituto;

import java.io.Serializable;

public class Persona implements Serializable {
    protected String nombre;
    protected String dni;
    protected int edad;

    public Persona(String nombre, String dni, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getDNI() { return dni; }
}
