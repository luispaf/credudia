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
@Table(name = "perfilprivilegio")
public class PerfilPrivilegio implements Serializable{

	private static final long serialVersionUID = -2677526188674492959L;

	@Id
	@GeneratedValue(generator = "autoinc")
	@Column(name = "codperprivilegio")
	private Long codigo;	
	
	@Column(name = "codperfil")
	private Long codigoPerfil;
	
	@Column(name = "codprivilegio")
	private Long codigoPrivilegio;

	@Column(name = "datcad")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name = "codperfil", insertable = false, updatable = false)
	private Perfil perfil;	
	
	@ManyToOne
	@JoinColumn(name = "codprivilegio", insertable = false, updatable = false)
	private Privilegio privilegio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public Long getCodigoPrivilegio() {
		return codigoPrivilegio;
	}

	public void setCodigoPrivilegio(Long codigoPrivilegio) {
		this.codigoPrivilegio = codigoPrivilegio;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Privilegio getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
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
		PerfilPrivilegio other = (PerfilPrivilegio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
}
