package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.Usuario;

public interface UsuarioRepository  extends JpaRepository <Usuario, Long>, JpaSpecificationExecutor<Usuario>{

}
