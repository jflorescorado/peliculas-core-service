package com.peliculas.prueba.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.dto.FacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.model.Factura;
import com.peliculas.prueba.repository.repo.AlquilerPeliculaRepo;
import com.peliculas.prueba.repository.repo.FacturaRepo;
import com.peliculas.prueba.service.FacturaService;
import com.peliculas.prueba.util.AppUtil;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	FacturaRepo facturaRepo;

	@Autowired
	AppUtil util;
	
	@Autowired
	AlquilerPeliculaRepo alquilerPeliculaRepo;

	private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);

	@Override
	public Factura saveFactura(FacturaRequest facturaRequest) {
		Factura response = null;
		try {

			// Valido si facturaRequest no es null y que no contenga detalles de factura
			if (facturaRequest != null && facturaRequest.getDetallesFactura().isEmpty()) {

				// Valido, asigno los datos y ejecuto el metodo para guardar
				response = facturaRepo.saveFactura(util.validateFactura(facturaRequest));

				// Valido si facturaRequest no es null y que contenga detalles de factura
			} else if (facturaRequest != null && !facturaRequest.getDetallesFactura().isEmpty()) {

				// Valido y asigno los datos
				response = util.validateFactura(facturaRequest);

				for (DetalleFacturaRequest detalleFacturaRequest : facturaRequest.getDetallesFactura()) {

					// Valido detalles de factura y agrego a a lista de detalles
					response.getDetalleFacturasCollection().add(util.validateDetalleFactura(detalleFacturaRequest));
				}

				// Ejecuto el metodo para guardar
				response = facturaRepo.saveFactura(response);
			} else {

				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@Override
	public Factura updateFactura(FacturaRequest facturaRequest) {
		// DetalleFactura detalleFactura = null;
		Factura response = null;
		try {

			// Valido si factura no viene vacia, valido si contiene id, valido si contiene
			// detalles de factura
			if (facturaRequest != null && !facturaRequest.getDetallesFactura().isEmpty()) {

				// Valido si existe factura a actualizar, si no exite devolvera un excepcion
				response = facturaRepo.findById(facturaRequest.getIdFactura());

				// Valido y asigno los datos
				response = util.validateFactura(facturaRequest);

				for (DetalleFacturaRequest detalleFacturaRequest : facturaRequest.getDetallesFactura()) {

					// Valido detalles de factura y agrego a a lista de detalles
					response.getDetalleFacturasCollection().add(util.validateDetalleFactura(detalleFacturaRequest));
				}

				// Ejecuto el metodo para guardar
				response = facturaRepo.saveFactura(response);
			} else if (facturaRequest != null && facturaRequest.getEstado() == AppConstants.FLOW_FINISHED) {
				
				// Valido si existe factura a actualizar, si no exite devolvera un excepcion
				response = facturaRepo.findById(facturaRequest.getIdFactura());
				
				//actualizo estado y fecha
				response.setEstado(facturaRequest.getEstado());
				response.setFecha(new Date());
				
				// Ejecuto el metodo para guardar
				response = facturaRepo.saveFactura(response);
				
				//Valido datos para validar si hay pelicula en alquiler dentro de la factura
				AlquilerPelicula alquilerPelicula = util.validateAlquilerPelicula(response);
				
				//valido si contiene valores
				if(alquilerPelicula != null && !alquilerPelicula.getDetalleAlquilers().isEmpty()) {
					
					//Ejecuto metodo para guardar
					alquilerPeliculaRepo.saveAlquilerBitacora(alquilerPelicula);
				}
			} else {

				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@Override
	public Factura findById(Long id) {
		Factura response = null;
		try {

			// Valido id no es null
			if (id != null) {

				// Realizo busqueda por id, si no exite devolvera un excepcion
				response = facturaRepo.findById(id);

				// Valido si contiene detalles
				if (!response.getDetalleFacturasCollection().isEmpty()) {

					// Si contiene detalles calculos los totales de factura y actualizo en base de
					// datos
					response = facturaRepo.saveFactura(util.calculateData(response));
				}
			} else {

				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@Override
	public List<Factura> findByUsuario(String usuario) {
		List<Factura> response = null;
		try {

			// Valido usuario no es null
			if (usuario != null) {

				// Realizo busqueda por usuario, si no exite devolvera un excepcion
				response = facturaRepo.findByUsuario(usuario);

				// Recorro la busqueda
				for (Factura factura : response) {

					// Valido si facturas tienen detalles
					if (!factura.getDetalleFacturasCollection().isEmpty()) {

						// Si contiene detalles calculos los totales de factura y actualizo en base de
						// datos
						factura = facturaRepo.saveFactura(util.calculateData(factura));
					}

					// agrego a a lista a retornar
					response.add(factura);
				}
			} else {
				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@Override
	public List<Factura> findAllFactura() {
		List<Factura> response = null;
		try {
			// Realizo busqueda, si no encuentra registros devolvera un excepcion
			response = facturaRepo.findAllFactura();

			// Recorro la busqueda
			for (Factura factura : response) {

				// Valido si facturas tienen detalles
				if (!factura.getDetalleFacturasCollection().isEmpty()) {

					// Si contiene detalles calculos los totales de factura y actualizo en base de
					// datos
					factura = facturaRepo.saveFactura(util.calculateData(factura));
				}

				// agrego a a lista a retornar
				response.add(factura);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@Override
	public void deleteFactura(Long id) {
		Factura response = null;
		try {
			// Valido id no es null
			if (id != null) {

				// Realizo busqueda por id, si no exite devolvera un excepcion
				response = facturaRepo.findById(id);
				
				// Si existe, procedo a ejecutar metodo para eliminar
				facturaRepo.deleteFactura(response);
			} else {
				
				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
	}

}

