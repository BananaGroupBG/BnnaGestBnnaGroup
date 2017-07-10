package com.bananagroup.models;

public class Tarea {
	// Definicion de los atributos de tarea generica
	private int tid;
	private String descripcion;
	private Usuario responsable;

	public Tarea(int tid, String descripcion, Usuario responsable) {
		// Inicializacion de los atributos de Tarea
		this.tid = tid;
		this.descripcion = descripcion;
		this.responsable = responsable;
	}

	public int getIdTarea() {
		return tid;
	}

	public void setIdTarea(int idTarea) {
		this.tid = idTarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

}