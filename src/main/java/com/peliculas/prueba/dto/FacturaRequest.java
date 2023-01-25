package com.peliculas.prueba.dto;

import java.util.List;

public class FacturaRequest {

	private Long idFactura;
	
	private String usuario;
	
	private String estado;
	
	private List<DetalleFacturaRequest> detallesFactura;
	
	public FacturaRequest() {
		super();
	}

	public FacturaRequest(Long idFactura, String usuario, String estado, List<DetalleFacturaRequest> detallesFactura) {
		super();
		this.idFactura = idFactura;
		this.usuario = usuario;
		this.estado = estado;
		this.detallesFactura = detallesFactura;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleFacturaRequest> getDetallesFactura() {
		return detallesFactura;
	}

	public void setDetallesFactura(List<DetalleFacturaRequest> detallesFactura) {
		this.detallesFactura = detallesFactura;
	}
	
}

