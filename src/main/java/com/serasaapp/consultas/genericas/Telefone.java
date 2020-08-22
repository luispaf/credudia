package com.serasaapp.consultas.genericas;

import java.io.Serializable;

public class Telefone implements Serializable{

	private static final long serialVersionUID = -4769679705118622282L;
	
	private String ddd;
	private String telefone;
	private String tipo;
	
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}
