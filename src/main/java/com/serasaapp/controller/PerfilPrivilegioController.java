package com.serasaapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasaapp.domain.PerfilPrivilegio;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.PerfilPrivilegioService;

@RestController
@RequestMapping("/perfilprivilegio")
@CrossOrigin(origins = "*")
public class PerfilPrivilegioController {

	@Autowired
	PerfilPrivilegioService Service;
	
	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<PerfilPrivilegio>> response = new GenericResponse<List<PerfilPrivilegio>>();
		List<String> erros = new ArrayList<String>();
		try {		
			Optional<List<PerfilPrivilegio>> listaOptional = null;
			List<PerfilPrivilegio> lista = Service.todos();
			listaOptional = Optional.ofNullable(lista);					 
			response.setData(listaOptional);
			response.setStatus(true);			
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);		
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody PerfilPrivilegio perfilPrivilegio) {
		GenericResponse<PerfilPrivilegio> response = new GenericResponse<PerfilPrivilegio>();
		List<String> erros = new ArrayList<String>();
		try {
			if (perfilPrivilegio != null) {
				PerfilPrivilegio registroSalvo = Service.salvar(perfilPrivilegio);
				Optional<PerfilPrivilegio> optional =  Optional.ofNullable(registroSalvo);	
				response.setData(optional);
				response.setStatus(true);	
			} else {
				throw new Exception("PerfilPrivilegio não informado.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody Long codigo) {
		GenericResponse<List<PerfilPrivilegio>> response = new GenericResponse<List<PerfilPrivilegio>>();
		List<String> erros = new ArrayList<String>();
		try {	
			Optional<PerfilPrivilegio> reg = Service.buscarPorId(codigo);
			if (reg.isPresent() && reg.get().getCodigo() != null) {
				Service.excluir(reg.get());			
			}
			response.setStatus(true);
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
	@PostMapping("/filtro")
	public ResponseEntity<GenericResponse<?>> filtro(@RequestBody PerfilPrivilegio perfilPrivilegio) {
		GenericResponse<List<PerfilPrivilegio>> response = new GenericResponse<List<PerfilPrivilegio>>();
		List<String> erros = new ArrayList<String>();
		try {
			if (perfilPrivilegio != null) {
				List<PerfilPrivilegio> lista = Service.buscarFiltro(perfilPrivilegio);
				Optional<List<PerfilPrivilegio>> listaOptional =  Optional.ofNullable(lista);	
				response.setData(listaOptional);
				response.setStatus(true);	
			} else {
				throw new Exception("PerfilPrivilegio não informado filtro.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}
	
	@PostMapping("/id")
	public ResponseEntity<GenericResponse<?>> buscarPorId(@RequestBody Long codigo) {
		GenericResponse<PerfilPrivilegio> response = new GenericResponse<PerfilPrivilegio>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<PerfilPrivilegio> reg = Service.buscarPorId(codigo);
				if (reg.isPresent() && reg.get().getCodigo() != null) {
					response.setData(reg);				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("PerfilPrivilegio não informado para busca por ID.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
}
