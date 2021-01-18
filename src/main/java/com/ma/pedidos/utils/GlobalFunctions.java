package com.ma.pedidos.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ma.pedidos.model.PedidoDetalle;

public class GlobalFunctions {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private GlobalFunctions() {
	
	}
	
	public static LocalDate getDate() {
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return LocalDate.parse(fecha);
	}
	
	public static LocalDate stringDateToLocal(String fecha) {
		return LocalDate.parse(fecha);
	}
	
	public static float calcularPrecio(PedidoDetalle pedido) {
		return pedido.getPrecioUnitario() * pedido.getCantidad();
	}
		
}
