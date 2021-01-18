package com.ma.pedidos.dto;

import java.util.List;

public class PedidoDTO {

	private String direccion;
	private String email;
	private String telefono;
	private String horario;
	private List<PedidoDetalleDTO>detalle;
	
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
	public List<PedidoDetalleDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidoDetalleDTO> detalle) {
		this.detalle = detalle;
	}
	
	@Override
	public String toString() {
		return "{direccion= " + this.direccion + ", email= " + this.email + ", telefono= " + this.telefono + ", horario= " + this.horario + ", detalle= { " + detalle.toString() + " }" ;
	}
}
