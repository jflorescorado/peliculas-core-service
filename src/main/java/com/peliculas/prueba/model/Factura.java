package com.peliculas.prueba.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private Long idFactura;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "cantidad_total_peliculas")
	private int cantidadTotalPeliculas;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "total")
	private BigDecimal total;
	
	@Column(name = "estado", length = 1)
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "idFactura")
	private Collection<DetalleFactura> detalleFacturasCollection;

	public Factura() {
		super();
	}

	public Factura(Long idFactura, String usuario, int cantidadTotalPeliculas, Date fecha, BigDecimal total,
			String estado, Collection<DetalleFactura> detalleFacturasCollection) {
		super();
		this.idFactura = idFactura;
		this.usuario = usuario;
		this.cantidadTotalPeliculas = cantidadTotalPeliculas;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.detalleFacturasCollection = detalleFacturasCollection;
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

	public int getCantidadTotalPeliculas() {
		return cantidadTotalPeliculas;
	}

	public void setCantidadTotalPeliculas(int cantidadTotalPeliculas) {
		this.cantidadTotalPeliculas = cantidadTotalPeliculas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Collection<DetalleFactura> getDetalleFacturasCollection() {
		return detalleFacturasCollection;
	}

	public void setDetalleFacturasCollection(Collection<DetalleFactura> detalleFacturasCollection) {
		this.detalleFacturasCollection = detalleFacturasCollection;
	}
	
}
