package com.serasaapp.consultas.placas;

import java.io.Serializable;

public class RetornoPrincipalPLACAS  implements Serializable{

	private static final long serialVersionUID = -8542353501562983277L;

	private String message;
	private ConsultaPLACA consulta;
	private Long result;
	
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
	public ConsultaPLACA getConsulta() {
		return consulta;
	}
	public void setConsulta(ConsultaPLACA consulta) {
		this.consulta = consulta;
	}
	
}
