package com.serasaapp.serasaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasaapp.serasaapp.domain.Perfil;
import com.serasaapp.serasaapp.repository.PerfilRepository;
import com.serasaapp.serasaapp.repository.dao.PerfilRepositoryImpl;
import com.serasaapp.serasaapp.service.PerfilService;
import com.serasaapp.serasaapp.service.exceptions.PerfilExceptions;

@Service
public class PerfilServiceImpl implements PerfilService{

	@Autowired
	PerfilRepositoryImpl perfilRepositoryImpl;
	@Autowired
	PerfilRepository perfilRepository;
	@Override
	public Perfil salvar(Perfil object) throws ServiceException {
		try {
			return perfilRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao salvar Perfil: " + e.getMessage());
		}
	}
	@Override
	public Perfil atualizar(Perfil object) throws ServiceException {
		try {
			return perfilRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao atualizar Perfil: " + e.getMessage());
		}
	}
	@Override
	public Perfil excluir(Perfil object) throws ServiceException {
		try {
			perfilRepository.delete(object);
			return object;
		} catch (Exception e) {
			throw new ServiceException("Falha ao excluir Perfil: " + e.getMessage());
		}
	}
	@Override
	public List<Perfil> buscarPrivilegioFiltro(Perfil perfil) throws PerfilExceptions {
		return perfilRepositoryImpl.buscarPerfilFiltro(perfil);
	}
	@Override
	public List<Perfil> todos() throws PerfilExceptions {
		return perfilRepository.findAll();
	}
	@Override
	public Optional<Perfil> buscarPorId(Long codigo) throws PerfilExceptions {
		return perfilRepository.findById(codigo);
	}
	
}
