package com.serasaapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(generator = "autoinc")
	@Column(name = "codusuario")
	private Long codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "datcad")
	private Date dataCadastro;
	
	@Column(name = "codperfil")
	private Long codigoPerfil;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "datinibloqueio")
	private Date dataInicioBloqueio;
	
	@Column(name = "datfimbloqueio")
	private Date dataFimBloqueio;
	
	@OneToOne
	@JoinColumn(name = "codperfil", insertable = false, updatable = false)
	private Perfil perfil;
	

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String usuario) {
		this.login = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Date getDataInicioBloqueio() {
		return dataInicioBloqueio;
	}

	public void setDataInicioBloqueio(Date dataInicioBloqueio) {
		this.dataInicioBloqueio = dataInicioBloqueio;
	}

	public Date getDataFimBloqueio() {
		return dataFimBloqueio;
	}

	public void setDataFimBloqueio(Date dataFimBloqueio) {
		this.dataFimBloqueio = dataFimBloqueio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((codigoPerfil == null) ? 0 : codigoPerfil.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataFimBloqueio == null) ? 0 : dataFimBloqueio.hashCode());
		result = prime * result + ((dataInicioBloqueio == null) ? 0 : dataInicioBloqueio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPerfil == null) {
			if (other.codigoPerfil != null)
				return false;
		} else if (!codigoPerfil.equals(other.codigoPerfil))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataFimBloqueio == null) {
			if (other.dataFimBloqueio != null)
				return false;
		} else if (!dataFimBloqueio.equals(other.dataFimBloqueio))
			return false;
		if (dataInicioBloqueio == null) {
			if (other.dataInicioBloqueio != null)
				return false;
		} else if (!dataInicioBloqueio.equals(other.dataInicioBloqueio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}	
}
