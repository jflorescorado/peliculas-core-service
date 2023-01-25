package com.peliculas.prueba.dto;

public class PeliculaLikeRequest {
	
	private String usuaario;

	private Long idPelicula;

	public PeliculaLikeRequest() {
		super();
	}

	public String getUsuaario() {
		return usuaario;
	}

	public void setUsuaario(String usuaario) {
		this.usuaario = usuaario;
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

}
