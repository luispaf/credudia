package com.serasaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.serasaapp.domain.RetornoConsulta;

public interface RetornoConsultaRepository  extends JpaRepository <RetornoConsulta, Long>, JpaSpecificationExecutor<RetornoConsulta> {

	@Query(value = " SELECT * FROM credudia.retornoconsulta "
			+ "  where codretorno in(SELECT max(codretorno) FROM credudia.retornoconsulta "
			+ "       					where valor = ?1 "
			+ "        				and uf = ?2 "
			+ "        				and codtipconsulta = ?3 "
			+ "        				and codusuario = ?4 "
			+ "        				and DATE(SYSDATE()) = datcad) ", nativeQuery = true)
	RetornoConsulta buscarConsultaSalvaPorData(String valor, String uf, Long codigoTipoConsulta, Long codigoUsuarioEmpresa);
}
