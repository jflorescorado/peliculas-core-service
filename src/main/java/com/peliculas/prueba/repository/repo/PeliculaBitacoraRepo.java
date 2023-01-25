package com.peliculas.prueba.repository.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.PeliculaBitacora;
import com.peliculas.prueba.repository.PeliculaBitacoraRepository;

@Component
public class PeliculaBitacoraRepo {

	@Autowired
	PeliculaBitacoraRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaBitacoraRepo.class);

	public void savePeliculaBitacora(PeliculaBitacora peliculaBitacora) {
		try {
			// Procedo a guardar la bitacora de pelicula
			PeliculaBitacora response = repository.save(peliculaBitacora);

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
	}

	public List<PeliculaBitacora> findByIdPelicula(Long idPelicula) {
		List<PeliculaBitacora> response = new ArrayList<>();
		try {
			// Realizo una buesqueda por id de pelicula
			response = repository.findByIdPelicula(idPelicula);

			// Valido que la busqueda no sea null
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

	public boolean getByIdPelicula(Long idPelicula) {
		boolean validate = true;
		List<PeliculaBitacora> response = new ArrayList<>();
		try {
			// Valido que la busqueda no sea null
			if (response.isEmpty()) {
				validate = false;
			}
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		return validate;
	}

	public void DeletePeliculaBitacora(Long idPelicula) {
		try {
			// Procedo a eliminar los registros
			repository.deleteByIdPelicula(idPelicula);
			;

		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}
}

