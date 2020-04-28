package com.example.institutosql;

public class Examen {

	private Alumno alumno;
	private Asignatura asignatura;
	private double nota;
	private String fecha;
	private String calificacion="SUSPENDIDO";
	private int idex;

	public Examen(int id, Asignatura asignatura, Alumno alumno, String fecha, double nota) {
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.nota = nota;
		this.idex = id;
		this.fecha = fecha;

		if(nota>=5) {
			calificacion="APROBADO";
		}		

	}
	//getters
	public Alumno getAlumno() {
		return alumno;
	}
	public int getID() {
		return idex;
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
