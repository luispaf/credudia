package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.Empresa;

public interface EmpresaRepository  extends JpaRepository <Empresa, Long>, JpaSpecificationExecutor<Empresa> {

}
