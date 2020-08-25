package com.serasaapp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.serasaapp.domain.Parametro;
import com.serasaapp.domain.RetornoConsulta;
import com.serasaapp.repository.ParametroRepository;
import com.serasaapp.repository.RetornoConsultaRepository;
import com.serasaapp.service.ConsultaService;
import com.serasaapp.util.Util;

@Service
public class ConsultaServiceImpl implements ConsultaService{
	
	@Autowired
	RetornoConsultaRepository retornoConsultaRepository;
	@Autowired
	ParametroRepository parametroRepository;
	
	
	
	public final Long USUARIO_SENHA_APLICACAO = 1L;
	public final Long BUSCA_POR_CPF = 2l;
	public final Long BUSCA_POR_PLACAS = 3L;
	public final Long BUSCA_POR_NOME = 4L;
	
	@Override
	public String consultarCPF(String cpf, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception {
		try {				
			Parametro UsuarioSenhaAplicacao = this.buscarParametro(USUARIO_SENHA_APLICACAO);
			String authorization = Util.gerarBase64(UsuarioSenhaAplicacao.getUsuario() + ":" + UsuarioSenhaAplicacao.getSenha());
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

			//ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
			//String json = response.getBody().toString();
			
			 String json =  buscaJson("CPF");

			 // Salva o retorno
			 RetornoConsulta retornoSalvo = this.salvarRetorno(codigoTipoConsulta, codigoUsuarioEmpresa, json, cpf, uf);	
		     return json;
		} catch (Exception e) {
			throw new Exception("Falha ao consultar o CPF: " + e.getMessage());
		}
	}
	
	@Override
	public String consultarPLACA(String placa, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception {
		try {				
			Parametro  UsuarioSenhaAplicacao = this.buscarParametro(USUARIO_SENHA_APLICACAO);
			String authorization = Util.gerarBase64(UsuarioSenhaAplicacao.getUsuario() + ":" + UsuarioSenhaAplicacao.getSenha());
			Parametro parametroCPF = this.buscarParametro(BUSCA_POR_PLACAS);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	        Map map = new HashMap<String, String>();
	        map.put("Authorization", "Basic " + authorization);
	        map.put("api-token", parametroCPF.getValor1());

	        headers.setAll(map);

	        Map req_payload = new HashMap();
	        req_payload.put("produto", parametroCPF.getValor2());
	        req_payload.put("param", "placa");
	        req_payload.put("value", placa);
	        req_payload.put("uf", uf);

	        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
	        String url = parametroCPF.getValor3();

	        //ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);			             
		     //String json = response.getBody().toString();
	        
			String json  = buscaJson("PLACA");
			
			 // Salva o retorno
			 RetornoConsulta retornoSalvo = this.salvarRetorno(codigoTipoConsulta, codigoUsuarioEmpresa, json, placa, uf);	
			 
		     return json;
		} catch (Exception e) {
			throw new Exception("Falha ao consultar a placa: " + placa + "/Nenhum registro foi encontrado!");
		}		
	}
	
	@Override
	public String consultarNome(String nome, String uf, Long codigoTipoConsulta,  Long codigoUsuarioEmpresa) throws Exception {
		try {				
			Parametro  UsuarioSenhaAplicacao = this.buscarParametro(USUARIO_SENHA_APLICACAO);
			String authorization = Util.gerarBase64(UsuarioSenhaAplicacao.getUsuario() + ":" + UsuarioSenhaAplicacao.getSenha());
			Parametro parametroCPF = this.buscarParametro(BUSCA_POR_NOME);
			
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	        Map map = new HashMap<String, String>();
	        map.put("Authorization", "Basic " + authorization);
	        map.put("api-token", parametroCPF.getValor1());

	        headers.setAll(map);

	        Map req_payload = new HashMap();
	        req_payload.put("produto", parametroCPF.getValor2());
	        req_payload.put("param", "nome");
	        req_payload.put("value", nome);
	        req_payload.put("uf", uf);

	        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
	        String url = parametroCPF.getValor3();

	        //ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);			             
		    //String json = response.getBody().toString();
	        
			String json  = buscaJson("NOME");
			
			 // Salva o retorno
			RetornoConsulta retornoSalvo = this.salvarRetorno(codigoTipoConsulta, codigoUsuarioEmpresa, json, nome, uf);
			 
		     return json;
		} catch (Exception e) {
			throw new Exception("Falha ao consultar o nome: " + nome + "/Nenhum registro foi encontrado!");
		}	
	}

	public String buscaJson(String tipoConsulta) {
		if (tipoConsulta == "PLACA") {
			return "{\"message\":\"Consulta realizada com sucesso\",\"consulta\":{\"token_grupoconsulta\":\"215F05DFB49ADCF15942204680V0OFKHVBBF135OM3TBIUF8WV\",\"token\":\"215F3FF3A05BDFF1598026656B0RXEM6URRA9L0M91VT4ZTFRQ\",\"parametro\":\"PLACA\",\"parametro_valor\":\"HGC6128\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"titulo\":\"Veicular TOP\",\"usuario\":{\"token\":null,\"nome\":\"SOARES\",\"email\":\"atendimento1@ksiconsultas.com\",\"cpf\":\"898.079.875-04\",\"cliente\":{\"nome\":\"SOARES\",\"nomeFantasia\":\"\",\"razaoSocial\":\"\",\"cpf\":\"898.079.875-04\",\"cnpj\":null,\"cidade\":\"SALVADOR\",\"uf\":\"BA\"}},\"status\":2,\"resultados\":[{\"id\":66,\"title\":\"BIN RF + Importa\\u00e7\\u00e3o\",\"token\":\"c1111bd512b29e821b120b86446026b8\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"situacao\":\"CIRCULACAO\",\"ocorrencia\":\"VEICULO NAO INDICA OCORRENCIA DE ROUBO\\/FURTO\",\"placa\":\"HGC6128\",\"municipio\":\"UBERLANDIA\",\"uf\":\"MG\",\"renavam\":\"00256732990\",\"documento\":\"09297615619\",\"ultimaatualizacao\":\"05\\/05\\/2020\",\"chassi\":\"9C6KG0460B0017298\",\"remarcacaodochassi\":\"NORMAL\",\"montagem\":null,\"motor\":\"G390E-017289\",\"caixadocambio\":null,\"numerodacarroceria\":null,\"numerodoeixo\":\"0\",\"numerodoeixoaux\":null,\"aux\":null,\"marca\":\"YAMAHA\\/FAZER YS250\",\"tipodoveiculo\":\"MOTOCICLETA\",\"anodefabricacao\":\"2010\",\"anodomodelo\":\"2011\",\"procedencia\":\"NACIONAL\",\"especie\":\"PASSAGEIRO\",\"combustivel\":\"GASOLINA\",\"cilindrada\":\"PASSAGEIRO\",\"cor\":\"PASSAGEIRO\",\"potencia\":\"PASSAGEIRO\",\"capacidadedepassageiros\":\"2\",\"capacidadedecarga\":\"0.0\",\"cmt\":\"0.0\",\"ptb\":\"0.32\",\"restricao\":[\"   \"],\"historico\":{\"status\":\"0\",\"mensagem\":\"Valor pesquisado n\\u00e3o foi encontrado na base consultada.\"}}},{\"id\":6,\"title\":\"Base Estadual\",\"token\":\"63f61ae75fbaa86e69ccd602ce88f31d\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"anofabricacaoveiculo\":\"2010\",\"anomodeloveiculo\":\"2011\",\"blindagem\":null,\"caixacambio\":null,\"capacidadecarga\":\"0.0\",\"categoria\":null,\"chassi\":\"9C6KG0460B0017298\",\"cilindradas\":\"249\",\"cmt\":\"0.0\",\"combustivel\":\"GASOLINA\",\"comunicacaovenda\":null,\"vendadatavenda\":null,\"documentocomprador\":null,\"vendainclusao\":null,\"vendanf\":null,\"vendanomecomprador\":null,\"vendaprotocolodentra\":null,\"tipodoccomprador\":null,\"cor\":\"PRETA\",\"dataemissaocrv\":null,\"dataatualizacao\":\"05\\/05\\/2020\",\"valordpvat\":\"0,00\",\"debitoipva\":\"0,00\",\"debitolicenciamento\":null,\"debitosmultascetesb\":null,\"debitosmultasder\":\"0,00\",\"debitosmultasdersa\":null,\"debitosmultasdetran\":\"0,00\",\"debitosmultasmunicipais\":null,\"debitosmultaspolrodfed\":null,\"multasrenainf\":null,\"docprop\":\"09297615619\",\"eixoauxiliar\":null,\"eixotraseiro\":null,\"especie\":\"PASSAGEIRO \\/ MOTOCICLETA\",\"codigoagentegravame\":null,\"datagravame\":null,\"datavigenciacontratogravame\":null,\"docfinanciadogravame\":null,\"nomeagentegravame\":null,\"nomefinanciadogravame\":null,\"numerocontratogravame\":null,\"restrfinanceiragravame\":null,\"indentificacaocontribuinte\":null,\"debitoslicenciamentoipva\":null,\"valortotalmultas\":null,\"valortotaldebitos\":0,\"agentegravame\":null,\"datainclusaogravame\":null,\"licenciamentodata\":\"05\\/05\\/2020\",\"licenciamentoexercicio\":null,\"marca\":\"YAMAHA\\/FAZER YS250\",\"numerodomotor\":\"G390E-017289\",\"municipio\":\"UBERLANDIA\",\"proprietarioatual\":\"LUIS PAULO ANDRADE FREITAS\",\"proprietarioanterior\":null,\"numcarroceria\":null,\"numeixos\":\"0\",\"observacoes\":\"VEICULO NAO INDICA OCORRENCIA DE ROUBO\\/FURTO ; VEICULO LICENCIADO EM 2020\",\"pbt\":\"0.32\",\"placa\":\"HGC6128\",\"placaanterior\":null,\"municipioplacaanterior\":null,\"ufplacaanterior\":null,\"potencia\":\"0\",\"procedencia\":null,\"documentoproprietarioanterior\":null,\"municipioanteriorproprietario\":null,\"rganteriorproprietario\":null,\"ufanteriorproprietario\":null,\"bairroproprietario\":null,\"cepproprietario\":null,\"enderecocomplementoproprietario\":null,\"enderecoproprietario\":null,\"ufenderecoproprietario\":null,\"municipioproprietario\":null,\"numeroenderecoproprietario\":null,\"orgaoexpedidorproprietario\":null,\"rgproprietario\":null,\"telefoneproprietario\":null,\"qtdpassageiros\":\"2\",\"renavam\":\"00256732990\",\"restricaoadministrativa\":null,\"resticoesbloqueioguincho\":null,\"roubofurto\":\"VEICULO NAO INDICA OCORRENCIA DE ROUBO\\/FURTO\",\"resticoesinspambiental\":null,\"restricaojudicial\":null,\"restricaorenajud\":null,\"restricaotributaria\":null,\"resticoes\":\"SEM RESTRICAO;SEM  RESTRICAO\",\"resticoesfinanceiras\":null,\"situacao\":\"CIRCULACAO\",\"statuschassi\":\"NORMAL\",\"tipocarroceria\":\"N\\u00e3O APLICAVEL\",\"tipodocprop\":\"CPF\",\"tipoveiculo\":\"MOTOCICLETA\",\"uf\":\"MG\"}},{\"id\":41,\"title\":\"Gravame completo e detalhado\",\"token\":\"05a6c7f934e7f9f88444855efc11d218\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"gravameObservacoes\":\"GRAVAME NAO CADASTRADO\"}},{\"id\":97,\"title\":\"Hist\\u00f3rico de Laudos \",\"token\":\"6ef889f58b462f2f09827410ab160979\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"message\":\"Ve\\u00edculo n\\u00e3o possu\\u00ed laudo em nossa plataforma\"}},{\"id\":69,\"title\":\"Decodificador de Chassi\",\"token\":\"4b1a5eeb58fdd8963cf24e19dc7c0c09\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"decodicador_chassi\":[\"Chassi n\\u00e3o possu\\u00ed irregularidades\"],\"dadosVeiculo\":{\"chassi\":\"9C6KG0460B0017298\",\"anoModelo\":\"2011\",\"combustivel\":\"Gasolina\",\"marca\":\"YAMAHA\",\"modelo\":\"FAZER YS250\",\"veiculo\":null,\"versao\":\"N\\u00e3o informado\",\"motor\":\"250\",\"codCategoria\":\"1\",\"categoria\":\"MOTOCICLETA\",\"localFabricacao\":null,\"origem\":\"NACIONAL\",\"pais\":\"BRASIL\",\"regiao\":\"BRASIL \\/ PARAGUAI \\/ COLOMBIA \\/ URUGUAI\"},\"precificador1\":[{\"codigo\":\"827052-0\",\"combustivel\":\"Gasolina\",\"marca\":\"YAMAHA\",\"modelo\":\"YS 250 FAZER\\/ FAZER L. EDITION \\/BLUEFLEX\",\"valor\":\"7818\"}],\"precificador2\":null}},{\"id\":132,\"title\":\"Sinistro - Base Hist\\u00f3rica\",\"token\":\"49714ed8e5986029007a0a10cd851040\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"status\":\"0\",\"message\":\"N\\u00e3o Possui Sinistro de Indeniza\\u00e7\\u00e3o Integral na Base Hist\\u00f3rica\"}},{\"id\":48,\"title\":\"Ofertas de Leil\\u00e3o\",\"token\":\"ae137524c197a7ad48665174ae64458b\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"status\":\"0\",\"mensagem\":\"Valor pesquisado n\\u00e3o foi encontrado na base consultada.\"}},{\"id\":183,\"title\":\"Ofertas de Leil\\u00e3o\",\"token\":\"ae137524c197a7ad48665174ae64458b\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":null},{\"id\":68,\"title\":\"Leil\\u00e3o Corporativo - Remarketing Automotivo - Cortesia\",\"token\":\"aeb97562f29ea632a27a0a76e6b6adaf\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":\"Class inv\\u00e1lida: app\\\\modules\\\\api\\\\serialize\\\\fornecedor\\\\leilaomark\"},{\"id\":123,\"title\":\"Localiza\\u00e7\\u00e3o - Informa\\u00e7\\u00f5es B\\u00e1sicas\",\"token\":\"ce7e311e14b92bf363bccc0299693dc6\",\"ctime\":\"2020-08-21T13:17:35-03:00\",\"retorno\":{\"cpf\":\"09297615619\",\"nome\":\"LUIS PAULO ANDRADE FREITAS\",\"nascimento\":\"13\\/04\\/1988\",\"idade\":32,\"sexo\":\"M\",\"nomedamae\":\"MARIA APARECIDA ANDRADE FREITAS\",\"signo\":\"N\\u00e3o Informado\",\"email\":[{\"email\":\"andradefreitas1988@hotmail.com\"}],\"enderecos\":[{\"logradouro\":\"RUA BENTO GONCALVES\",\"numero\":\"703\",\"bairro\":\"NOSSA SENHORA DAS GRACAS\",\"cidade\":\"UBERLANDIA\",\"uf\":\"MG\",\"cep\":\"38402004\"}],\"telefones\":[{\"ddd\":\"34\",\"telefone\":\"32573703\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32106073\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"999411654\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"991645219\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32139114\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32242007\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32916600\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32128050\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32916809\",\"tipo\":null}]}}]},\"result\":1}";			
		}
		if (tipoConsulta == "CPF") {
			return "{\"message\":\"Consulta realizada com sucesso\",\"consulta\":{\"token_grupoconsulta\":\"215F05DDDB5489715942199957JETE0B9WP50P3M1MZREOLZ3T\",\"token\":\"215F410BBD879EF15980983656XYD67TIKN5F9N9LCE3WK9NXD\",\"parametro\":\"CPF\",\"parametro_valor\":\"092.976.156-19\",\"ctime\":\"2020-08-22T09:12:44-03:00\",\"titulo\":\"Infobusca Detalhada PF\\/PJ\",\"usuario\":{\"token\":null,\"nome\":\"SOARES\",\"email\":\"atendimento1@ksiconsultas.com\",\"cpf\":\"898.079.875-04\",\"cliente\":{\"nome\":\"SOARES\",\"nomeFantasia\":\"\",\"razaoSocial\":\"\",\"cpf\":\"898.079.875-04\",\"cnpj\":null,\"cidade\":\"SALVADOR\",\"uf\":\"BA\"}},\"status\":1,\"resultados\":[{\"id\":123,\"title\":\"Localiza\\u00e7\\u00e3o - Informa\\u00e7\\u00f5es B\\u00e1sicas\",\"token\":\"ce7e311e14b92bf363bccc0299693dc6\",\"ctime\":\"2020-08-22T09:12:44-03:00\",\"retorno\":{\"cpf\":\"09297615619\",\"nome\":\"LUIS PAULO ANDRADE FREITAS\",\"nascimento\":\"13\\/04\\/1988\",\"idade\":32,\"sexo\":\"M\",\"nomedamae\":\"MARIA APARECIDA ANDRADE FREITAS\",\"signo\":\"N\\u00e3o Informado\",\"email\":[{\"email\":\"andradefreitas1988@hotmail.com\"}],\"enderecos\":[{\"logradouro\":\"RUA BENTO GONCALVES\",\"numero\":\"703\",\"bairro\":\"NOSSA SENHORA DAS GRACAS\",\"cidade\":\"UBERLANDIA\",\"uf\":\"MG\",\"cep\":\"38402004\"}],\"telefones\":[{\"ddd\":\"34\",\"telefone\":\"32573703\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32106073\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"999411654\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"991645219\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32139114\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32242007\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32916600\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32128050\",\"tipo\":null},{\"ddd\":\"34\",\"telefone\":\"32916809\",\"tipo\":null}]}},{\"id\":157,\"title\":\"Localiza por Documento\",\"token\":\"0a5822f5dce1bce56b0c3a04739137e4\",\"ctime\":\"2020-08-22T09:12:44-03:00\",\"retorno\":{\"cadastral\":{\"cpf\":\"09297615619\",\"nome\":\"LUIS PAULO ANDRADE FREITAS\",\"nascimento\":\"13\\/04\\/1988\",\"idade\":32,\"sexo\":\"Masculino\",\"nomedamae\":\"MARIA APARECIDA ANDRADE FREITAS\",\"codigocbo\":\"252105\",\"descricaocbo\":\"Administrador\",\"escolaridade\":\"ARIES\",\"dependentes\":0,\"estadocivil\":\"N\\u00e3o Informado\",\"renda\":\"1.102,00\"},\"email\":[{\"email\":\"andradefreitas590@gmail.com\"},{\"email\":\"andradefreitas1988@hotmail.com\"}],\"enderecos\":[{\"logradouro\":\"AV FLORIANO PEIXOTO\",\"bairro\":\"NOSSA SENHORA APARECIDA\",\"cidade\":\"UBERLANDIA\",\"uf\":\"MG\",\"cep\":\"38400698\"},{\"logradouro\":\"AV DOS FERREIRAS\",\"bairro\":\"JD CALIFORNIA\",\"cidade\":\"UBERLANDIA\",\"uf\":\"MG\",\"cep\":\"38406136\"},{\"logradouro\":\"AV DOS FERREIRAS\",\"bairro\":\"JD CALIFORNIA\",\"cidade\":\"UBERLANDIA\",\"uf\":\"MG\",\"cep\":\"38406136\"}],\"telefone\":[{\"ddd\":\"34\",\"numero\":\"991645219\"},{\"ddd\":\"34\",\"numero\":\"997902815\"},{\"ddd\":\"34\",\"numero\":\"999411654\"},{\"ddd\":\"34\",\"numero\":\"32916809\"},{\"ddd\":\"34\",\"numero\":\"32573703\"}]}}]},\"result\":1}";
		}
		if (tipoConsulta == "NOME") {
			return "{\"message\":\"Consulta realizada com sucesso\",\"consulta\":{\"token_grupoconsulta\":\"215F05DA615F070159421910558LN1PAST2ZIRJ4DVG5AGL8P9\",\"token\":\"215F4467C7C46021598318535TFDL8MX6ATGZR4LC6J68A5TAV\",\"parametro\":\"NOME\",\"parametro_valor\":\"LUIS PAULO ANDRADE FREITAS\",\"ctime\":\"2020-08-24T22:22:15-03:00\",\"titulo\":\"Infobusca\",\"usuario\":{\"token\":null,\"nome\":\"SOARES\",\"email\":\"atendimento1@ksiconsultas.com\",\"cpf\":\"898.079.875-04\",\"cliente\":{\"nome\":\"SOARES\",\"nomeFantasia\":\"\",\"razaoSocial\":\"\",\"cpf\":\"898.079.875-04\",\"cnpj\":null,\"cidade\":\"SALVADOR\",\"uf\":\"BA\"}},\"status\":1,\"resultados\":[{\"id\":118,\"title\":\"Localiza por Nome\",\"token\":\"a1a0b6557d18d25eb8722e86da1ea92d\",\"ctime\":\"2020-08-24T22:22:15-03:00\",\"retorno\":{\"status\":\"1\",\"mensagem\":\"Encontrado registros\",\"registrosPF\":[{\"documento\":\"09297615619\",\"nome\":\"LUIS PAULO ANDRADE FREITAS\",\"idade\":\"32\",\"bairro\":\"NOSSA SENHORA APARECIDA\",\"cidade\":\"UBERLANDIA\",\"estado\":\"MG\"}],\"registrosPJ\":null}}]},\"result\":1}";
		}
		return null;
	}
	
	public RetornoConsulta salvarRetorno(Long codigoTipoConsulta, Long codigoUsuario, String json, String cpf, String uf) throws Exception {
		try {
			RetornoConsulta retorno = new RetornoConsulta();
			retorno.setCodigoTipoConsulta(codigoTipoConsulta);
			retorno.setCodigoUsuario(codigoUsuario);
			retorno.setDataCadastro(new Date());
			retorno.setValor(cpf);
			retorno.setUf(uf);
			retorno.setJson(json);
			retornoConsultaRepository.save(retorno);
			return retorno;
		} catch (Exception e) {
			throw new Exception("Falha ao salvar retorno da consulta: " + e.getMessage());
		}		
	}
			
	public Parametro buscarParametro(Long codigoParametro) throws Exception {
		Optional<Parametro> parametro = parametroRepository.findById(codigoParametro);
		if (parametro != null && parametro.isPresent()) {
			return parametro.get();
		}else {
			throw new Exception("Parametro:" + codigoParametro + " não encontrado!");
		}		
	}
	
}
