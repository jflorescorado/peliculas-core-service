package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.consumer.Consumer;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.service.AlquilerPeliculaService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/alquilerPelicula")
@Api(value = "/alquilerPelicula", tags = "AlquilerPeliculaController")
public class AlquilerPeliculaController {

	@Autowired
	AlquilerPeliculaService alquilerPeliculaService;

	@Autowired
	private Consumer consumer;

	@Autowired
	ResponseEntityUtil responseEntityUtil;

	private static final Logger logger = LoggerFactory.getLogger(AlquilerPeliculaController.class);

	@ApiOperation(value = "Proceso para actualizar registro de alquiler de pelicula")
	@PutMapping("/")
	public ResponseEntity<?> updateAlquilerPelicula(@RequestHeader(name = "Authorization") String token,
			@RequestBody AlquilerPelicula alquilerPelicula) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(alquilerPeliculaService.updateAlquilerBitacora(alquilerPelicula),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para obtener alquiler de pelicula por usuario")
	@GetMapping("/{usuario}")
	public ResponseEntity<?> findByUsuario(@RequestHeader(name = "Authorization") String token,
			@PathVariable String usuario) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(alquilerPeliculaService.findByUsuario(usuario),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para obtener todos los registros de alquiler pelicula")
	@GetMapping("/")
	public ResponseEntity<?> findAllFactura(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			response = responseEntityUtil.createOkResponse(alquilerPeliculaService.findAllAlquilerBitacora(),
					AppConstants.CODE_SUCCESS, AppConstants.DESCRIPTION_SUCCESS);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}

	@ApiOperation(value = "Proceso para eliminar alquiler de pelicula por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAlquilerPelicula(@RequestHeader(name = "Authorization") String token,
			@PathVariable Long id) {
		ResponseEntity<?> response = null;
		try {
			alquilerPeliculaService.deletefindAlquilerBitacora(id);

			response = responseEntityUtil.createOkResponse(null, AppConstants.CODE_SUCCESS,
					"registro de alquiler de pelicula eliminado existosamente");
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
		return response;
	}
}
