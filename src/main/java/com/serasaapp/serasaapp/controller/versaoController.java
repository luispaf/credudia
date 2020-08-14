package com.serasaapp.serasaapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/versao")
@CrossOrigin(origins = "*")
public class versaoController {
	
	private RestTemplate rest = new RestTemplate();

	@GetMapping("/versaoatual")
	public String vesaoAtual() {
		return "1.0.0";
	}
	
	@GetMapping("/buscaVersaoAtual")
	public String buscaVersaoAtual() {
		/*
		 * String uri = "http://localhost:8080/versao/versaoatual"; String teste =
		 * rest.getForObject(uri, String.class);
		 */
		this.teste();
        return "";
	}
	
	public String teste() {
		// TODO Auto-generated method stub
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Authorization", "Basic c29hcmVzOjEwMjAzMA==");
        map.put("api-token", "215F2417EAB71A115962009389G5YD2F06HPVXIG2QTLGXJBWZ");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("produto", "215F05DDDB5489715942199957JETE0B9WP50P3M1MZREOLZ3T");
        req_payload.put("param", "cpf");
        req_payload.put("value", "74550713615");
        req_payload.put("uf", "MG");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
        String url = "https://www.radarconsultas.com.br/rdrv2/api/consultar";

        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
        //ServiceResponse entityResponse = (ServiceResponse) response.getBody();
        //System.out.println(entityResponse.getData());
        return "";
	}
}
