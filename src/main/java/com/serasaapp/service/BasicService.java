package com.serasaapp.service;

import org.hibernate.service.spi.ServiceException;

public interface BasicService <T,V> {
	
	public T salvar(T object) throws ServiceException;	
	public T atualizar(T object) throws ServiceException;	
	public T excluir(T object) throws ServiceException;

}
