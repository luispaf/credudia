package com.serasaapp.controller;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.serasaapp.service.BasicService;

public abstract class BasicController<T,ID>{

public abstract BasicService<T,ID> getService();
	
	public abstract JpaRepository<T, ID>  getRepository();
	
	@GetMapping(path="/{id}")
	public T buscarPorId(@PathVariable("id") ID id){
		return (T) getRepository().findById(id).get();
	}
	
	@GetMapping(path="/todos")
	public List<T> buscarTodos(){
		return  getRepository().findAll();
	}
	
	@GetMapping(path="/todosPaginado")
	public List<T> buscarTodosPaginado(@RequestParam("page") int page,@RequestParam("size") int size){
		Pageable pageable = PageRequest.of(page, size);
		return  getRepository().findAll(pageable).getContent();
	}
	
	@PostMapping(path="/salvar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public T salvar(@RequestBody T object) throws ServiceException{
		return (T) getService().salvar(object);
	}
	
	@PutMapping(path="/atualizar")
	public T atualizar(@RequestBody T object) throws ServiceException{
		return (T) getService().atualizar(object);
	}
	
	@PostMapping(path="/excluir")
	public T excluir(@RequestBody T object) throws ServiceException{
		return (T) getService().excluir(object);
	}
}
