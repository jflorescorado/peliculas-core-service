package com.peliculas.prueba.model;

import java.math.BigDecimal;

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
@Table(name = "detalle_alquiler")
public class DetalleAlquiler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_aquiler")
	private Long idDetalleAlquiler;
	
	@Column(name = "id_detalle_factura")
	private Long idDetalleFactura;
	
	@Column(name = "id_pelicula")
	private Long idPelicula;
	
	@Column(name = "monto_restraso_unidad")
	private BigDecimal montoRetrasoUnidad;
	
	@Column(name = "cantidad_pelicula")
	private int cantidadPelicula;
	
	@Column(name = "monto_retraso")
	private BigDecimal montoRetraso;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_alquiler_pelicula", referencedColumnName = "id_alquiler_pelicula")
	private AlquilerPelicula idAlquilerPelicula;

	public DetalleAlquiler() {
		super();
	}

	public DetalleAlquiler(Long idDetalleAlquiler, Long idDetalleFactura, Long idPelicula,
			BigDecimal montoRetrasoUnidad, int cantidadPelicula, BigDecimal montoRetraso,
			AlquilerPelicula idAlquilerPelicula) {
		super();
		this.idDetalleAlquiler = idDetalleAlquiler;
		this.idDetalleFactura = idDetalleFactura;
		this.idPelicula = idPelicula;
		this.montoRetrasoUnidad = montoRetrasoUnidad;
		this.cantidadPelicula = cantidadPelicula;
		this.montoRetraso = montoRetraso;
		this.idAlquilerPelicula = idAlquilerPelicula;
	}

	public Long getIdDetalleAlquiler() {
		return idDetalleAlquiler;
	}

	public void setIdDetalleAlquiler(Long idDetalleAlquiler) {
		this.idDetalleAlquiler = idDetalleAlquiler;
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

	public BigDecimal getMontoRetrasoUnidad() {
		return montoRetrasoUnidad;
	}

	public void setMontoRetrasoUnidad(BigDecimal montoRetrasoUnidad) {
		this.montoRetrasoUnidad = montoRetrasoUnidad;
	}

	public int getCantidadPelicula() {
		return cantidadPelicula;
	}

	public void setCantidadPelicula(int cantidadPelicula) {
		this.cantidadPelicula = cantidadPelicula;
	}

	public BigDecimal getMontoRetraso() {
		return montoRetraso;
	}

	public void setMontoRetraso(BigDecimal montoRetraso) {
		this.montoRetraso = montoRetraso;
	}

	public AlquilerPelicula getIdAlquilerPelicula() {
		return idAlquilerPelicula;
	}

	public void setIdAlquilerPelicula(AlquilerPelicula idAlquilerPelicula) {
		this.idAlquilerPelicula = idAlquilerPelicula;
	}

}

