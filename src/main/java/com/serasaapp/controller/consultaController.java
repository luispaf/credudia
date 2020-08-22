package com.serasaapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasaapp.consultas.cpfDetalhada.RetornoPrincipalCPF;
import com.serasaapp.consultas.placas.RetornoPrincipalPLACAS;
import com.serasaapp.response.GenericResponse;
import com.serasaapp.service.ConsultaService;
import com.serasaapp.util.Util;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*")
public class consultaController {
	
	@Autowired
	ConsultaService consultaService;
	
	@GetMapping("/buscarPorCPF/{cpf}/{uf}")
	public ResponseEntity<GenericResponse<?>> buscarPorCPF(@PathVariable("cpf") String cpf, @PathVariable("uf") String uf) {
		
		GenericResponse<RetornoPrincipalCPF> response = new GenericResponse<RetornoPrincipalCPF>();
		List<String> erros = new ArrayList<String>();
		try {
			
			Boolean cpfValido = Util.isCPF(cpf);
			if (!cpfValido) {
				throw new Exception("CPF invalido: " + cpf);
			}
			
			RetornoPrincipalCPF ret = consultaService.consultarCPF(cpf, uf);
			if (ret != null) {					 
				response.setData(Optional.ofNullable(ret));
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
	
	@GetMapping("/buscarPorPlaca/{placa}/{uf}")
	public ResponseEntity<GenericResponse<?>> buscarPorPlaca(@PathVariable("placa") String placa, @PathVariable("uf") String uf) {
		
		GenericResponse<RetornoPrincipalPLACAS> response = new GenericResponse<RetornoPrincipalPLACAS>();
		List<String> erros = new ArrayList<String>();
		try {
			RetornoPrincipalPLACAS ret = consultaService.consultarPLACA(placa, uf);
			if (ret != null) {					 
				response.setData(Optional.ofNullable(ret));
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
