package com.peliculas.prueba.service;

import java.util.List;

import com.peliculas.prueba.model.AlquilerPelicula;

public interface AlquilerPeliculaService {
	
	public AlquilerPelicula updateAlquilerBitacora(AlquilerPelicula alquilerBitacora);
	
	public List<AlquilerPelicula> findByUsuario(String usuario);
	
	public List<AlquilerPelicula> findAllAlquilerBitacora();
	
	public void deletefindAlquilerBitacora(Long id);
}

