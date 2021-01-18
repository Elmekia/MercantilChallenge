package com.ma.pedidos.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ma.pedidos.dao.IPedidoDAO;
import com.ma.pedidos.dto.PedidoDTO;
import com.ma.pedidos.dto.PedidoDetalleDTO;
import com.ma.pedidos.dto.PedidoResponseDTO;
import com.ma.pedidos.model.PedidoCabecera;
import com.ma.pedidos.model.PedidoDetalle;
import com.ma.pedidos.model.Producto;
import com.ma.pedidos.service.ICrudService;
import com.ma.pedidos.service.IPedidoService;
import com.ma.pedidos.utils.Constants;
import com.ma.pedidos.utils.GlobalFunctions;
import com.ma.pedidos.utils.response.ResponseError;

@Service("pedidoService")
public class PedidoService implements IPedidoService {
	
	@Value("${CANTIDAD_DESCUENTO:3}")
    private int cantidadDescuento;
	
	@Value("${PORCENTAJE_DESCUENTO:0.3}")
    private float porcentajeDescuento;
	

	@Autowired
	IPedidoDAO pedidoDAO;
	
	@Autowired
	ICrudService<Producto,String> productoService;
	
	@Override
	public PedidoResponseDTO create(PedidoDTO pedido) {
		PedidoCabecera pedidoCabecera = new PedidoCabecera(pedido, GlobalFunctions.getDate(), Constants.ESTADO_INICIAL_PEDIDO);
		List<PedidoDetalle> pedidoDetalleList = new ArrayList<PedidoDetalle>();
		pedido.getDetalle().forEach(detalle -> {
			Producto producto = productoService.get(detalle.getProducto());
			PedidoDetalle pedidoDetalle = new PedidoDetalle(pedidoCabecera, producto, detalle);
			pedidoDetalleList.add(pedidoDetalle);
		});
		pedidoCabecera.setDetalle(pedidoDetalleList);
		this.calcularDescuento(pedidoCabecera);
		this.pedidoDAO.create(pedidoCabecera);
		return new PedidoResponseDTO(pedidoCabecera);
	}

	@Override
	public List<PedidoResponseDTO> findByFecha(String fecha) {
		LocalDate fechaPedido = GlobalFunctions.stringDateToLocal(fecha);
		List<PedidoCabecera> pedidos = this.pedidoDAO.findByFecha(fechaPedido);
		List<PedidoResponseDTO> pedidosResponse = new ArrayList<>();
		pedidos.forEach(pedido -> {
			pedidosResponse.add(new PedidoResponseDTO(pedido));
		});
		return pedidosResponse ;
	}
	
	private void calcularDescuento(PedidoCabecera cabecera) {
		
		Integer sum = cabecera.getDetalle().stream().map(x -> x.getCantidad()).reduce(0, Integer::sum);
		
		Float total = cabecera.getDetalle().stream().map(x -> x.getPrecioUnitario() * x.getCantidad()).reduce((float) 0, Float::sum);
		cabecera.setTotal(total);
		
		if(sum > this.cantidadDescuento) {
			cabecera.setDescuento(true);
			cabecera.setTotal(total - total * this.porcentajeDescuento);
		}
	}

	@Override
	public List<ResponseError> validate(PedidoDTO pedido) {
		
		List<ResponseError> errores = new ArrayList<ResponseError>();
		
		if(pedido.getDireccion() == null || pedido.getDireccion().trim().equals(""))
			errores.add(new ResponseError(Constants.MSG_PEDIDO_SIN_DIRECCION));
		
		Optional<PedidoDetalleDTO> detalle =  pedido.getDetalle().stream().filter(obj -> obj.getCantidad() < 1).findFirst();
		if(detalle.isPresent())
			errores.add(new ResponseError(Constants.MSG_PEDIDO_SIN_CANTIDAD));
		
		return errores;
	}

}
