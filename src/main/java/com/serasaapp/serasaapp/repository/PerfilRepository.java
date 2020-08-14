package com.serasaapp.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.serasaapp.domain.Perfil;

public interface PerfilRepository extends JpaRepository <Perfil, Long>, JpaSpecificationExecutor<Perfil> {

}
