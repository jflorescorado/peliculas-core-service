package com.peliculas.prueba.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.peliculas.prueba.model.Pelicula;

public interface PeliculaService {

	public Pelicula savePelicula(Pelicula pelicula);
	
	public Pelicula updatePelicula(Pelicula peliculaUpdate);
	
	public Pelicula findById(Long id);
	
	public List<Pelicula> findByTitulo(String titulo);
	
	public Page<Pelicula> findByDisponibilidad(boolean disponibilidad, Pageable pageable);
	
	public void deletePelicula(Long id);
}
