package com.serasaapp.consultas.cpfDetalhada;

import java.io.Serializable;
import java.util.List;

import com.serasaapp.consultas.genericas.Usuario;

public class Consulta implements Serializable{

	private static final long serialVersionUID = 3205010030711458874L;

	private String token_grupoconsulta;
	private String token;
	private String parametro;
	private String parametro_valor;
	private String ctime;
	private String titulo;
	private Long status;
	private Usuario usuario;
	private List<ResultadoCPF> resultados;
	
	
	public List<ResultadoCPF> getResultados() {
		return resultados;
	}
	public void setResultados(List<ResultadoCPF> resultados) {
		this.resultados = resultados;
	}
	public String getToken_grupoconsulta() {
		return token_grupoconsulta;
	}
	public void setToken_grupoconsulta(String token_grupoconsulta) {
		this.token_grupoconsulta = token_grupoconsulta;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getParametro_valor() {
		return parametro_valor;
	}
	public void setParametro_valor(String parametro_valor) {
		this.parametro_valor = parametro_valor;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
