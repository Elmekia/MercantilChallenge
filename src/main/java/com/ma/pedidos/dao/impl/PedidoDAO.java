package com.ma.pedidos.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.ma.pedidos.dao.IPedidoDAO;
import com.ma.pedidos.model.PedidoCabecera;

@Component
public class PedidoDAO implements IPedidoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void create(PedidoCabecera pedido) {
		
		this.entityManager.persist(pedido);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PedidoCabecera> findByFecha(LocalDate fecha) {
		List<PedidoCabecera> pedidos = null;
	    Query query = entityManager.createQuery("SELECT u FROM PedidoCabecera u WHERE u.fecha=:fecha");
	    query.setParameter("fecha", fecha);
	    pedidos = (List<PedidoCabecera>) query.getResultList();
	    return pedidos;
	}

}
