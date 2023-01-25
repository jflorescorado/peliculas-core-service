package com.peliculas.prueba.service;

import java.util.List;

import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.model.DetalleFactura;

public interface DetalleFacturaService {

	public DetalleFactura saveDetalleFactura(DetalleFacturaRequest detalleFacturaRequest); 
	
	public DetalleFactura updateDetalleFactura(DetalleFacturaRequest detalleFacturaRequest); 
	
	public List<DetalleFactura> findByIdFactura(Long idFactura);
	
	public void deleteDetalleFactura(Long id);
}
