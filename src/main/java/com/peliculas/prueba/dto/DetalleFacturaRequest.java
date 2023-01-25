package com.peliculas.prueba.dto;

public class DetalleFacturaRequest {
	
	private Long idDetalleFactura;

	private Long idPelicula;

	private int cantidad;

	private String estado;

	private Long idFactura;

	public DetalleFacturaRequest(Long idDetalleFactura, Long idPelicula, int cantidad, String estado, Long idFactura) {
		super();
		this.idDetalleFactura = idDetalleFactura;
		this.idPelicula = idPelicula;
		this.cantidad = cantidad;
		this.estado = estado;
		this.idFactura = idFactura;
	}

	public DetalleFacturaRequest() {
		super();
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getIdDetalleFactura() {
		return idDetalleFactura;
	}

	public void setIdDetalleFactura(Long idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}

}
