package com.peliculas.prueba.repository.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.repository.AlquilerPeliculaRepository;

@Component
public class AlquilerPeliculaRepo {

	@Autowired
	AlquilerPeliculaRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(AlquilerPeliculaRepo.class);

	public AlquilerPelicula saveAlquilerBitacora(AlquilerPelicula alquilerBitacora) {
		AlquilerPelicula response = new AlquilerPelicula();
		try {
			// Procedo a guardar la bitacora del alquiler
			response = repository.save(alquilerBitacora);

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

	public List<AlquilerPelicula> findByUsuario(String usuario) {
		List<AlquilerPelicula> response = new ArrayList<>();
		try {
			// Procedo a guardar la bitacora del alquiler
			response = repository.findByUsuario(usuario);

			// Valido si el resultado de la busqueda se encuentra vacio
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

	public List<AlquilerPelicula> findAllAlquilerBitacora() {
		List<AlquilerPelicula> response = new ArrayList<>();
		try {
			// Procedo a guardar la bitacora del alquiler
			response = repository.findAll();

			// Valido si el resultado de la busqueda se encuentra vacio
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

	public AlquilerPelicula findById(Long id) {
		AlquilerPelicula response = new AlquilerPelicula();
		try {
			//Procedo a realizar busqueda
			response = repository.findById(id)
					.orElseThrow(() -> new AppServiceException(AppConstants.CODE_DATA_NOTFOUND,
							"No se encontro registro de Alquiler con el ID : " + id));

			// Valido si el resultado de la busqueda se encuentra vacio
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

	public void deletefindAlquilerBitacora(AlquilerPelicula alquilerPelicula) {
		try {
			// Procedo para eliminar la bitacora del alquiler
			repository.delete(alquilerPelicula);
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}
}
