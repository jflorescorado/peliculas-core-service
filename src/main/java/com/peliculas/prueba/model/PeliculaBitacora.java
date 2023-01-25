package com.peliculas.prueba.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula_bitacora")
public class PeliculaBitacora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula_bitacora")
	private Long idPeliculaBitacora;
	
	@Column(name = "id_pelicula")
	private Long idPelicula;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "titulo_nuevo")
	private String tituloNuevo;
	
	@Column(name = "precio_venta")
	private BigDecimal precioVenta;
	
	@Column(name = "precio_venta_nuevo")
	private BigDecimal precioVentaNuevo;
	
	@Column(name = "precio_alquiler")
	private BigDecimal precioAlquiler;
	
	@Column(name = "precio_alquiler_nuevo")
	private BigDecimal precioAlquilerNuevo;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "fecha")
	private Date fecha;

	public PeliculaBitacora() {
		super();
	}

	public PeliculaBitacora(Long idPeliculaBitacora, Long idPelicula, String titulo, String tituloNuevo,
			BigDecimal precioVenta, BigDecimal precioVentaNuevo, BigDecimal precioAlquiler,
			BigDecimal precioAlquilerNuevo, String usuario, Date fecha) {
		super();
		this.idPeliculaBitacora = idPeliculaBitacora;
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.tituloNuevo = tituloNuevo;
		this.precioVenta = precioVenta;
		this.precioVentaNuevo = precioVentaNuevo;
		this.precioAlquiler = precioAlquiler;
		this.precioAlquilerNuevo = precioAlquilerNuevo;
		this.usuario = usuario;
		this.fecha = fecha;
	}

	public Long getIdPeliculaBitacora() {
		return idPeliculaBitacora;
	}

	public void setIdPeliculaBitacora(Long idPeliculaBitacora) {
		this.idPeliculaBitacora = idPeliculaBitacora;
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloNuevo() {
		return tituloNuevo;
	}

	public void setTituloNuevo(String tituloNuevo) {
		this.tituloNuevo = tituloNuevo;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getPrecioVentaNuevo() {
		return precioVentaNuevo;
	}

	public void setPrecioVentaNuevo(BigDecimal precioVentaNuevo) {
		this.precioVentaNuevo = precioVentaNuevo;
	}

	public BigDecimal getPrecioAlquiler() {
		return precioAlquiler;
	}

	public void setPrecioAlquiler(BigDecimal precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public BigDecimal getPrecioAlquilerNuevo() {
		return precioAlquilerNuevo;
	}

	public void setPrecioAlquilerNuevo(BigDecimal precioAlquilerNuevo) {
		this.precioAlquilerNuevo = precioAlquilerNuevo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
