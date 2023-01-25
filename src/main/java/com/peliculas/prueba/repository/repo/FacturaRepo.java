package com.peliculas.prueba.repository.repo;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Factura;
import com.peliculas.prueba.repository.FacturaRepository;
import com.peliculas.prueba.util.AppUtil;

@Component
public class FacturaRepo {

	@Autowired
	FacturaRepository repository;

	@Autowired
	AppUtil util;

	private static final Logger logger = LoggerFactory.getLogger(FacturaRepo.class);

	public Factura saveFactura(Factura factura) {
		Factura response = new Factura();
		try {
			// Procedo a guardar la factura
			response = repository.save(factura);

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

	public Factura findById(Long id) {
		Factura response = new Factura();
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

	public List<Factura> findByUsuario(String usuario) {
		List<Factura> response = new ArrayList<>();
		try {
			// Realizo una buesqueda por usuario
			response = repository.findByUsuario(usuario);

			// Valido si el resultado de la busqueda se encuentra vacio
			if (response.isEmpty()) {
				throw new AppServiceException(AppConstants.CODE_DATA_NOTFOUND,
						"No se encontro registro de Facturas con usuario :" + usuario);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public List<Factura> findAllFactura() {
		List<Factura> response = new ArrayList<>();
		try {
			// Realizo una buesqueda por usuario
			repository.findAll();

			// Valido si el resultado de la busqueda se encuentra vacio
			if (response.isEmpty()) {
				throw new AppServiceException(AppConstants.CODE_DATA_NOTFOUND, AppConstants.DESCRIPTION_DATA_NOTFOUND);
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return response;
	}

	public void deleteFactura(Factura factura) {
		try {
			// Procedo a eliminar el registro
			repository.delete(factura);

		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}
}

