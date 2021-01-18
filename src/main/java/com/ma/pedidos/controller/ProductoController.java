package com.ma.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.model.Producto;
import com.ma.pedidos.service.ICrudService;
import com.ma.pedidos.utils.Constants;

@RestController()
@RequestMapping("productos")
public class ProductoController extends CrudController<Producto,String> {
	
	
	@Autowired
	private ICrudService<Producto, String> productoService;
	
	@Override
	protected ICrudService<Producto, String> getService() {
		return this.productoService;
	}

	@Override
	protected String getNotFoundMessage() {
		return Constants.MSG_PRODUCTO_NOT_FOUND;
	}

}
