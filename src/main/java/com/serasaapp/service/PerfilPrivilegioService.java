package com.serasaapp.service;

import java.util.List;
import java.util.Optional;

import com.serasaapp.domain.PerfilPrivilegio;
import com.serasaapp.service.exceptions.PerfilPrivilegioExceptions;

public interface PerfilPrivilegioService  extends BasicService<PerfilPrivilegio, Long>  {

	List<PerfilPrivilegio> buscarFiltro(PerfilPrivilegio perfilPrivilegio) throws PerfilPrivilegioExceptions;
	List<PerfilPrivilegio> todos() throws PerfilPrivilegioExceptions;
	Optional<PerfilPrivilegio> buscarPorId(Long codigo) throws PerfilPrivilegioExceptions;
}
