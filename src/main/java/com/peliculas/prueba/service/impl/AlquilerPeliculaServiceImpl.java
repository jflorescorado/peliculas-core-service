package com.peliculas.prueba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.repository.repo.AlquilerPeliculaRepo;
import com.peliculas.prueba.service.AlquilerPeliculaService;
import com.peliculas.prueba.util.AppUtil;

@Service
public class AlquilerPeliculaServiceImpl implements AlquilerPeliculaService {

	@Autowired
	AlquilerPeliculaRepo alquilerPeliculaRepo;
	
	@Autowired
	AppUtil util;

	private static final Logger logger = LoggerFactory.getLogger(AlquilerPeliculaServiceImpl.class);

	@Override
	public AlquilerPelicula updateAlquilerBitacora(AlquilerPelicula alquilerBitacora) {
		AlquilerPelicula response = new AlquilerPelicula();
		try {
			// Valido si alquilerPelicula no es null
			if (alquilerBitacora != null & alquilerBitacora.getIdAlquilerPelicula() != null
					&& alquilerBitacora.getFecRetira() != null || alquilerBitacora.getFecDevolucionOriginal() != null
					|| alquilerBitacora.getFecDevolucion() != null) {
				
				// Valido si existe registro, si no existe devolvera una excepcion
				response = alquilerPeliculaRepo.findById(alquilerBitacora.getIdAlquilerPelicula());

				//Valido fecha de retiro 
				if (alquilerBitacora.getFecRetira() != null) {
					response.setFecRetira(alquilerBitacora.getFecRetira());
				}

				//Valido fecha de devolucion original
				if (alquilerBitacora.getFecDevolucionOriginal() != null) {
					response.setFecDevolucionOriginal(alquilerBitacora.getFecDevolucionOriginal());
				}

				//Valido fecha de devolucion
				if (alquilerBitacora.getFecDevolucion() != null) {
					response.setFecDevolucion(alquilerBitacora.getFecDevolucion());
				}
				
				//Actualizo en base de datos
				response = alquilerPeliculaRepo.saveAlquilerBitacora(util.calculateDataAlquiler(response));
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
	public List<AlquilerPelicula> findByUsuario(String usuario) {
		List<AlquilerPelicula> response = new ArrayList<>();
		try {
			// Valido si usuario no es null
			if (usuario != null) {

				// Valido si existe registro, si no existe devolvera una excepcion
				response = alquilerPeliculaRepo.findByUsuario(usuario);
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
	public List<AlquilerPelicula> findAllAlquilerBitacora() {
		try {
			// Valido si existe registro, si no existe devolvera una excepcion
			return alquilerPeliculaRepo.findAllAlquilerBitacora();
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
	}

	@Override
	public void deletefindAlquilerBitacora(Long id) {
		AlquilerPelicula response = new AlquilerPelicula();
		try {
			// Valido si existe registro, si no existe devolvera una excepcion
			response = alquilerPeliculaRepo.findById(id);

			// Procedo a ejecutar metodo para eliminar
			alquilerPeliculaRepo.deletefindAlquilerBitacora(response);
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
	}

}

