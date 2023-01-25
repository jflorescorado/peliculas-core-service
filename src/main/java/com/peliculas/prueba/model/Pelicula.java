package com.peliculas.prueba.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private Long idPelicula;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "imagen")
	private byte[] imagen;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "precio_venta")
	private BigDecimal precioVenta;
	
	@Column(name = "precio_alquiler")
	private BigDecimal precioAlquiler;
	
	@Column(name = "monto_restraso")
	private BigDecimal montoRetraso;
	
	@Column(name = "usuario_crea")
	private String usuarioCrea;
	
	@Column(name = "fec_crea")
	private Date fecCrea; 
	
	@Column(name = "usuario_modifica")
	private String usuarioModifica;
	
	@Column(name = "fec_modifica")
	private Date feModifica;
	
	@Column(name = "disponibilidad")
	private boolean disponibilidad;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "idPelicula")
	private Collection<PeliculaLike> likes;
	
	public Pelicula() {
		super();
	}

	public Pelicula(Long idPelicula, String titulo, byte[] imagen, String descripcion, int stock,
			BigDecimal precioVenta, BigDecimal precioAlquiler, BigDecimal montoRetraso, String usuarioCrea,
			Date fecCrea, String usuarioModifica, Date feModifica, boolean disponibilidad,
			Collection<PeliculaLike> likes) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precioVenta = precioVenta;
		this.precioAlquiler = precioAlquiler;
		this.montoRetraso = montoRetraso;
		this.usuarioCrea = usuarioCrea;
		this.fecCrea = fecCrea;
		this.usuarioModifica = usuarioModifica;
		this.feModifica = feModifica;
		this.disponibilidad = disponibilidad;
		this.likes = likes;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public BigDecimal getPrecioAlquiler() {
		return precioAlquiler;
	}

	public void setPrecioAlquiler(BigDecimal precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public BigDecimal getMontoRetraso() {
		return montoRetraso;
	}

	public void setMontoRetraso(BigDecimal montoRetraso) {
		this.montoRetraso = montoRetraso;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFecCrea() {
		return fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public String getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFeModifica() {
		return feModifica;
	}

	public void setFeModifica(Date feModifica) {
		this.feModifica = feModifica;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Collection<PeliculaLike> getLikes() {
		return likes;
	}

	public void setLikes(Collection<PeliculaLike> likes) {
		this.likes = likes;
	}

}