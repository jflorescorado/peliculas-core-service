package com.peliculas.prueba.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.dto.PeliculaLikeRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.model.PeliculaLike;
import com.peliculas.prueba.repository.repo.PeliculaLikeRepo;
import com.peliculas.prueba.service.PeliculaLikeService;

@Service
public class PeliculaLikeServiceImpl implements PeliculaLikeService{
	
	@Autowired
	PeliculaLikeRepo likeRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(PeliculaLikeServiceImpl.class);

	@Override
	public PeliculaLike savePeliculaLike(PeliculaLikeRequest peliculaLikeRequest) {
		PeliculaLike response = new PeliculaLike();
		Pelicula pelicula = new Pelicula();
		try {
			//Valido si peliculaLike no esta vacia
			if(peliculaLikeRequest != null) {
				response.setFecha(new Date());
				pelicula.setIdPelicula(peliculaLikeRequest.getIdPelicula());
				response.setIdPelicula(pelicula);
				
				//Ejecuto metodo guardar
				response = likeRepo.savePeliculaLike(response);
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

}

