package com.ma.pedidos.service;

public interface ICrudService<T,K> {

	public void create(T entity);
	public T get(K id);
	public void update(K id, T entity);
	public void delete(K id);
	public boolean validate(T entity);
}
