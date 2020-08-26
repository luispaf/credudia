package com.serasaapp.service;

import java.util.List;
import java.util.Optional;

import com.serasaapp.domain.Empresa;
import com.serasaapp.service.exceptions.EmpresaExceptions;

public interface EmpresaService  extends BasicService<Empresa, Long>  {

	List<Empresa> buscarEmpresaFiltro(Empresa empresa) throws EmpresaExceptions;
	List<Empresa> todos() throws EmpresaExceptions;
	Optional<Empresa> buscarPorId(Long codigo) throws EmpresaExceptions;
}
