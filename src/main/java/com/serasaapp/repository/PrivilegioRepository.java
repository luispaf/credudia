package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.Privilegio;

public interface PrivilegioRepository extends JpaRepository <Privilegio, Long>, JpaSpecificationExecutor<Privilegio> {
	  

	
}
