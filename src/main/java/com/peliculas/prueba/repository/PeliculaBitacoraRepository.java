package com.peliculas.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.PeliculaBitacora;

@Repository
public interface PeliculaBitacoraRepository extends JpaRepository<PeliculaBitacora, Long>{

	public List<PeliculaBitacora> findByIdPelicula(Long idPelicula);
	
	public void deleteByIdPelicula(Long idPelicula);
}

