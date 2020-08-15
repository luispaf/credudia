package com.serasaapp.service;

import java.util.List;
import java.util.Optional;

import com.serasaapp.domain.Usuario;
import com.serasaapp.service.exceptions.UsuarioExceptions;

public interface UsuarioService   extends BasicService<Usuario, Long>  {

	List<Usuario> buscarFiltro(Usuario usuario) throws UsuarioExceptions;
	List<Usuario> todos() throws UsuarioExceptions;
	Optional<Usuario> buscarPorId(Long codigo) throws UsuarioExceptions;
}
