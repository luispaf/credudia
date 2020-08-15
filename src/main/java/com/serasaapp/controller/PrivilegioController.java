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

import com.serasaapp.domain.Privilegio;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.PrivilegioService;

@RestController
@RequestMapping("/privilegio")
@CrossOrigin(origins = "*")
public class PrivilegioController {

	@Autowired
	PrivilegioService privilegioService;

	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {				
			Optional<List<Privilegio>> listaOptional = null;
			List<Privilegio> lista = privilegioService.todos();
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
	public ResponseEntity<GenericResponse<?>> save(@RequestBody Privilegio privilegio) {
		GenericResponse<Privilegio> response = new GenericResponse<Privilegio>();
		List<String> erros = new ArrayList<String>();
		try {
			if (privilegio != null) {
				Privilegio privilegioSalvo = privilegioService.salvar(privilegio);
				Optional<Privilegio> optional =  Optional.ofNullable(privilegioSalvo);	
				response.setData(optional);
				response.setStatus(true);	
			} else {
				throw new Exception("Privilegio não informado.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<GenericResponse<?>> excluir(@RequestBody Long codigo) {
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<Privilegio> privilegio = privilegioService.buscarPorId(codigo);
				if (privilegio.isPresent() && privilegio.get().getCodigo() != null) {
					privilegioService.excluir(privilegio.get());				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("Privilegio não informado para exclusão.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
	@PostMapping("/filtro")
	public ResponseEntity<GenericResponse<?>> filtro(@RequestBody Privilegio privilegio) {
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {
			if (privilegio != null) {
				List<Privilegio> listaPrivilegio = privilegioService.buscarPrivilegioFiltro(privilegio);
				Optional<List<Privilegio>> lista =  Optional.ofNullable(listaPrivilegio);	
				response.setData(lista);
				response.setStatus(true);	
			} else {
				throw new Exception("Privilegio não informado filtro.");
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
		GenericResponse<Privilegio> response = new GenericResponse<Privilegio>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<Privilegio> privilegio = privilegioService.buscarPorId(codigo);
				if (privilegio.isPresent() && privilegio.get().getCodigo() != null) {
					response.setData(privilegio);				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("Privilegio não informado para busca por ID.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
}
