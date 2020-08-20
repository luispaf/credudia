package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.Parametro;

public interface ParametroRepository  extends JpaRepository <Parametro, Long>, JpaSpecificationExecutor<Parametro>{

}
