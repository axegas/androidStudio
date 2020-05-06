package com.example.institutosqlv2;

public class Examen {

	private Alumno alumno;
	private Asignatura asignatura;
	private double nota;
	private String fecha;
	private String calificacion="SUSPENDIDO";
	private int id;

	public Examen(int id, Asignatura asignatura, Alumno alumno, String fecha, double nota) {
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.nota = nota;
		this.id = id;
		this.fecha = fecha;

		if(nota>=5) {
			calificacion="APROBADO";
		}		

	}

	public Alumno getAlumno() {
		return alumno;
	}
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public double getNota() {
		return nota;
	}
	public String getFecha() {
		return fecha;
	}
	public String getCalificacion() {
		return calificacion;
	}

}
