package com.peliculas.prueba.repository.repo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.PeliculaLike;
import com.peliculas.prueba.repository.PeliculaLikeRepository;

@Component
public class PeliculaLikeRepo {

	@Autowired
	PeliculaLikeRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaLikeRepo.class);

	public PeliculaLike savePeliculaLike(PeliculaLike peliculaLike) {
		PeliculaLike response = new PeliculaLike();
		try {
			if(peliculaLike.getIdPelicula() != null && peliculaLike.getUsuario() != null) {
				peliculaLike.setFecha(new Date());
				response = repository.save(peliculaLike);
			}else {
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
}

