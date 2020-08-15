package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.PerfilPrivilegio;

public interface PerfilPrivilegioRepository  extends JpaRepository <PerfilPrivilegio, Long>, JpaSpecificationExecutor<PerfilPrivilegio> {

}
