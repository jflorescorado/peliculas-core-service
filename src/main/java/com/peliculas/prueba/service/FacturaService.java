package com.peliculas.prueba.service;

import java.util.List;
import com.peliculas.prueba.dto.FacturaRequest;
import com.peliculas.prueba.model.Factura;

public interface FacturaService {

	public Factura saveFactura(FacturaRequest facturaRequest);
	
	public Factura updateFactura(FacturaRequest facturaRequest);
	
	public Factura findById(Long id);
	
	public List<Factura> findByUsuario(String usuario);
	
	public List<Factura> findAllFactura();
	
	public void deleteFactura(Long id);
	
}
