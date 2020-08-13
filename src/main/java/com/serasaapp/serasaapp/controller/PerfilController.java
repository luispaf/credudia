package com.serasaapp.serasaapp.controller;

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

import com.serasaapp.serasaapp.domain.Perfil;
import com.serasaapp.serasaapp.domain.Privilegio;
import com.serasaapp.serasaapp.repository.PerfilRepository;
import com.serasaapp.serasaapp.repository.PrivilegioRepository;
import com.serasaapp.serasaapp.response.GenericResponse;

@RestController
@RequestMapping("/perfil")
@CrossOrigin(origins = "*")
public class PerfilController {


	@Autowired
	PerfilRepository perfilRepository;
	
	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Perfil>> response = new GenericResponse<List<Perfil>>();
		List<String> erros = new ArrayList<String>();
		try {			
			
			Optional<List<Perfil>> listaOptional = null;
			List<Perfil> lista = perfilRepository.findAll();
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
		GenericResponse<List<Perfil>> response = new GenericResponse<List<Perfil>>();
		List<String> erros = new ArrayList<String>();
		try {				
			perfilRepository.save(perfil);
			response.setStatus(true);
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
			Optional<Perfil> perfil = perfilRepository.findById(codigo);
			if (perfil.isPresent() && perfil.get().getCodigo() != null) {
				perfilRepository.deleteById(codigo);				
			}
			response.setStatus(true);
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
}
