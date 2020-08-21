package com.serasaapp.enums;

public enum StatusUsuario {

	ATIVO("ATIVO"), 
	BLOQUEADO("BLOQUEADO"), 
	INATIVO("INATIVO");

	private String descricao;

	private StatusUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}
}
