package com.serasaapp.consultas.placas;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResultadoPLACA  implements Serializable{

	private static final long serialVersionUID = -6501486660922000774L;
	private Long id;
	private String title;
	private String token;
	private String ctime;
	@JsonIgnore
	private RetornoPLACA retornoPLACA;	
	private Object retorno;
	
	public Object getRetorno() {
		return retorno;
	}
	public void setRetorno(Object retorno) {
		this.retorno = retorno;
	}
	
	public RetornoPLACA getRetornoPLACA() {
		if(retorno instanceof String) {
			return retornoPLACA;
		}else if(retorno instanceof HashMap ) {
			retornoPLACA = new RetornoPLACA();
			HashMap<String, Object> retornoMap = (HashMap) retorno;
			retornoPLACA.setUf((String)retornoMap.get("uf"));
		}
		return retornoPLACA;
	}
	public void setRetornoPLACA(RetornoPLACA retornoPLACA) {
		this.retornoPLACA = retornoPLACA;
	}
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
	
	
}
