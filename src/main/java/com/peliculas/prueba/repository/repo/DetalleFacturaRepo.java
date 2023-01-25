package com.peliculas.prueba.repository.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.DetalleFactura;
import com.peliculas.prueba.repository.DetalleFacturaRepository;
import com.peliculas.prueba.util.AppUtil;

@Component
public class DetalleFacturaRepo {

	@Autowired
	DetalleFacturaRepository repository;

	@Autowired
	PeliculaRepo peliculaRepo;

	@Autowired
	AppUtil util;

	@Autowired
	FacturaRepo facturaRepo;

	private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaRepo.class);

	public DetalleFactura saveDetalleFactura(DetalleFacturaRequest detalleFacturaRequest) {
		DetalleFactura response = new DetalleFactura();
		try {
			if (detalleFacturaRequest.getIdFactura() != null && detalleFacturaRequest.getIdPelicula() != null
					&& detalleFacturaRequest.getEstado() != null) {
				// Valido y asigno datos
				response = this.util.validateDetalleFactura(detalleFacturaRequest);

				// Procedo a guardar el detalle de la factura
				response = repository.save(response);
			}

			// Valido si se guardo el registro
			if (response == null) {
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public void saveAllDetalleFactura(List<DetalleFacturaRequest> detallesFactura, Long idFactura) {
		List<DetalleFactura> response = new ArrayList<>();
		DetalleFactura detalleFactura = null;
		try {
			for (DetalleFacturaRequest detalleFacturaRequest : detallesFactura) {
				// Valido si tiene idFactura,
				if (detalleFacturaRequest.getIdFactura() != null && detalleFacturaRequest.getIdPelicula() != null
						&& detalleFacturaRequest.getEstado() != null) {
					// Valido y asigno datos
					detalleFactura = util.validateDetalleFactura(detalleFacturaRequest);

					response.add(detalleFactura);
				} else {
					// Devuelvo una excepcion
					throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DATO_INVALIDO);
				}
			}

			//Valido si lista a guardar no esta vacia
			if (!response.isEmpty()) {
				repository.saveAll(response);
			} else {
				// Devuelvo una excepcion
				throw new AppServiceException(AppConstants.CODE_ERROR,
						"Ocurrio un error al guardar listado de detalles de factura");
			}

		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}

	public List<DetalleFactura> findByIdFactura(Long id) {
		List<DetalleFactura> response = new ArrayList<>();
		try {
			// Procedo a guardar el detalle de la factura
			response = repository.findByIdFactura(id);

			// Valido si se guardo el registro
			if (response == null) {
				throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public DetalleFactura findById(Long id) {
		DetalleFactura response = new DetalleFactura();
		try {
			// Realizo una buesqueda por id
			response = repository.findById(id)
					.orElseThrow(() -> new AppServiceException(AppConstants.CODE_DATA_NOTFOUND,
							"No se encontro factura con el ID : " + id));
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public void deleteAllDetalleFactura(Long idFactura) {

	}

	public void deleteDetalleFactura(DetalleFactura detalleFactura) {
		try {
			// Procedo a eliminar el registro
			repository.delete(detalleFactura);
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}
}
