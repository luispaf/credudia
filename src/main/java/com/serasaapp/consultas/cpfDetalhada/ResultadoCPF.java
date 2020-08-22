package com.serasaapp.consultas.cpfDetalhada;

import java.io.Serializable;

public class ResultadoCPF implements Serializable{

	private static final long serialVersionUID = -4107460888162470289L;
	private Long id;
	private String title;
	private String token;
	private String ctime;
	private RetornoConsultaCPF retorno;
					
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}	
	public RetornoConsultaCPF getRetorno() {
		return retorno;
	}
	public void setRetorno(RetornoConsultaCPF retorno) {
		this.retorno = retorno;
	}
	
	@Override
	public String toString() {
		return "Resultado [title=" + title + "]";
	}	
	
}
