package com.peliculas.prueba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.PeliculaBitacora;
import com.peliculas.prueba.repository.repo.PeliculaBitacoraRepo;
import com.peliculas.prueba.service.PeliculaBitacoraService;

@Service
public class PeliculaBitacoraServiceImpl implements PeliculaBitacoraService{
	
	@Autowired
	PeliculaBitacoraRepo peliculaBitacoraRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(PeliculaBitacoraServiceImpl.class);

	@Override
	public List<PeliculaBitacora> findByIdPelicula(Long idPelicula) {
		List<PeliculaBitacora> response = new ArrayList<>();
		try {
			if(idPelicula != null) {
				response = peliculaBitacoraRepo.findByIdPelicula(idPelicula);
			}else {
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
