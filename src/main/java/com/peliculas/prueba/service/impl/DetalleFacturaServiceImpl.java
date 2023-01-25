package com.peliculas.prueba.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.DetalleFactura;
import com.peliculas.prueba.repository.repo.DetalleFacturaRepo;
import com.peliculas.prueba.repository.repo.PeliculaRepo;
import com.peliculas.prueba.service.DetalleFacturaService;
import com.peliculas.prueba.util.AppUtil;

@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService {

	@Autowired
	DetalleFacturaRepo detalleFacturaRepo;

	@Autowired
	PeliculaRepo peliculaRepo;

	@Autowired
	AppUtil util;

	private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaServiceImpl.class);

	@Override
	public DetalleFactura saveDetalleFactura(DetalleFacturaRequest detalleFacturaRequest) {
		DetalleFactura response = null;
		try {
			// Valido si no viene null
			if (detalleFacturaRequest != null) {
				// Procedo a guardar
				response = detalleFacturaRepo.saveDetalleFactura(detalleFacturaRequest);
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
	public DetalleFactura updateDetalleFactura(DetalleFacturaRequest detalleFacturaRequest) {
		DetalleFactura response = null;
		try {
			// Valido si datos no viene nulos y si id de la factura a actualizar detalle no
			// viene null
			if (detalleFacturaRequest != null && detalleFacturaRequest.getIdDetalleFactura() != null) {
				// Valido si existe registro a actualizar, si no existe devolvera una expcepcion
				response = detalleFacturaRepo.findById(detalleFacturaRequest.getIdDetalleFactura());

				// Si existe procedo a guardar
				response = detalleFacturaRepo.saveDetalleFactura(detalleFacturaRequest);
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
	public List<DetalleFactura> findByIdFactura(Long idFactura) {
		List<DetalleFactura> response = null;
		try {
			// Valido si id de factura no viene null
			if (idFactura != null) {
				// ejecuto el metodo para buscar
				response = detalleFacturaRepo.findByIdFactura(idFactura);
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
	public void deleteDetalleFactura(Long id) {
		DetalleFactura detalleFactura = null;
		try {
			// Valido si id no es null
			if (id != null) {
				// Realizo la busqueda del detalle de factura a eliminar, si no lo encuentra devolvera una excepcion
				detalleFactura = detalleFacturaRepo.findById(id);

				// Procedo a eliminar detalle de factura
				detalleFacturaRepo.deleteDetalleFactura(detalleFactura);
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
