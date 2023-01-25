package com.peliculas.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.AlquilerPelicula;

@Repository
public interface AlquilerPeliculaRepository extends JpaRepository<AlquilerPelicula, Long>{

	public List<AlquilerPelicula> findByUsuario(String usuario);
	
	public List<AlquilerPelicula> findByEstado(String estado);
}
