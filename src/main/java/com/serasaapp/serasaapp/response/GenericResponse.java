package com.serasaapp.serasaapp.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericResponse<T> {


	private Boolean status;
	private Optional<T> data;
	private List<String> errors;
	private int statusCode ;
	private String info;

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	public GenericResponse() {
		this.errors = new ArrayList<>();
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Optional<T> getData() {
		return data;
	}

	public void setData(Optional<T> data) {
		this.data = data;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
