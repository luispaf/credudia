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

import com.serasaapp.domain.Empresa;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {


	@Autowired
	EmpresaService empresaService;
	
	@GetMapping("/todos")
	public ResponseEntity<GenericResponse<?>> todos() {
		GenericResponse<List<Empresa>> response = new GenericResponse<List<Empresa>>();
		List<String> erros = new ArrayList<String>();
		try {		
			Optional<List<Empresa>> listaOptional = null;
			List<Empresa> lista = empresaService.todos();
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
	public ResponseEntity<GenericResponse<?>> salvar(@RequestBody Empresa empresa) {
		GenericResponse<Empresa> response = new GenericResponse<Empresa>();
		List<String> erros = new ArrayList<String>();
		try {
			if (empresa != null) {
				Empresa registroSalvo = empresaService.salvar(empresa);
				Optional<Empresa> optional =  Optional.ofNullable(registroSalvo);	
				response.setData(optional);
				response.setStatus(true);	
			} else {
				throw new Exception("Empresa não informado.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}
	
	@PostMapping("/deletar")
	public ResponseEntity<GenericResponse<?>> deletar(@RequestBody Long codigo) {
		GenericResponse<List<Empresa>> response = new GenericResponse<List<Empresa>>();
		List<String> erros = new ArrayList<String>();
		try {	
			Optional<Empresa> empresa = empresaService.buscarPorId(codigo);
			if (empresa.isPresent() && empresa.get().getCodigo() != null) {
				empresaService.excluir(empresa.get());			
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
	public ResponseEntity<GenericResponse<?>> filtro(@RequestBody Empresa empresa) {
		GenericResponse<List<Empresa>> response = new GenericResponse<List<Empresa>>();
		List<String> erros = new ArrayList<String>();
		try {
			if (empresa != null) {
				List<Empresa> listaRetorno = empresaService.buscarEmpresaFiltro(empresa);
				Optional<List<Empresa>> lista =  Optional.ofNullable(listaRetorno);	
				response.setData(lista);
				response.setStatus(true);	
			} else {
				throw new Exception("Empresa não informado filtro.");
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
		GenericResponse<Empresa> response = new GenericResponse<Empresa>();
		List<String> erros = new ArrayList<String>();
		try {	
			if (codigo != null) {
				Optional<Empresa> empresa = empresaService.buscarPorId(codigo);
				if (empresa.isPresent() && empresa.get().getCodigo() != null) {
					response.setData(empresa);				
				}
				response.setStatus(true);				
			} else {
				throw new Exception("Empresa não informado para busca por ID.");
			}
		} catch (Exception e) {
			erros.add("Falha: " + e.getMessage());
			response.setStatus(false);
			response.setErrors(erros);
		}
		return ResponseEntity.ok(response);			
	}	
	
}
