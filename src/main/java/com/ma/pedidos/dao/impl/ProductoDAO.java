package com.ma.pedidos.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.ma.pedidos.dao.ICrudDAO;
import com.ma.pedidos.model.Producto;

@Component
public class ProductoDAO implements ICrudDAO<Producto, String> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void create(Producto entity) throws DataIntegrityViolationException {
		this.entityManager.persist(entity);
	}

	@Override
	public Producto get(String id) {
		Producto producto = this.entityManager.find(Producto.class, id);
		return producto;
	}
	@Transactional
	@Override
	public void update(Producto entity) {
		this.entityManager.merge(entity);
	}
	
	@Transactional
	@Override
	public void delete(Producto entity) {
		entityManager.remove(entity);
	}

}
