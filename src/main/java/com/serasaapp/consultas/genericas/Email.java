package com.serasaapp.consultas.genericas;

import java.io.Serializable;

public class Email implements Serializable{

	private static final long serialVersionUID = 7394111135214171988L;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
