package com.ma.pedidos.dto;

public class PedidoDetalleDTO {
	
	private String producto;
    private int cantidad;
    
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "{ producto= " + this.producto + ", cantidad= " + this.cantidad + " }";
	}
}
