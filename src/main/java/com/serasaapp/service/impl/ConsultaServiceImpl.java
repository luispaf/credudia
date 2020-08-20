package com.serasaapp.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serasaapp.consultas.cpfDetalhada.RetornoPrincipalCPF;
import com.serasaapp.consultas.placas.RetornoPrincipalPLACAS;
import com.serasaapp.domain.Parametro;
import com.serasaapp.repository.ParametroRepository;
import com.serasaapp.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService{

	@Autowired
	ParametroRepository parametroRepository;
	
	public final Long USUARIO_SENHA_APLICACAO = 1L;
	public final Long BUSCA_POR_CPF = 2l;
	public final Long BUSCA_POR_PLACAS = 3L;
	
	@Override
	public RetornoPrincipalCPF consultarCPF(String cpf, String uf) throws Exception {
		if ((cpf != null && cpf.length() > 0) && (uf != null && uf.length() > 0)) {
			try {				
				Parametro  UsuarioSenhaAplicacao = this.buscarParametro(USUARIO_SENHA_APLICACAO);
				String authorization = this.gerarBase64(UsuarioSenhaAplicacao.getUsuario() + ":" + UsuarioSenhaAplicacao.getSenha());
				Parametro parametroCPF = this.buscarParametro(BUSCA_POR_CPF);
				
				MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		        Map map = new HashMap<String, String>();
		        map.put("Authorization", "Basic " + authorization);
		        map.put("api-token", parametroCPF.getValor1());

		        headers.setAll(map);

		        Map req_payload = new HashMap();
		        req_payload.put("produto", parametroCPF.getValor2());
		        req_payload.put("param", "cpf");
		        req_payload.put("value", cpf);
		        req_payload.put("uf", uf);

		        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		        String url = parametroCPF.getValor3();

		        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
			             
			     String json = response.getBody().toString();
			     RetornoPrincipalCPF infobuscaDetalhadaPfPj = new RetornoPrincipalCPF();  
			     ObjectMapper mapper = new ObjectMapper(); 
			     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			     infobuscaDetalhadaPfPj = mapper.readValue(json, RetornoPrincipalCPF.class);
			     return infobuscaDetalhadaPfPj;
			} catch (Exception e) {
				throw new Exception("Falha ao consultar o CPF: " + cpf + "/Nenhum registro foi encontrado!");
			}		
		}
		return null;
	}
	
	@Override
	public RetornoPrincipalPLACAS consultarPLACA(String placa, String uf) throws Exception {
		if ((placa != null && placa.length() > 0) && (uf != null && uf.length() > 0)) {
			try {				
				Parametro  UsuarioSenhaAplicacao = this.buscarParametro(USUARIO_SENHA_APLICACAO);
				String authorization = this.gerarBase64(UsuarioSenhaAplicacao.getUsuario() + ":" + UsuarioSenhaAplicacao.getSenha());
				Parametro parametroCPF = this.buscarParametro(BUSCA_POR_PLACAS);
				
				MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		        Map map = new HashMap<String, String>();
		        map.put("Authorization", "Basic " + authorization);
		        map.put("api-token", parametroCPF.getValor1());

		        headers.setAll(map);

		        Map req_payload = new HashMap();
		        req_payload.put("produto", parametroCPF.getValor2());
		        req_payload.put("param", "cpf");
		        req_payload.put("value", placa);
		        req_payload.put("uf", uf);

		        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
		        String url = parametroCPF.getValor3();

		        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
			             
			     String json = response.getBody().toString();
			     RetornoPrincipalPLACAS retornoPrincipalPLACAS = new RetornoPrincipalPLACAS();  
			     ObjectMapper mapper = new ObjectMapper(); 
			     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			     retornoPrincipalPLACAS = mapper.readValue(json, RetornoPrincipalPLACAS.class);
			     return retornoPrincipalPLACAS;
			} catch (Exception e) {
				throw new Exception("Falha ao consultar a placa: " + placa + "/Nenhum registro foi encontrado!");
			}		
		}
		return null;
	}

	
	public Parametro buscarParametro(Long codigoParametro) throws Exception {
		Optional<Parametro> parametro = parametroRepository.findById(codigoParametro);
		if (parametro != null && parametro.isPresent()) {
			return parametro.get();
		}else {
			throw new Exception("Parametro:" + codigoParametro + " não encontrado!");
		}		
	}
	
	public static String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;       
    
    }
	
	public String gerarBase64(String usuarioSenha) {
		return Base64.getEncoder().encodeToString(usuarioSenha.getBytes());
	}
	
	@Override
	public boolean isCPF(String CPF) {
	    // considera-se erro CPF's formados por uma sequencia de numeros iguais
	    if (CPF.equals("00000000000") ||
	        CPF.equals("11111111111") ||
	        CPF.equals("22222222222") || CPF.equals("33333333333") ||
	        CPF.equals("44444444444") || CPF.equals("55555555555") ||
	        CPF.equals("66666666666") || CPF.equals("77777777777") ||
	        CPF.equals("88888888888") || CPF.equals("99999999999") ||
	        (CPF.length() != 11))
	        return(false);
	
	    char dig10, dig11;
	    int sm, i, r, num, peso;
	
	    // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	    try {
	    // Calculo do 1o. Digito Verificador
	        sm = 0;
	        peso = 10;
	        for (i=0; i<9; i++) {
	    // converte o i-esimo caractere do CPF em um numero:
	    // por exemplo, transforma o caractere '0' no inteiro 0
	    // (48 eh a posicao de '0' na tabela ASCII)
	        num = (int)(CPF.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso - 1;
	        }
	
	        r = 11 - (sm % 11);
	        if ((r == 10) || (r == 11))
	            dig10 = '0';
	        else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
	
	    // Calculo do 2o. Digito Verificador
	        sm = 0;
	        peso = 11;
	        for(i=0; i<10; i++) {
	        num = (int)(CPF.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso - 1;
	        }
	
	        r = 11 - (sm % 11);
	        if ((r == 10) || (r == 11))
	             dig11 = '0';
	        else dig11 = (char)(r + 48);
	
	    // Verifica se os digitos calculados conferem com os digitos informados.
	        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	             return(true);
	        else return(false);
	            } catch (InputMismatchException erro) {
	            return(false);
	        }
	    }

	
}
