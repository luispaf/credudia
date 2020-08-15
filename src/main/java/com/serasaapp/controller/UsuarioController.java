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

import com.serasaapp.domain.Usuario;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	

	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Usuario>> response = new GenericResponse<List<Usuario>>();
		List<String> erros = new ArrayList<String>();
		try {		
			Optional<List<Usuario>> listaOptional = null;
			List<Usuario> lista = service.todos();
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
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody Usuario usuario) {
		GenericResponse<Usuario> response = new GenericResponse<Usuario>();
		List<String> erros = new ArrayList<String>();
		try {
			if (usuario != null) {
				Usuario registroSalvo = service.salvar(usuario);
				Optional<Usuario> optional =  Optional.ofNullable(registroSalvo);	
				response.setData(optional);
				response.setStatus(true);	
			} else {
				throw new Exception("Usuario não informado.");
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
		GenericResponse<List<Usuario>> response = new GenericResponse<List<Usuario>>();
		List<String> erros = new ArrayList<String>();
		try {	
			Optional<Usuario> reg = service.buscarPorId(codigo);
			if (reg.isPresent() && reg.get().getCodigo() != null) {
				service.excluir(reg.get());			
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
	public ResponseEntity<GenericResponse<?>> filtro(@RequestBody Usuario usuario) {
		GenericResponse<List<Usuario>> response = new GenericResponse<List<Usuario>>();
		List<String> erros = new ArrayList<String>();
		try {
			if (usuario != null) {
				List<Usuario> lista = service.buscarFiltro(usuario);
				Optional<List<Usuario>> listaOptional =  Optional.ofNullable(lista);	
				response.setData(listaOptional);
				response.setStatus(true);	
			} else {
				throw new Exception("Usuario não informado filtro.");
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
		GenericResponse<Usuario> response = new GenericResponse<Usuario>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<Usuario> reg = service.buscarPorId(codigo);
				if (reg.isPresent() && reg.get().getCodigo() != null) {
					response.setData(reg);				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("Usuario não informado para busca por ID.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
}
