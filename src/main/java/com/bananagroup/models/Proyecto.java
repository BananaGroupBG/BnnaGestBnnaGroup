package com.bananagroup.models;

import java.util.Date;

public class Proyecto {
	// Definicion de los atributos de proyecto generico
	private int pid;
	private String titulo;
	private String descripcion;
	private Date fechaI;
	private Usuario responsable;
	private boolean activo;
	private Tarea listaTareas;
	// private Tarea[] tareasPy; //define un Array de las tareas del proyecto.
	// Cada tarea se define con su claseModelo Tarea.java PERO NO LA PRESENTAMOS
	// EN ESTA LISTA

	// public Proyecto(int idProyecto, String tituloPy, String descripcionPy,
	// Date fechaInicioPy, String responsablePy, boolean activoPy, Tarea[]
	// tareas) {

	public Proyecto(int pid, String titulo, String descripcion, Date fechaI, Usuario responsable, boolean activo,
			Tarea listaTareas) {
		// Inicializacion de los atributos de Proyecto
		this.pid = pid;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaI = fechaI;
		this.responsable = responsable;
		this.setActivo(activo);
		this.listaTareas = listaTareas;
	}

	public Proyecto() {}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaI() {
		return fechaI;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Tarea getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(Tarea listaTareas) {
		this.listaTareas = listaTareas;
	}

}