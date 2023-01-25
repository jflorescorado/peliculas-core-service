package com.peliculas.prueba.service;

import java.util.List;

import com.peliculas.prueba.model.PeliculaBitacora;

public interface PeliculaBitacoraService {

	public List<PeliculaBitacora> findByIdPelicula(Long idPelicula);
}
