package com.serasaapp.service;

import java.util.List;
import java.util.Optional;

import com.serasaapp.domain.Privilegio;
import com.serasaapp.service.exceptions.PrivilegioExceptions;

public interface PrivilegioService extends BasicService<Privilegio, Long> {
	
	List<Privilegio> buscarPrivilegioFiltro(Privilegio privilegio) throws PrivilegioExceptions;
	List<Privilegio> todos() throws PrivilegioExceptions;
	Optional<Privilegio> buscarPorId(Long codigo) throws PrivilegioExceptions;

}
