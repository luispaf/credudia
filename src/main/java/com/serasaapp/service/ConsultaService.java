package com.serasaapp.service;

public interface ConsultaService {

	public String consultarCPF(String cpf, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String consultarPLACA(String placa, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String consultarNome(String nome, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String buscarLeilaoCompletaSCORE(String placa, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String consultarACoesJudiciaisCPF(String cpf, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String consultarACoesJudiciaisCNPJ(String cnpj, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception; 
}
