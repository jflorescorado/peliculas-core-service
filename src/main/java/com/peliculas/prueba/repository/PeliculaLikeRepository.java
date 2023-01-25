package com.peliculas.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.PeliculaLike;

@Repository
public interface PeliculaLikeRepository extends JpaRepository<PeliculaLike, Long>{

	public void deleteByIdPelicula(Long idPelicula);
}