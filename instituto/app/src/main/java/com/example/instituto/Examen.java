package com.example.instituto;



public class Examen {

	private Alumno alumno;
	private Asignatura asignatura;
	private double nota;
	private String calificacion="SUSPENDIDO";
	private static int id= 0;
	public int idex;
	
	public Examen(Alumno alumno, Asignatura asignatura, double nota) {
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.nota = nota;
		if(nota>=5) {
			calificacion="APROBADO";
		}		
		id++;
		idex = id;
	}
	//getters
	public Alumno getAlumno() {
		return alumno;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public double getNota() {
		return nota;
	}
	public String getCalificacion() {
		return calificacion;
	}
	//toString
	public String toString() {
		return "- " + asignatura.getNombre() + ", nota: " + nota + ", calificacion: "+ calificacion + "\n";
	}	


}
