package com.serasaapp.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasaapp.domain.Perfil;
import com.serasaapp.repository.PerfilRepository;
import com.serasaapp.service.exceptions.PerfilExceptions;
import com.serasaapp.specification.OperationsCriteria;
import com.serasaapp.specification.SpecificationsBuilder;

@Component
public class PerfilRepositoryImpl {
	
	@Autowired
	PerfilRepository perfilRepository;
	
	public List<Perfil> buscarPerfilFiltro(Perfil perfil) throws PerfilExceptions {
		 SpecificationsBuilder<Perfil> specificationBuilder = new SpecificationsBuilder<>();
			
		 if (perfil != null) {
			 if (perfil.getCodigo() != null) {
				 specificationBuilder.with("codigo",  OperationsCriteria.EQ, perfil.getCodigo());				 
			 }			 
			 if (perfil.getDescricao() != null && perfil.getDescricao().length() > 0) {
				 specificationBuilder.with("descricao",  OperationsCriteria.LK, perfil.getDescricao());			
			 }	 
			 return perfilRepository.findAll(specificationBuilder.build());				 
		 }
		 return null;
	}

}
