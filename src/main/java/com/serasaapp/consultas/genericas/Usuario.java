package com.serasaapp.consultas.genericas;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 727942064401194019L;

	private String token;
	private String nome;
	private String email;
	private String cpf;
	private Cliente cliente;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
}
