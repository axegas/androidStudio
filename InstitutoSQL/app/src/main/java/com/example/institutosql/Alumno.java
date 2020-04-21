package com.example.institutosql;

import java.util.ArrayList;
import java.util.Iterator;

public class Alumno extends Persona{
	
	//atributos
	private String curso;
	private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
	private double notaMedia = 0;
	//private ArrayList<Examen> examenes = new ArrayList<Examen>();
	
	//constructor
	public Alumno(String dni,String nombre,int edad,String curso) {
		super(dni,nombre,edad);
		this.curso = curso;
	}
	//geters
	public String getCurso() {
		return curso;
	}
	public double getNotaMedia() {
		return notaMedia;
	}
	public ArrayList<Asignatura> getAsignaturas(){ return asignaturas;	}

	//metodos
	public void matricular(Asignatura as) {
		asignaturas.add(as);
	}

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

	/*
	public String verAlumno() {
		String str = "Asignaturas:\n";
		Iterator<Asignatura> iter = asignaturas.iterator();
		while(iter.hasNext()) {
			str += "\t" + iter.next().toString();
		}
		str += "Examenes:\n";
		Iterator<Examen> iter2 = examenes.iterator();
		while(iter2.hasNext()) {
			str += "\t" + iter2.next().toString();
		}
		return this.toString() + str + "Nota media del curso: " + this.notaMedia;
	}*/



	
	/*public void examinar(Examen e) {
		notaMedia=0;
		examenes.add(e);
		Iterator<Examen> iter = examenes.iterator();
		while(iter.hasNext()) {
			notaMedia+=iter.next().getNota();
		}
		notaMedia=notaMedia/examenes.size();		
	}*/




}
