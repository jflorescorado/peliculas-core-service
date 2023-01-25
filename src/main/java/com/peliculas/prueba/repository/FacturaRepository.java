package com.peliculas.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{

	public List<Factura> findByUsuario(String usuario);
	
}
