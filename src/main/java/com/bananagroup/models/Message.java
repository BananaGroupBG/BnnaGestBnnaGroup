package com.bananagroup.models;

public class Message {
	private String cuerpo;
	
	public Message(){}
	
	public Message(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	
}