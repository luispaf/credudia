package com.serasaapp.serasaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasaapp.serasaapp.domain.Privilegio;
import com.serasaapp.serasaapp.repository.PrivilegioRepository;
import com.serasaapp.serasaapp.repository.dao.PrivilegioRepositoryImpl;
import com.serasaapp.serasaapp.service.PrivilegioService;
import com.serasaapp.serasaapp.service.exceptions.PrivilegioExceptions;

@Service
public class PrivilegioServiceImpl implements PrivilegioService{

	@Autowired
	PrivilegioRepositoryImpl privilegioRepositoryImpl;
	@Autowired
	PrivilegioRepository privilegioRepository;
	
	@Override
	public Privilegio salvar(Privilegio object) throws ServiceException {
		try {
			return privilegioRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao salvar Privilegio: " + e.getMessage());
		}
	}

	@Override
	public Privilegio atualizar(Privilegio object) throws ServiceException {
		try {
			return privilegioRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao atualizar Privilegio: " + e.getMessage());
		}
	}

	@Override
	public Privilegio excluir(Privilegio object) throws ServiceException {
		try {
			privilegioRepository.delete(object);
			return object;
		} catch (Exception e) {
			throw new ServiceException("Falha ao excluir Privilegio: " + e.getMessage());
		}
	}

	@Override
	public List<Privilegio> buscarPrivilegioFiltro(Privilegio privilegio) throws PrivilegioExceptions {
		return privilegioRepositoryImpl.buscarPrivilegioFiltro(privilegio);
	}

	@Override
	public List<Privilegio> todos() throws PrivilegioExceptions {
		return privilegioRepository.findAll();
	}

	@Override
	public Optional<Privilegio> buscarPorId(Long codigo) throws PrivilegioExceptions {
		return privilegioRepository.findById(codigo);
	}

}
