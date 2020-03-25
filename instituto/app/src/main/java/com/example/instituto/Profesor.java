package com.example.instituto;

public class Profesor {
	
	private String nombre;
	private int edad;
	private String DNI;
	private static int id= 0;
	public int idprof;
	
	public Profesor(String DNI,String nombre,int edad) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.edad = edad;
		id++;
		idprof = id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public String getDNI() {
		return DNI;
	}

	public String toString() {
		return "- " + nombre + ", edad: " + edad + ", DNI: " + DNI + "\n";		
	}
	
}
