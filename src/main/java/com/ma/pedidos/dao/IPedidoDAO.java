package com.ma.pedidos.dao;

import java.time.LocalDate;
import java.util.List;

import com.ma.pedidos.model.PedidoCabecera;

public interface IPedidoDAO {

	public void create(PedidoCabecera pedido);
	public List<PedidoCabecera> findByFecha(LocalDate fecha);
}
