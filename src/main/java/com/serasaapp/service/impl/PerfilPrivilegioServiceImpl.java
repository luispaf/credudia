package com.serasaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasaapp.domain.PerfilPrivilegio;
import com.serasaapp.repository.PerfilPrivilegioRepository;
import com.serasaapp.repository.dao.PerfilPrivilegioRepositoryImpl;
import com.serasaapp.service.PerfilPrivilegioService;
import com.serasaapp.service.exceptions.PerfilPrivilegioExceptions;

@Service
public class PerfilPrivilegioServiceImpl implements PerfilPrivilegioService{
	
	@Autowired
	PerfilPrivilegioRepository perfilPrivilegioRepository;
	@Autowired 
	PerfilPrivilegioRepositoryImpl perfilPrivilegioRepositoryImpl;

	@Override
	public PerfilPrivilegio salvar(PerfilPrivilegio object) throws ServiceException {
		try {
			return perfilPrivilegioRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao salvar Perfil: " + e.getMessage());
		}
	}

	@Override
	public PerfilPrivilegio atualizar(PerfilPrivilegio object) throws ServiceException {
		try {
			return perfilPrivilegioRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao atualizar Perfil: " + e.getMessage());
		}
	}

	@Override
	public PerfilPrivilegio excluir(PerfilPrivilegio object) throws ServiceException {
		try {
			perfilPrivilegioRepository.delete(object);
			return object;
		} catch (Exception e) {
			throw new ServiceException("Falha ao excluir Perfil: " + e.getMessage());
		}
	}

	@Override
	public List<PerfilPrivilegio> buscarFiltro(PerfilPrivilegio perfilPrivilegio) throws PerfilPrivilegioExceptions {
		return perfilPrivilegioRepositoryImpl.buscarFiltro(perfilPrivilegio);
	}

	@Override
	public List<PerfilPrivilegio> todos() throws PerfilPrivilegioExceptions {
		return perfilPrivilegioRepository.findAll();
	}

	@Override
	public Optional<PerfilPrivilegio> buscarPorId(Long codigo) throws PerfilPrivilegioExceptions {
		return perfilPrivilegioRepository.findById(codigo);
	}

}
