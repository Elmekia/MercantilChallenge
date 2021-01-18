package com.ma.pedidos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	private String id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "descripcion_corta", nullable = false, length = 50)
	private String descripcionCorta;
	
	@Column(name = "descripcion_larga", nullable = false, length = 200)
	private String descripcionLarga;
	
	@Column(name = "precio_unitario", nullable = false, length = 16)
    private float precioUnitario;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	@Override
	public String toString() {
		return "producto{ id= " + this.id + ", nombre= " + this.nombre + ", descripcionCorta= " + this.descripcionCorta + ", descripcionLarga=" + this.descripcionLarga + ", precioUnitario= " + this.precioUnitario + " }";
	}
	
}
