package com.serasaapp.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasaapp.domain.Empresa;
import com.serasaapp.repository.EmpresaRepository;
import com.serasaapp.service.exceptions.EmpresaExceptions;
import com.serasaapp.specification.OperationsCriteria;
import com.serasaapp.specification.SpecificationsBuilder;

@Component
public class EmpresaRepositoryImpl {

	@Autowired
	EmpresaRepository empresaRepository;
	
	public List<Empresa> buscarEmpresaFiltro(Empresa empresa) throws EmpresaExceptions {
		 SpecificationsBuilder<Empresa> specificationBuilder = new SpecificationsBuilder<>();
			
		 if (empresa != null) {
			 if (empresa.getCodigo() != null) {
				 specificationBuilder.with("codigo",  OperationsCriteria.EQ, empresa.getCodigo());				 
			 }			 
			 if (empresa.getNome() != null && empresa.getNome().length() > 0) {
				 specificationBuilder.with("nome",  OperationsCriteria.LK, empresa.getNome());			
			 }
			 if (empresa.getCnpj() != null && empresa.getCnpj().length() > 0) {
				 specificationBuilder.with("cnpj",  OperationsCriteria.EQ, empresa.getCnpj());				 
			 }
			 if (empresa.getTelefone() != null && empresa.getTelefone().length() > 0) {
				 specificationBuilder.with("telefone",  OperationsCriteria.EQ, empresa.getTelefone());				 
			 }
			 if (empresa.getCodigoEmpresaAssociado() != null) {
				 specificationBuilder.with("codigoEmpresaAssociado",  OperationsCriteria.EQ, empresa.getCodigoEmpresaAssociado());				 
			 }	
			 return empresaRepository.findAll(specificationBuilder.build());				 
		 }
		 return null;
	}
}
