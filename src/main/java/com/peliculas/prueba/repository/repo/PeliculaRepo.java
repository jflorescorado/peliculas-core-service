package com.peliculas.prueba.repository.repo;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.repository.PeliculaRepository;
import com.peliculas.prueba.util.AppUtil;

@Component
public class PeliculaRepo {
	
	@Autowired
	PeliculaRepository repository;
	
	@Autowired
	AppUtil util;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaRepo.class);
	
	public Pelicula savePelicula(Pelicula pelicula) {
		Pelicula response = null;
		try {
			//valido los datos requeridos antes de guardar
			if(util.ValidateDataPelicula(pelicula)) {
				//En caso la respuesta es true, procedo a guardar el registro
				response = repository.save(pelicula);
			}
			
			//Valido si el registro se encuentra vacia
			if(response == null) {
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
	
	public Pelicula findById(Long id){
		Pelicula response = null;
		try {
			//Realizo una buesqueda por id
			response = repository.findById(id)
					.orElseThrow(() -> new AppServiceException( AppConstants.CODE_DATA_NOTFOUND ,"No se encontro pelicula con el ID : " + id));
		} catch (AppServiceException e) {
			throw e;	
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
		
		return response;
	}
	
	public List<Pelicula> findByTitulo(String titulo){
		List<Pelicula> response = new ArrayList<>();
		try {
			//Realizo busqueda por nombre completo de la pelicula
			Pelicula pelicula = repository.findByTitulo(titulo);
			if(pelicula != null) {
				response.add(pelicula);
			}
			
			//Realizo una buesqueda por titulo
			response = repository.findByListTitulo(titulo);
			
			//Valido si la lista en donde se almaceno la busqueda se encuentra vacia
			if(response.isEmpty()) {
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
	
	public Page<Pelicula> findByDisponibilidad(boolean disponibilidad, Pageable pageable) {
		try {
			//Realizo busqueda por disponibilidad y paginacion
			return repository.findByDisponibilidad(disponibilidad, pageable);
		} catch (AppServiceException e) {
			throw e;	
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
    }
	
	public void deletePelicula(Pelicula pelicula) {
		try {
				//Procedo a eliminar el registro
				repository.delete(pelicula);
			
		} catch (AppServiceException e) {
			throw e;	
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_REPOSITORY);
		}
	}
	
}
