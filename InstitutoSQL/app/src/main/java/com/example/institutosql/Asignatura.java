package com.example.institutosql;


import java.io.Serializable;

public class Asignatura implements Serializable {
	//atributos
	private String nombre;
	private String libro;
	private Profesor profesor;
	private String obligatoria = "NO";
	
	//constructor
	public Asignatura(String nombre, String libro, Profesor profesor) {
		this.nombre = nombre;
		this.libro = libro;
		this.profesor = profesor;
		if(nombre.equals("Programacion")) {
			obligatoria = "Si";
		}
	}

	//getters
	public String getNombre() {
		return nombre;
	}
	public String getLibro() {
		return libro;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public String getObligatoria() {
		return obligatoria;
	}
	//toString
	public String toString() {
		return "- " + nombre + ", libro: " + libro + ", profesor: " + profesor.getNombre()
				+ ", obligatoria: " + obligatoria + "\n";
	}
	
	
	
}
