package com.peliculas.prueba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
	
	@Query(value = "SELECT p FROM Pelicula p WHERE p.titulo LIKE %:titulo%")
	public List<Pelicula> findByListTitulo(@Param("titulo") String titulo);
	
	public Pelicula findByTitulo(String titulo);

	public Page<Pelicula> findByDisponibilidad(boolean disponibilidad, Pageable pageable);
	
}


