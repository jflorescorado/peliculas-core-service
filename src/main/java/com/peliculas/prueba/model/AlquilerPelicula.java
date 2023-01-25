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
@Table(name = "alquiler_pelicula")
public class AlquilerPelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alquiler_pelicula")
	private Long idAlquilerPelicula;
	
	@Column(name = "id_factura")
	private Long idFactura;
	
	@Column(name = "usuario")
	private String usuario;
	
	@Column(name = "fec_retira")
	private Date fecRetira;
	
	@Column(name = "fec_devolucion_original")
	private Date fecDevolucionOriginal;
	
	@Column(name = "fec_devolucion")
	private Date fecDevolucion;
	
	@Column(name = "monto_retraso_dia")
	private BigDecimal montoRetrasoDia;
	
	@Column(name = "dias_retraso")
	private int diasRetraso;
	
	@Column(name = "cobro_retraso")
	private BigDecimal cobroRetraso;
	
	@Column(name = "estado", length = 1)
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "idAlquilerPelicula")
	private Collection<DetalleAlquiler> detalleAlquilers;

	public AlquilerPelicula() {
		super();
	}

	public AlquilerPelicula(Long idAlquilerPelicula, Long idFactura, String usuario, Date fecRetira,
			Date fecDevolucionOriginal, Date fecDevolucion, BigDecimal montoRetrasoDia, int diasRetraso,
			BigDecimal cobroRetraso, String estado, Collection<DetalleAlquiler> detalleAlquilers) {
		super();
		this.idAlquilerPelicula = idAlquilerPelicula;
		this.idFactura = idFactura;
		this.usuario = usuario;
		this.fecRetira = fecRetira;
		this.fecDevolucionOriginal = fecDevolucionOriginal;
		this.fecDevolucion = fecDevolucion;
		this.montoRetrasoDia = montoRetrasoDia;
		this.diasRetraso = diasRetraso;
		this.cobroRetraso = cobroRetraso;
		this.estado = estado;
		this.detalleAlquilers = detalleAlquilers;
	}

	public Long getIdAlquilerPelicula() {
		return idAlquilerPelicula;
	}

	public void setIdAlquilerPelicula(Long idAlquilerPelicula) {
		this.idAlquilerPelicula = idAlquilerPelicula;
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

	public Date getFecRetira() {
		return fecRetira;
	}

	public void setFecRetira(Date fecRetira) {
		this.fecRetira = fecRetira;
	}

	public Date getFecDevolucion() {
		return fecDevolucion;
	}

	public void setFecDevolucion(Date fecDevolucion) {
		this.fecDevolucion = fecDevolucion;
	}

	public int getDiasRetraso() {
		return diasRetraso;
	}

	public void setDiasRetraso(int diasRetraso) {
		this.diasRetraso = diasRetraso;
	}

	public BigDecimal getCobroRetraso() {
		return cobroRetraso;
	}

	public void setCobroRetraso(BigDecimal cobroRetraso) {
		this.cobroRetraso = cobroRetraso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Collection<DetalleAlquiler> getDetalleAlquilers() {
		return detalleAlquilers;
	}

	public void setDetalleAlquilers(Collection<DetalleAlquiler> detalleAlquilers) {
		this.detalleAlquilers = detalleAlquilers;
	}

	public Date getFecDevolucionOriginal() {
		return fecDevolucionOriginal;
	}

	public void setFecDevolucionOriginal(Date fecDevolucionOriginal) {
		this.fecDevolucionOriginal = fecDevolucionOriginal;
	}

	public BigDecimal getMontoRetrasoDia() {
		return montoRetrasoDia;
	}

	public void setMontoRetrasoDia(BigDecimal montoRetrasoDia) {
		this.montoRetrasoDia = montoRetrasoDia;
	}

	
}
