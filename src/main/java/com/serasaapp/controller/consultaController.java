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
import com.serasaapp.domain.RetornoConsulta;
import com.serasaapp.repository.RetornoConsultaRepository;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.ConsultaService;
import com.serasaapp.util.Util;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*")
public class consultaController {
	
	@Autowired
	ConsultaService consultaService;
	@Autowired
	RetornoConsultaRepository retornoConsultaRepository;
	
	
	public final Long INFOBUSCA_DETALHADA_PF = 1L;
	public final Long INFOBUSCA_DETALHADA_PJ = 2L;
	public final Long ACOES_JUDICIAIS_PF = 6L;
	public final Long ACOES_JUDICIAIS_PJ = 7L;
	
	@GetMapping("/infobuscaDetalhada/{valor}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> infobuscaDetalhada(@PathVariable("valor") String valor, 
														   @PathVariable("uf") String uf, 
														   @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
														   @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {		
		
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			if ((valor == null || valor.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}	
			
			String ret = "";
			if (codigoTipoConsulta.equals(INFOBUSCA_DETALHADA_PF)) {
				Boolean cpfValido = Util.isCPF(valor);				
				if (!cpfValido) {throw new Exception("CPF invalido: " + valor);}				
				ret = consultaService.infobuscaDetalhada(valor, uf, INFOBUSCA_DETALHADA_PF, codigoUsuarioEmpresa);					
			}
			
			if (codigoTipoConsulta.equals(INFOBUSCA_DETALHADA_PJ)) {
				Boolean cnpjValido = Util.isCNPJ(valor); 
				if (!cnpjValido) {throw new Exception("CNPJ invalido: " + valor);}
				ret = consultaService.infobuscaDetalhada(valor, uf, INFOBUSCA_DETALHADA_PJ, codigoUsuarioEmpresa);	
			}
			
			if (ret != null && ret.length() > 0) {	
				ObjectMapper mapper = new ObjectMapper(); 
			    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
			    HashMap<String, Object> retorno = mapper.readValue(ret, HashMap.class);
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
	
	@GetMapping("/veicularTOP/{placa}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> veicularTOP(@PathVariable("placa") String placa, 
			                                                 @PathVariable("uf") String uf, 
															 @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
															 @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((placa == null || placa.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			String ret = consultaService.veicularTOP(placa, uf, codigoTipoConsulta, codigoUsuarioEmpresa);
			
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
	
	@GetMapping("/infobusca/{nome}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> infobusca(@PathVariable("nome") String nome, 
			                                            @PathVariable("uf") String uf, 
														@PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
														@PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((nome == null || nome.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			String ret = consultaService.infobusca(nome, uf, codigoTipoConsulta, codigoUsuarioEmpresa);
			
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
	
	@GetMapping("/leilaoCompletaMaisScore/{placa}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> leilaoCompletaMaisScore(@PathVariable("placa") String placa, 
					                                                  @PathVariable("uf") String uf, 
																	  @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
																	  @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((placa == null || placa.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			String ret = consultaService.leilaoCompletaMaisScore(placa, uf, codigoTipoConsulta, codigoUsuarioEmpresa);
			
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
	

	@GetMapping("/acoesJudiciais/{valor}/{uf}/{codigoTipoConsulta}/{codigoUsuarioEmpresa}")
	public ResponseEntity<GenericResponse<?>> acoesJudiciais(@PathVariable("valor") String valor, 
														   	 @PathVariable("uf") String uf, 
														   	 @PathVariable("codigoTipoConsulta") Long codigoTipoConsulta,
														   	 @PathVariable("codigoUsuarioEmpresa") Long codigoUsuarioEmpresa) {		
		
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if ((valor == null || valor.length() == 0) || (uf == null || uf.length() == 0) || (codigoTipoConsulta == null) || (codigoUsuarioEmpresa == null)) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			
			String ret = "";
			if (codigoTipoConsulta.equals(ACOES_JUDICIAIS_PF)) {
				Boolean cpfValido = Util.isCPF(valor);				
				if (!cpfValido) {throw new Exception("CPF invalido: " + valor);}				
				ret = consultaService.acoesJudiciais(valor, uf, ACOES_JUDICIAIS_PF, codigoUsuarioEmpresa);				
			}
			if (codigoTipoConsulta.equals(ACOES_JUDICIAIS_PJ)) {
				Boolean cnpjValido = Util.isCNPJ(valor);				
				if (!cnpjValido) {throw new Exception("CNPJ invalido: " + valor);}				
				ret = consultaService.acoesJudiciais(valor, uf, ACOES_JUDICIAIS_PJ, codigoUsuarioEmpresa);
			}			
		
			if (ret != null && ret.length() > 0) {					 
				ObjectMapper mapper = new ObjectMapper(); 
			    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			    mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
			    HashMap<String, Object> retorno = mapper.readValue(ret, HashMap.class);
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
	
	@GetMapping("/buscaRetornoPorCodigo/{codigo}")
	public ResponseEntity<GenericResponse<?>> buscaRetornoPorCodigo(@PathVariable("codigo") Long codigo) {
		GenericResponse<RetornoConsulta> response = new GenericResponse<RetornoConsulta>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if (codigo == null) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			
			Optional<RetornoConsulta> ret = retornoConsultaRepository.findById(codigo);			
			
			if (ret != null && ret.isPresent()) {					 
				response.setData(ret);
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
	
	@GetMapping("/buscaJsonRetornoPorCodigo/{codigo}")
	public ResponseEntity<GenericResponse<?>> buscaJsonRetornoPorCodigo(@PathVariable("codigo") Long codigo) {
		GenericResponse<HashMap> response = new GenericResponse<HashMap>();
		List<String> erros = new ArrayList<String>();
		try {
			
			if (codigo == null) {
				throw new Exception("Todos os parametros são origatórios!");
			}
			
			Optional<RetornoConsulta> ret = retornoConsultaRepository.findById(codigo);			
			
			if (ret != null && ret.isPresent() && ret.get().getJson() != null && ret.get().getJson().length() > 0) {
				String json = ret.get().getJson();				
				ObjectMapper mapper = new ObjectMapper(); 
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
				mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
				mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
				HashMap<String, Object> retorno = mapper.readValue(json, HashMap.class);
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
