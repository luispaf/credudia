package com.serasaapp.consultas.placas;

import java.io.Serializable;

public class RetornoPLACA implements Serializable{

	private static final long serialVersionUID = -3067333398540404024L;
	
	public RetornoPLACA() {}
	
	private String uf;

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
