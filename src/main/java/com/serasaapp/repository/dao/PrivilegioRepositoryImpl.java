package com.serasaapp.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasaapp.domain.Privilegio;
import com.serasaapp.repository.PrivilegioRepository;
import com.serasaapp.service.exceptions.PrivilegioExceptions;
import com.serasaapp.specification.OperationsCriteria;
import com.serasaapp.specification.SpecificationsBuilder;

@Component
public class PrivilegioRepositoryImpl {

	@Autowired
	PrivilegioRepository privilegioRepository;
	
	public List<Privilegio> buscarPrivilegioFiltro(Privilegio privilegio) throws PrivilegioExceptions {
		 SpecificationsBuilder<Privilegio> specificationBuilder = new SpecificationsBuilder<>();
			
		 if (privilegio != null) {
			 if (privilegio.getCodigo() != null) {
				 specificationBuilder.with("codigo",  OperationsCriteria.EQ, privilegio.getCodigo());				 
			 }			 
			 if (privilegio.getDescricao() != null && privilegio.getDescricao().length() > 0) {
				 specificationBuilder.with("descricao",  OperationsCriteria.LK, privilegio.getDescricao());			
			 }
			 if (privilegio.getTela() != null && privilegio.getTela().length() > 0) {
				 specificationBuilder.with("tela",  OperationsCriteria.LK, privilegio.getTela());	
			 }
			 if (privilegio.getObservacao() != null && privilegio.getObservacao().length() > 0) {
				 specificationBuilder.with("observacao",  OperationsCriteria.LK, privilegio.getObservacao());	
			 }		 
			 return privilegioRepository.findAll(specificationBuilder.build());				 
		 }
		 return null;
	}
}
