package com.example.institutosqlv2;

import java.util.ArrayList;
import java.util.Iterator;

public class Alumno extends Persona{

	private String curso;
	private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
	private double notaMedia;

	public Alumno(String dni,String nombre,int edad,String curso) {
		super(dni,nombre,edad);
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}
	public double getNotaMedia() {
		return notaMedia;
	}
	public ArrayList<Asignatura> getAsignaturas(){ return asignaturas;	}

	public void setNotaMedia(double nota){ notaMedia = nota;}
	public void setAsignaturas(ArrayList<Asignatura> asignaturas){ this.asignaturas = asignaturas;	}


	public boolean tieneAsig(Asignatura a) {
		boolean existe = false;
		Iterator<Asignatura> iter = asignaturas.iterator();
		while(iter.hasNext()&&!existe) {
			if(a.equals(iter.next())) {
				existe = true;
			}
		}
		return existe;
	}





	





}
