package com.ma.pedidos.dto;

import com.ma.pedidos.model.PedidoDetalle;

public class DetalleResposneDTO {
	
	private String producto;
	private String nombre; 
    private int cantidad;
    private float importe;
	
    public DetalleResposneDTO(PedidoDetalle detalle) {
		this.producto = detalle.getProducto().getId();
		this.nombre = detalle.getProducto().getNombre();
		this.cantidad = detalle.getCantidad();
		this.importe = detalle.getPrecioUnitario();
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
}
