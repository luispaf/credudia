package com.serasaapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable{

	private static final long serialVersionUID = -8817658850447665509L;

	@Id
	@GeneratedValue(generator = "autoinc")
	@Column(name = "codempresa")
	private Long codigo;
	
	@Column(name = "nomempresa")
	private String nome;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "codempassociada")
	private Long codigoEmpresaAssociado;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Long getCodigoEmpresaAssociado() {
		return codigoEmpresaAssociado;
	}
	public void setCodigoEmpresaAssociado(Long codigoEmpresaAssociado) {
		this.codigoEmpresaAssociado = codigoEmpresaAssociado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
