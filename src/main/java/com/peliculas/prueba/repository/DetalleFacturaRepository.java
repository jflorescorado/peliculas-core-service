package com.peliculas.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.peliculas.prueba.model.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long>{

	@Query("SELECT df FROM DetalleFactura df WHERE df.idFactura = :idFactura")
	public List<DetalleFactura> findByIdFactura(@Param("idFactura") Long idFactura);
	
}
