package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.serasaapp.domain.TipoConsulta;

public interface TipoConsultaRepository  extends JpaRepository <TipoConsulta, Long>, JpaSpecificationExecutor<TipoConsulta> {

}
