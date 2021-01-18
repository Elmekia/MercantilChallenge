package com.ma.pedidos.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ma.pedidos.dto.PedidoDTO;

@Entity
@Table(name="pedidos_cabecera")
public class PedidoCabecera {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	 @Column(name = "direccion", nullable = false, length = 300)
	private String direccion;
	 
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "telefono", nullable = false, length = 25)
	private String telefono;
	
	@Column(name = "horario", nullable = false, length = 10)
	private String horario;
	
	@JsonSerialize(using= ToStringSerializer.class)
	private LocalDate fecha;
	
	@Column(name = "monto_total", nullable = false, length = 16)
	private float total;
	
	@Column(name = "aplico_descuento", nullable = false)
	private boolean descuento;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@OneToMany(mappedBy="pedidoCabecera" , cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} , fetch=FetchType.LAZY, orphanRemoval=true)
    private List<PedidoDetalle> detalle;
	
	
	public PedidoCabecera() {
		
	}
	
	public PedidoCabecera(PedidoDTO pedidoDTO, LocalDate fecha, String estado) {
		this.direccion = pedidoDTO.getDireccion();
		this.email = pedidoDTO.getEmail();
		this.telefono = pedidoDTO.getTelefono();
		this.horario = pedidoDTO.getHorario();
		this.fecha = fecha;
		this.estado = estado;
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
	
	public List<PedidoDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}
}
