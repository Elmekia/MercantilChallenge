package com.ma.pedidos.service;


import java.util.List;

import com.ma.pedidos.dto.PedidoDTO;
import com.ma.pedidos.dto.PedidoResponseDTO;
import com.ma.pedidos.utils.response.ResponseError;

public interface IPedidoService {

	public PedidoResponseDTO create(PedidoDTO pedido);

	public List<PedidoResponseDTO> findByFecha(String fecha);

	public List<ResponseError> validate(PedidoDTO pedido);

}
