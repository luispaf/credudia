package com.serasaapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.ConsultaService;
import com.serasaapp.util.Util;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*")
public class consultaController {
	
	@Autowired
	ConsultaService consultaService;
	
	@GetMapping("/buscarPorCPF/{cpf}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> buscarPorCPF(@PathVariable("cpf") String cpf, 
														   @PathVariable("uf") String uf, 
														   @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
														   @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {		
		
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((cpf == null || cpf.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			
			Boolean cpfValido = Util.isCPF(cpf);
			
			if (!cpfValido) {throw new Exception("CPF invalido: " + cpf);}
			
			String ret = consultaService.consultarCPF(cpf, uf, codigoTipoConsulta, codigoUsuarioEmpresa);			
		
			 ObjectMapper mapper = new ObjectMapper(); 
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		     HashMap<String, Object> retorno = mapper.readValue(ret, HashMap.class);
			if (ret != null) {					 
				response.setData(Optional.ofNullable(retorno));
				response.setStatus(true);			
			} else {
				response.setStatus(true);
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/buscarPorPlaca/{placa}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> buscarPorPlaca(@PathVariable("placa") String placa, 
			                                                 @PathVariable("uf") String uf, 
															 @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
															 @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((placa == null || placa.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			String ret = consultaService.consultarPLACA(placa, uf, codigoTipoConsulta, codigoUsuarioEmpresa);
			
			 ObjectMapper mapper = new ObjectMapper(); 
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		     HashMap<String, Object> retorno = mapper.readValue(ret, HashMap.class);
			if (ret != null) {					 
				response.setData(Optional.ofNullable(retorno));
				response.setStatus(true);			
			} else {
				response.setStatus(true);
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/buscaPorNome/{nome}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> buscaPorNome(@PathVariable("nome") String nome, 
			                                                 @PathVariable("uf") String uf, 
															 @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
															 @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((nome == null || nome.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			String ret = consultaService.consultarNome(nome, uf, codigoTipoConsulta, codigoUsuarioEmpresa);
			
			 ObjectMapper mapper = new ObjectMapper(); 
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
		     mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		     HashMap<String, Object> retorno = mapper.readValue(ret, HashMap.class);
			if (ret != null) {					 
				response.setData(Optional.ofNullable(retorno));
				response.setStatus(true);			
			} else {
				response.setStatus(true);
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);		
	}
	
	
}
