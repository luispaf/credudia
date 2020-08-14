package com.serasaapp.serasaapp.service;

import java.util.List;
import java.util.Optional;

import com.serasaapp.serasaapp.domain.Perfil;
import com.serasaapp.serasaapp.service.exceptions.PerfilExceptions;

public interface PerfilService extends BasicService<Perfil, Long>  {

	List<Perfil> buscarPrivilegioFiltro(Perfil perfil) throws PerfilExceptions;
	List<Perfil> todos() throws PerfilExceptions;
	Optional<Perfil> buscarPorId(Long codigo) throws PerfilExceptions;
}
