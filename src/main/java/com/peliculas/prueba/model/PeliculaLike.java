package com.peliculas.prueba.model;

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
@Table(name = "pelicula_like")
public class PeliculaLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula_like")
	private Long idPeliculaLike;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula")
	private Pelicula idPelicula;

	public PeliculaLike(Long idPeliculaLike, String usuario, Date fecha, Pelicula idPelicula) {
		super();
		this.idPeliculaLike = idPeliculaLike;
		this.usuario = usuario;
		this.fecha = fecha;
		this.idPelicula = idPelicula;
	}

	public PeliculaLike() {
		super();
	}

	public Long getIdPeliculaLike() {
		return idPeliculaLike;
	}

	public void setIdPeliculaLike(Long idPeliculaLike) {
		this.idPeliculaLike = idPeliculaLike;
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

	public Pelicula getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Pelicula idPelicula) {
		this.idPelicula = idPelicula;
	}
	
}