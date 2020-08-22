package com.serasaapp.consultas.cpfDetalhada;

import java.io.Serializable;

public class RetornoPrincipalCPF implements Serializable{

	private static final long serialVersionUID = -606222464337374713L;
	private String message;
	private Long result;
	private ConsultaCPFDET consulta;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getResult() {
		return result;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	public ConsultaCPFDET getConsulta() {
		return consulta;
	}
	public void setConsulta(ConsultaCPFDET consulta) {
		this.consulta = consulta;
	}		
}
