package com.peliculas.prueba.service;

import com.peliculas.prueba.dto.PeliculaLikeRequest;
import com.peliculas.prueba.model.PeliculaLike;

public interface PeliculaLikeService {

	public PeliculaLike savePeliculaLike(PeliculaLikeRequest peliculaLikeRequest);
}
