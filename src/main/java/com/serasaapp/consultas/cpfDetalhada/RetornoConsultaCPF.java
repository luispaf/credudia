package com.serasaapp.consultas.cpfDetalhada;

import java.io.Serializable;
import java.util.List;

import com.serasaapp.consultas.genericas.Email;
import com.serasaapp.consultas.genericas.Endereco;
import com.serasaapp.consultas.genericas.Telefone;

public class RetornoConsultaCPF implements Serializable{

	private static final long serialVersionUID = 6768918744721894853L;
	
	private String cpf;
	private String nome;
	private String nascimento;
	private Long idade;
	private String sexo;
	private String nomedamae;
	private String signo;
	private List<Email> email;
	private List<Endereco> enderecos;
	private List<Telefone> telefones;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public Long getIdade() {
		return idade;
	}
	public void setIdade(Long idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getNomedamae() {
		return nomedamae;
	}
	public void setNomedamae(String nomedamae) {
		this.nomedamae = nomedamae;
	}
	public String getSigno() {
		return signo;
	}
	public void setSigno(String signo) {
		this.signo = signo;
	}
	public List<Email> getEmail() {
		return email;
	}
	public void setEmail(List<Email> email) {
		this.email = email;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
