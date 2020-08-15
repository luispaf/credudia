package com.serasaapp.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasaapp.domain.Perfil;
import com.serasaapp.domain.PerfilPrivilegio;
import com.serasaapp.repository.PerfilPrivilegioRepository;
import com.serasaapp.repository.PerfilRepository;
import com.serasaapp.service.exceptions.PerfilExceptions;
import com.serasaapp.service.exceptions.PerfilPrivilegioExceptions;
import com.serasaapp.specification.OperationsCriteria;
import com.serasaapp.specification.SpecificationsBuilder;

@Component
public class PerfilPrivilegioRepositoryImpl {

	@Autowired
	PerfilPrivilegioRepository perfilPrivilegioRepository;
	
	public List<PerfilPrivilegio> buscarFiltro(PerfilPrivilegio perfilPrivilegio) throws PerfilPrivilegioExceptions {
		 SpecificationsBuilder<PerfilPrivilegio> specificationBuilder = new SpecificationsBuilder<>();
			
		 if (perfilPrivilegio != null) {
			 if (perfilPrivilegio.getCodigo() != null) {
				 specificationBuilder.with("codigo",  OperationsCriteria.EQ, perfilPrivilegio.getCodigo());				 
			 }
			 if (perfilPrivilegio.getCodigoPerfil() != null) {
				 specificationBuilder.with("codigoPerfil",  OperationsCriteria.EQ, perfilPrivilegio.getCodigoPerfil());				 
			 }	
			 if (perfilPrivilegio.getCodigoPrivilegio() != null) {
				 specificationBuilder.with("codigoPrivilegio",  OperationsCriteria.EQ, perfilPrivilegio.getCodigoPrivilegio());				 
			 } 
			 return perfilPrivilegioRepository.findAll(specificationBuilder.build());				 
		 }
		 return null;
	}
}
