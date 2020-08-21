package com.serasaapp.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasaapp.domain.Usuario;
import com.serasaapp.repository.UsuarioRepository;
import com.serasaapp.service.exceptions.UsuarioExceptions;
import com.serasaapp.specification.OperationsCriteria;
import com.serasaapp.specification.SpecificationsBuilder;

@Component
public class UsuarioRepositoryImpl {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscarFiltro(Usuario usuario) throws UsuarioExceptions {
		 SpecificationsBuilder<Usuario> specificationBuilder = new SpecificationsBuilder<>();
			
		 if (usuario != null) {
			 if (usuario.getCodigo() != null) {
				 specificationBuilder.with("codigo",  OperationsCriteria.EQ, usuario.getCodigo());				 
			 }
			 if (usuario.getNome() != null && usuario.getNome().length() > 0) {
				 specificationBuilder.with("nome",  OperationsCriteria.LK, usuario.getNome());			 
			 }
			 if (usuario.getLogin() != null && usuario.getLogin().length() > 0) {
				 specificationBuilder.with("login",  OperationsCriteria.LK, usuario.getLogin());			 
			 }
			 if (usuario.getCodigoPerfil() != null) {
				 specificationBuilder.with("codigoPerfil",  OperationsCriteria.EQ, usuario.getCodigoPerfil());				 
			 }
			 if (usuario.getStatus() != null && usuario.getStatus().length() > 0) {
				 specificationBuilder.with("status",  OperationsCriteria.LK, usuario.getStatus());				 
			 }			 
			 return usuarioRepository.findAll(specificationBuilder.build());				 
		 }
		 return null;
	}
}
