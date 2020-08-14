package com.serasaapp.serasaapp.specification;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 5597735669159204486L;

	private String key;
	private String operation;
	private Serializable  value;
	private boolean orPredicate;

	public SearchCriteria(String key, String operation, Serializable  value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Serializable  value) {
		this.value = value;
	}

	public boolean isOrPredicate() {

		return orPredicate;
	}

	public void setOrPredicate(boolean orPredicate) {
		this.orPredicate = orPredicate;
	}

	@Override
	public String toString() {
		return "SearchCriteria [key=" + key + ", operation=" + operation + ", value=" + value + ", orPredicate="
				+ orPredicate + "]";
	}
}
