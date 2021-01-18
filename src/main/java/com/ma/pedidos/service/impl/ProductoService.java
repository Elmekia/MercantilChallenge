package com.ma.pedidos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ma.pedidos.dao.ICrudDAO;
import com.ma.pedidos.model.Producto;
import com.ma.pedidos.service.ICrudService;

@Service("productoService")
public class ProductoService implements ICrudService<Producto, String> {
	
	
	@Autowired
	private ICrudDAO<Producto, String> productoDAO;
	
	@Override
	public void create(Producto entity) throws DataIntegrityViolationException {
		this.productoDAO.create(entity);
	}

	@Override
	public Producto get(String id) {
		return this.productoDAO.get(id);
	}

	@Override
	public void update(String id, Producto entity) {
		entity.setId(id);
		this.productoDAO.update(entity);
	}

	@Override
	public void delete(String id) {
		this.productoDAO.delete(this.get(id));
	}

	@Override
	public boolean validate(Producto entity) {
		return (entity.getId() != null && !entity.getId().trim().equals(""));
	}

}
