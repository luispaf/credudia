package com.serasaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasaapp.domain.Empresa;
import com.serasaapp.repository.EmpresaRepository;
import com.serasaapp.repository.dao.EmpresaRepositoryImpl;
import com.serasaapp.service.EmpresaService;
import com.serasaapp.service.exceptions.EmpresaExceptions;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	EmpresaRepositoryImpl empresaRepositoryImpl;
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Override
	public Empresa salvar(Empresa object) throws ServiceException {
		try {
			return empresaRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao salvar Empresa: " + e.getMessage());
		}
	}

	@Override
	public Empresa atualizar(Empresa object) throws ServiceException {
		try {
			return empresaRepository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao atualizar Empresa: " + e.getMessage());
		}
	}

	@Override
	public Empresa excluir(Empresa object) throws ServiceException {
		try {
			empresaRepository.delete(object);
			return object;
		} catch (Exception e) {
			throw new ServiceException("Falha ao excluir Empresa: " + e.getMessage());
		}
	}

	@Override
	public List<Empresa> buscarEmpresaFiltro(Empresa empresa) throws EmpresaExceptions {
		return empresaRepositoryImpl.buscarEmpresaFiltro(empresa);
	}

	@Override
	public List<Empresa> todos() throws EmpresaExceptions {
		return empresaRepository.findAll();
	}

	@Override
	public Optional<Empresa> buscarPorId(Long codigo) throws EmpresaExceptions {
		return empresaRepository.findById(codigo);
	}

}
