package com.ma.pedidos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ma.pedidos.dto.PedidoDetalleDTO;

@Entity
@Table(name="pedidos_detalle")
public class PedidoDetalle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_pedido", nullable = false)
	private PedidoCabecera pedidoCabecera;
	
	@ManyToOne
    @JoinColumn(name="id_producto", nullable = false)
	private Producto producto;
	
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	@Column(name = "precio_unitario", nullable = false)
	private float precioUnitario;
	
	public PedidoDetalle() {
		
	}
	
	public PedidoDetalle(PedidoCabecera pedidoCabecera, Producto producto, PedidoDetalleDTO detalle) {
		this.pedidoCabecera = pedidoCabecera;
		this.producto = producto;
		this.precioUnitario = producto.getPrecioUnitario();
		this.cantidad = detalle.getCantidad();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
