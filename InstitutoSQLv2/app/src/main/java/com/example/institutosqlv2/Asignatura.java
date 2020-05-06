package com.example.institutosqlv2;

public class Asignatura {

	private String nombre;
	private String libro;
	private Profesor profesor;
	private String obligatoria = "NO";

	public Asignatura(String nombre, String libro, Profesor profesor) {
		this.nombre = nombre;
		this.libro = libro;
		this.profesor = profesor;
		if(nombre.equals("Programacion")) {
			obligatoria = "Si";
		}
	}

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
	
	
}
