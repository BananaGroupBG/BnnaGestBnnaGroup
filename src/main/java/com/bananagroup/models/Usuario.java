package com.bananagroup.models;

public class Usuario {
	private int uid;
	private String nombre;
	private String email;
	private String password;

	public Usuario(int uid, String nombre, String email, String password) {
		this.uid = uid;
		this.nombre = nombre;
		this.setEmail(email);
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
