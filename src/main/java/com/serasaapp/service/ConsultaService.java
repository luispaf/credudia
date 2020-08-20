package com.serasaapp.service;

import com.serasaapp.consultas.cpfDetalhada.RetornoPrincipalCPF;
import com.serasaapp.consultas.placas.RetornoPrincipalPLACAS;

public interface ConsultaService {

	public RetornoPrincipalCPF consultarCPF(String cpf, String uf) throws Exception;
	public boolean isCPF(String CPF);
	public RetornoPrincipalPLACAS consultarPLACA(String placa, String uf) throws Exception;
}
