package com.ma.pedidos.dao;

public interface ICrudDAO <T,K> {

	public void create(T entity);
	public T get(K id);
	public void update(T entity);
	public void delete(T entity);
}
