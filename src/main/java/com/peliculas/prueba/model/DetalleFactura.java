package com.peliculas.prueba.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_factura")
	private Long idDetalleFactura;
	
	@Column(name = "id_pelicula")
	private Long idPelicula;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precio_unidad")
	private BigDecimal precioUnidad;
	
	@Column(name = "sub_total")
	private BigDecimal subTotal;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "estado", length = 1)
	private String estado;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
	private Factura idFactura;

	public DetalleFactura() {
		super();
	}

	public DetalleFactura(Long idDetalleFactura, Long idPelicula, int cantidad, BigDecimal precioUnidad,
			BigDecimal subTotal, Date fecha, String estado, Factura idFactura) {
		super();
		this.idDetalleFactura = idDetalleFactura;
		this.idPelicula = idPelicula;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.subTotal = subTotal;
		this.fecha = fecha;
		this.estado = estado;
		this.idFactura = idFactura;
	}

	public Long getIdDetalleFactura() {
		return idDetalleFactura;
	}

	public void setIdDetalleFactura(Long idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Factura getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}

	public BigDecimal getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(BigDecimal precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

}

