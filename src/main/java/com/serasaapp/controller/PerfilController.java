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

import com.serasaapp.domain.Perfil;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.PerfilService;

@RestController
@RequestMapping("/perfil")
@CrossOrigin(origins = "*")
public class PerfilController {


	@Autowired
	PerfilService perfilService;
	
	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Perfil>> response = new GenericResponse<List<Perfil>>();
		List<String> erros = new ArrayList<String>();
		try {		
			Optional<List<Perfil>> listaOptional = null;
			List<Perfil> lista = perfilService.todos();
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
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody Perfil perfil) {
		GenericResponse<Perfil> response = new GenericResponse<Perfil>();
		List<String> erros = new ArrayList<String>();
		try {
			if (perfil != null) {
				Perfil perfilSalvo = perfilService.salvar(perfil);
				Optional<Perfil> optional =  Optional.ofNullable(perfilSalvo);	
				response.setData(optional);
				response.setStatus(true);	
			} else {
				throw new Exception("Perfil não informado.");
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
		GenericResponse<List<Perfil>> response = new GenericResponse<List<Perfil>>();
		List<String> erros = new ArrayList<String>();
		try {	
			Optional<Perfil> perfil = perfilService.buscarPorId(codigo);
			if (perfil.isPresent() && perfil.get().getCodigo() != null) {
				perfilService.excluir(perfil.get());			
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
	public ResponseEntity<GenericResponse<?>> filtro(@RequestBody Perfil perfil) {
		GenericResponse<List<Perfil>> response = new GenericResponse<List<Perfil>>();
		List<String> erros = new ArrayList<String>();
		try {
			if (perfil != null) {
				List<Perfil> listaPerfil = perfilService.buscarPrivilegioFiltro(perfil);
				Optional<List<Perfil>> lista =  Optional.ofNullable(listaPerfil);	
				response.setData(lista);
				response.setStatus(true);	
			} else {
				throw new Exception("Perfil não informado filtro.");
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
		GenericResponse<Perfil> response = new GenericResponse<Perfil>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<Perfil> perfil = perfilService.buscarPorId(codigo);
				if (perfil.isPresent() && perfil.get().getCodigo() != null) {
					response.setData(perfil);				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("Perfil não informado para busca por ID.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
}
