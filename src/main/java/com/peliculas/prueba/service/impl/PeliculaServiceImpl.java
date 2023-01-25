package com.peliculas.prueba.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.model.PeliculaBitacora;
import com.peliculas.prueba.repository.repo.PeliculaBitacoraRepo;
import com.peliculas.prueba.repository.repo.PeliculaRepo;
import com.peliculas.prueba.service.PeliculaService;
import com.peliculas.prueba.util.AppUtil;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	PeliculaRepo peliculaRepo;

	@Autowired
	PeliculaBitacoraRepo bitacoraRepo;

	@Autowired
	AppUtil util;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaServiceImpl.class);

	@Override
	public Pelicula savePelicula(Pelicula pelicula) {
		Date fecha = new Date();
		Pelicula response = null;
		try {
			
			// Valido si pelicula es diferente a nulo para ejecutar el metodo para guardar
			if (pelicula != null) {
				pelicula.setFecCrea(fecha);

				// Procedo a guardar la pelicula
				response = peliculaRepo.savePelicula(pelicula);
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
	public Pelicula updatePelicula(Pelicula peliculaUpdate) {
		Date fecha = new Date();
		PeliculaBitacora peliculaBitacora = null;
		Pelicula pelicula = null;
		try {
			
			// Valido que pelicula a actualizar no venga vacia y valido si tiene el id
			if (peliculaUpdate != null && peliculaUpdate.getIdPelicula() != null) {
				peliculaUpdate.setFeModifica(fecha);
				pelicula = peliculaRepo.findById(peliculaUpdate.getIdPelicula());

				// Valido si pelicula es diferente a null y valido se se han realizado cambios en 
				// el Titulo, Precio de Venta y/o Precio de Renta
				if (pelicula != null && !pelicula.getTitulo().equals(peliculaUpdate.getTitulo())
						|| pelicula.getPrecioVenta().compareTo(peliculaUpdate.getPrecioVenta()) != 0
						|| pelicula.getPrecioAlquiler().compareTo(peliculaUpdate.getPrecioAlquiler()) != 0) {

					// Procedo a asignar valores para la bitacora
					peliculaBitacora = util.saveDataPeliculaBitacora(pelicula, peliculaUpdate);

					// Procedo a guardar la bitacora
					bitacoraRepo.savePeliculaBitacora(peliculaBitacora);
				}
				
				// Procedo a guardar la pelicula
				pelicula = peliculaRepo.savePelicula(peliculaUpdate);
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
		return pelicula;
	}

	@Override
	public Pelicula findById(Long id) {
		Pelicula response = null;
		try {
			
			// Valido si id es diferente a nulo para realizar la busqueda
			if (id != null) {
				response = peliculaRepo.findById(id);
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
	public List<Pelicula> findByTitulo(String titulo) {
		List<Pelicula> response = null;
		try {
			
			// Valido si titulo es diferente a nulo para realizar la busqueda
			if (titulo != null) {
				response = peliculaRepo.findByTitulo(titulo);
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
	public Page<Pelicula> findByDisponibilidad(boolean disponibilidad, Pageable pageable) {
		try {
			
			// Ejecuto y retorno el metodo para la busqueda
			return peliculaRepo.findByDisponibilidad(disponibilidad, pageable);
		} catch (AppServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new AppServiceException(AppConstants.CODE_ERROR, AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
	}

	@Override
	public void deletePelicula(Long id) {
		Pelicula pelicula = null;
		try {
			// Valido si id es diferente a nulo para eliminar el registro
			if (id != null) {
				
				// Realizo la busqueda de la pelicula a eliminar, si no la encuentra devolvera
				// una excepcion
				pelicula = peliculaRepo.findById(id);

				// Valido si tiene bitacoras, si tiene ejecuta el metodo para eliminar
				if (bitacoraRepo.getByIdPelicula(id)) {
					
					// Procedo a ejecutar metodo para eliminarlas
					bitacoraRepo.DeletePeliculaBitacora(id);
				}

				// Procedo a ejecutar metodo para eliminar
				peliculaRepo.deletePelicula(pelicula);
				;
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
