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

import com.serasaapp.serasaapp.domain.Privilegio;
import com.serasaapp.serasaapp.repository.PrivilegioRepository;
import com.serasaapp.serasaapp.response.GenericResponse;

@RestController
@RequestMapping("/privilegio")
@CrossOrigin(origins = "*")
public class PrivilegioController {

	@Autowired
	PrivilegioRepository privilegioRepository;
	
	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {			
			
			Optional<List<Privilegio>> listaOptional = null;
			List<Privilegio> lista = privilegioRepository.findAll();
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
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody Privilegio privilegio) {
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {				
			privilegioRepository.save(privilegio);
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
		GenericResponse<List<Privilegio>> response = new GenericResponse<List<Privilegio>>();
		List<String> erros = new ArrayList<String>();
		try {	
			Optional<Privilegio> privilegio = privilegioRepository.findById(codigo);
			if (privilegio.isPresent() && privilegio.get().getCodigo() != null) {
				privilegioRepository.deleteById(codigo);				
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
