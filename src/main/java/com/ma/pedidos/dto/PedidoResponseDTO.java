package com.ma.pedidos.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ma.pedidos.model.PedidoCabecera;

public class PedidoResponseDTO {
	
	private LocalDate fecha;
	private String direccion;
	private String email;
	private String telefono;
	private String horario;
	private List<DetalleResposneDTO> detalle;
	private float total;
	private boolean descuento;
	private String estado;

	public PedidoResponseDTO(PedidoCabecera pedidoCabecera) {
		
		this.fecha = pedidoCabecera.getFecha();
		this.direccion = pedidoCabecera.getDireccion();
		this.email = pedidoCabecera.getEmail();
		this.telefono = pedidoCabecera.getTelefono();
		this.horario = pedidoCabecera.getHorario();
		this.total = pedidoCabecera.getTotal();
		this.descuento = pedidoCabecera.isDescuento();
		this.estado = pedidoCabecera.getEstado();
		this.detalle = new ArrayList<>();
		pedidoCabecera.getDetalle().forEach(detalle -> {
			this.detalle.add(new DetalleResposneDTO(detalle));
		});
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public List<DetalleResposneDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleResposneDTO> detalle) {
		this.detalle = detalle;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public boolean isDescuento() {
		return descuento;
	}
	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
