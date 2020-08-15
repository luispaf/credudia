package com.serasaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasaapp.domain.Usuario;
import com.serasaapp.repository.UsuarioRepository;
import com.serasaapp.repository.dao.UsuarioRepositoryImpl;
import com.serasaapp.service.UsuarioService;
import com.serasaapp.service.exceptions.UsuarioExceptions;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository repository;
	@Autowired 
	UsuarioRepositoryImpl repositoryImpl;
	@Override
	public Usuario salvar(Usuario object) throws ServiceException {
		try {
			return repository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao salvar Usuario: " + e.getMessage());
		}
	}
	@Override
	public Usuario atualizar(Usuario object) throws ServiceException {
		try {
			return repository.save(object);			
		} catch (Exception e) {
			throw new ServiceException("Falha ao atualizar Usuario: " + e.getMessage());
		}
	}
	@Override
	public Usuario excluir(Usuario object) throws ServiceException {
		try {
			repository.delete(object);
			return object;
		} catch (Exception e) {
			throw new ServiceException("Falha ao excluir Usuario: " + e.getMessage());
		}
	}
	@Override
	public List<Usuario> buscarFiltro(Usuario usuario) throws UsuarioExceptions {
		return repositoryImpl.buscarFiltro(usuario);
	}
	@Override
	public List<Usuario> todos() throws UsuarioExceptions {
		return repository.findAll();
	}
	@Override
	public Optional<Usuario> buscarPorId(Long codigo) throws UsuarioExceptions {
		return repository.findById(codigo);
	}
	
}
