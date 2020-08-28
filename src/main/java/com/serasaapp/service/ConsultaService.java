package com.serasaapp.service;

public interface ConsultaService {

	public String infobuscaDetalhada(String cpf, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String veicularTOP(String placa, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String infobusca(String nome, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String leilaoCompletaMaisScore(String placa, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
	public String acoesJudiciais(String cpf, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception;
}
