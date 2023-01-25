package com.peliculas.prueba.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.dto.FacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.model.DetalleAlquiler;
import com.peliculas.prueba.model.DetalleFactura;
import com.peliculas.prueba.model.Factura;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.model.PeliculaBitacora;
import com.peliculas.prueba.repository.repo.PeliculaRepo;

@Component("appUtilLocal")
public class AppUtil {

	@Autowired
	PeliculaRepo peliculaRepo;

	private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);

	public boolean ValidateDataPelicula(Pelicula pelicula) {
		boolean validate = true;
		try {
			// Valido titulo
			if (pelicula.getTitulo().isEmpty()) {
				validate = false;
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide el titulo de la pelicula");
			}

			// Valido Descripcion
			if (pelicula.getDescripcion().isEmpty()) {
				validate = false;
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide la descripcion de la pelicula");
			}

			// Valido precio de venta
			if (pelicula.getPrecioVenta() == null) {
				validate = false;
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide el precio de venta de la pelicula");
			}

			// Valido precio de renta
			if (pelicula.getPrecioAlquiler() == null) {
				validate = false;
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide la precio de renta de la pelicula");
			}

			// Valido el monto por dia de retraso
			if (pelicula.getMontoRetraso() == null) {
				validate = false;
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"Valide el monto a cobrar por dia de retraso de la pelicula");
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return validate;
	}

	public PeliculaBitacora saveDataPeliculaBitacora(Pelicula pelicula, Pelicula peliculaUpdate) {
		PeliculaBitacora response = new PeliculaBitacora();
		try {
			response.setFecha(new Date());

			// Valido id de la pelicula a crear bitacora
			if (pelicula.getIdPelicula() != null) {
				response.setIdPelicula(pelicula.getIdPelicula());
			} else if (peliculaUpdate.getIdPelicula() != null) {
				response.setIdPelicula(peliculaUpdate.getIdPelicula());
			}

			// Valido que titulo no sea null
			if (pelicula.getTitulo() != null) {
				response.setTitulo(pelicula.getTitulo());
			}
			// Valido que titulo nuevo no sea null
			if (peliculaUpdate.getTitulo() != null) {
				response.setTituloNuevo(peliculaUpdate.getTitulo());
			}

			// Valido que precio de venta no sea null
			if (pelicula.getPrecioVenta() != null) {
				response.setPrecioVenta(pelicula.getPrecioVenta());
			}

			// Valido que precio de venta nuevo no sea null
			if (peliculaUpdate.getPrecioVenta() != null) {
				response.setPrecioVentaNuevo(peliculaUpdate.getPrecioVenta());
			}

			// Valido que precio de renta no sea null
			if (pelicula.getPrecioAlquiler() != null) {
				response.setPrecioAlquiler(pelicula.getPrecioAlquiler());
			}

			// Valido que precio de renta nuevo no sea null
			if (peliculaUpdate.getPrecioAlquiler() != null) {
				response.setPrecioAlquilerNuevo(peliculaUpdate.getPrecioAlquiler());
			}

			// Valido el usuario que la modifica no sea null
			if (peliculaUpdate.getUsuarioModifica() != null) {
				response.setUsuario(peliculaUpdate.getUsuarioModifica());
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public DetalleFactura validateDetalleFactura(DetalleFacturaRequest detalleFacturaRequest) {
		DetalleFactura response = new DetalleFactura();
		Factura factura = new Factura();
		Pelicula pelicula = new Pelicula();
		try {
			// Busco por id de pelicula, si no la encuentra devolvera una excepcion
			pelicula = peliculaRepo.findById(detalleFacturaRequest.getIdPelicula());

			// Valido si tiene id
			if (detalleFacturaRequest.getIdDetalleFactura() != null) {
				response.setIdDetalleFactura(detalleFacturaRequest.getIdDetalleFactura());
			}

			response.setIdPelicula(detalleFacturaRequest.getIdPelicula());
			factura.setIdFactura(detalleFacturaRequest.getIdFactura());
			response.setIdFactura(factura);

			// Valido el estado
			if (detalleFacturaRequest.getEstado() == AppConstants.PURCHASE_STATE
					|| detalleFacturaRequest.getEstado() == AppConstants.RENTAL_STATE)
				response.setEstado(detalleFacturaRequest.getEstado());

			// Valido la cantidad
			if (detalleFacturaRequest.getCantidad() != 0) {
				response.setCantidad(detalleFacturaRequest.getCantidad());
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide la cantidad de la pelicula");
			}

			// Valido si es compra o si es renta
			if (detalleFacturaRequest.getEstado() == AppConstants.PURCHASE_STATE
					&& detalleFacturaRequest.getCantidad() < pelicula.getStock()) {
				// Asigno precio por unidad cuando es compra
				response.setPrecioUnidad(pelicula.getPrecioVenta());
				// Asigno subTotal (precio de venta x cantidad)
				response.setSubTotal(
						pelicula.getPrecioVenta().multiply(BigDecimal.valueOf(detalleFacturaRequest.getCantidad())));
			} else if (detalleFacturaRequest.getEstado() == AppConstants.RENTAL_STATE
					&& detalleFacturaRequest.getCantidad() < pelicula.getStock()) {

				// Asigno precio por unidad cuando es alquiler
				response.setPrecioUnidad(pelicula.getPrecioAlquiler());

				// Asigno subTotal (precio de alquiler x cantidad)
				response.setSubTotal(
						pelicula.getPrecioAlquiler().multiply(BigDecimal.valueOf(detalleFacturaRequest.getCantidad())));
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"Valide la cantidad, no puede ser mayor al stock de la pelicula");
			}

			response.setFecha(new Date());
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public Factura validateFactura(FacturaRequest facturaRequest) {
		Factura response = new Factura();
		try {
			// Valido si tiene id
			if (facturaRequest.getIdFactura() != null) {
				response.setIdFactura(facturaRequest.getIdFactura());
			}

			// Valido si usuario no es null
			if (facturaRequest.getUsuario() != null) {
				response.setUsuario(facturaRequest.getUsuario());
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide el usuario");
			}

			// Valido estado
			if (facturaRequest.getEstado() != null && facturaRequest.getEstado() == AppConstants.FLOW_STAR
					|| facturaRequest.getEstado() == AppConstants.FLOW_PENDIENT
					|| facturaRequest.getEstado() == AppConstants.FLOW_FINISHED) {
				response.setEstado(facturaRequest.getEstado());
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR, "No ha sido posible actualizar el estado");
			}

			response.setFecha(new Date());
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public Factura calculateData(Factura response) {
		int countCantidad = 0;
		BigDecimal total = null;
		try {
			for (DetalleFactura detalleFactura : response.getDetalleFacturasCollection()) {

				// Valido si el contador esta en 0 o ya tiene un valor
				if (countCantidad == 0) {
					countCantidad = detalleFactura.getCantidad();
				} else {
					// Realizo suma de valores
					countCantidad = countCantidad + detalleFactura.getCantidad();
				}

				// Valido si total es null o ya tiene un valor
				if (total == null) {
					total = detalleFactura.getSubTotal();
				} else {
					// Realizo suma de valores
					total = total.add(detalleFactura.getSubTotal());
				}
			}

			// Valido si contador es diferente a 0 y asigno valor a cantidad total de
			// peliculas
			if (countCantidad != 0) {
				response.setCantidadTotalPeliculas(countCantidad);
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"No ha sido posible calcular la cantidad de peliculas agregadas en la factura");
			}

			// Valido si total es diferente a null y asigno valor a total a pagar de
			// peliculas
			if (total != null) {
				response.setTotal(total);
			} else {
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"No ha sido posible calcular el total a pagar por las peliculas agregadas en la factura");
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public AlquilerPelicula validateAlquilerPelicula(Factura factura) {
		BigDecimal subTotal = null;
		Pelicula pelicula = null;
		AlquilerPelicula response = new AlquilerPelicula();
		DetalleAlquiler detalleListAlquiler = new DetalleAlquiler();
		List<DetalleAlquiler> detalleListAlquilers = new ArrayList<>();
		try {
			// Valido el id de factura
			if (factura.getIdFactura() == null) {
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"Ocurrio un error al crear registro para alquiler de la pelicula"
								+ " valide los detalles de su factura ID : " + factura.getIdFactura());
			} else {
				response.setIdFactura(factura.getIdFactura());
			}

			// Valido el usuario
			if (factura.getUsuario() == null) {
				throw new AppServiceException(AppConstants.CODE_ERROR, "Valide el usuario");
			} else {
				response.setUsuario(factura.getUsuario());
			}

			// Recorro los lista de los detalles de la factura
			for (DetalleFactura detalleFactura : factura.getDetalleFacturasCollection()) {

				// Valido el estado, tiene quer ser alquiler
				if (detalleFactura.getEstado() == AppConstants.RENTAL_STATE) {

					// Busco por id de pelicula, si no la encuentra devolvera una excepcion
					pelicula = peliculaRepo.findById(detalleFactura.getIdPelicula());

					// Valido el id del detalle de la factura
					if (detalleFactura.getIdDetalleFactura() == null) {
						throw new AppServiceException(AppConstants.CODE_ERROR,
								"Ocurrio un error al crear registro para alquiler de la pelicula ID : "
										+ detalleFactura.getIdPelicula() + " , valide los detalles de su factura ID : "
										+ factura.getIdFactura());
					} else {
						detalleListAlquiler.setIdDetalleFactura(detalleFactura.getIdDetalleFactura());
					}

					detalleListAlquiler.setIdPelicula(detalleFactura.getIdPelicula());

					// Valido la cantidad
					if (detalleFactura.getCantidad() == 0) {
						throw new AppServiceException(AppConstants.CODE_ERROR, "Valide la cantidad de la pelicula");
					} else {
						detalleListAlquiler.setCantidadPelicula(detalleFactura.getCantidad());
					}

					// Valido el monto por retraso de la pelicula
					if (pelicula.getMontoRetraso() == null) {
						throw new AppServiceException(AppConstants.CODE_ERROR,
								"Valide el monto por retraso de la pelicula");
					} else {
						detalleListAlquiler.setMontoRetrasoUnidad(pelicula.getMontoRetraso());
					}

					// Calculo el monto por retraso diario(cantidad x monto retraso por unidad)
					detalleListAlquiler.setMontoRetraso(detalleListAlquiler.getMontoRetraso()
							.multiply(BigDecimal.valueOf(detalleListAlquiler.getCantidadPelicula())));

					// Agrego a la lista de detalles de alquiler
					detalleListAlquilers.add(detalleListAlquiler);

					if (subTotal == null) {
						subTotal = detalleListAlquiler.getMontoRetraso();
					} else {
						subTotal = subTotal.add(detalleListAlquiler.getMontoRetraso());
					}
				}
			}

			// Valido que detalle de alquiler no esten vacios
			if (detalleListAlquiler != null) {

				// asigno el subtotal de todos lo detalles de alquiler
				response.setMontoRetrasoDia(subTotal);

				// Asigno la lista a la coleccion del objeto de alquiler peliculas
				response.setDetalleAlquilers(detalleListAlquilers);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public AlquilerPelicula calculateDataAlquiler(AlquilerPelicula response) {
		try {
			if (response.getFecDevolucionOriginal() != null && response.getFecDevolucion() != null) {
				int days = response.getFecDevolucion().getDay() - response.getFecDevolucionOriginal().getDay();
				response.setDiasRetraso(days);

				response.setCobroRetraso(
						response.getMontoRetrasoDia().multiply(BigDecimal.valueOf(response.getDiasRetraso())));
			}

		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}
}

