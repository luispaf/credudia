package com.serasaapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "retornoconsulta")
public class RetornoConsulta implements Serializable{

	private static final long serialVersionUID = -3810261282846391421L;
	
	@Id
	@GeneratedValue(generator = "autoinc")
	@Column(name = "codretorno")
	private Long codigo;
	
	@Column(name = "codtipconsulta")
	private Long codigoTipoConsulta;
	
	@ManyToOne
	@JoinColumn(name = "codtipconsulta", insertable = false, updatable = false)
	private TipoConsulta tipoConsulta;
	
	@Column(name = "codusuario")
	private Long codigoUsuario;
	
	@ManyToOne
	@JoinColumn(name = "codusuario", insertable = false, updatable = false)
	private Usuario usuario;
	
	@Column(name = "json")
	private String json;
	
	@Column(name = "datcad")
	private Date dataCadastro;
	
	@Column(name = "valor")
	private String valor;
	
	@Column(name = "uf")
	private String uf;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}	
	
	public Long getCodigoTipoConsulta() {
		return codigoTipoConsulta;
	}
	public void setCodigoTipoConsulta(Long codigoTipoConsulta) {
		this.codigoTipoConsulta = codigoTipoConsulta;
	}
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}		
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
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
		RetornoConsulta other = (RetornoConsulta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
