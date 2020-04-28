package com.example.institutosqlv2;

public class Profesor extends Persona {

	private String categoria;
	public Profesor(String dni,String nombre,int edad, String categoria) {
		super(dni,nombre,edad);
		this.categoria = categoria;
	}

	public String getCategoria(){
		return  categoria;
	}

}
