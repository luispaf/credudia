package com.serasaapp.consultas.placas;

import java.io.Serializable;

import com.serasaapp.consultas.cpfDetalhada.Consulta;

public class RetornoPrincipalPLACAS  implements Serializable{

	private static final long serialVersionUID = -8542353501562983277L;

	private String message;
	private Long result;
	private Consulta consulta;
	
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
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}	
}
