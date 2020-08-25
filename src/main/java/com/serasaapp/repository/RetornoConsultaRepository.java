package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.RetornoConsulta;

public interface RetornoConsultaRepository  extends JpaRepository <RetornoConsulta, Long>, JpaSpecificationExecutor<RetornoConsulta> {

}
