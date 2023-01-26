package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.consumer.Consumer;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.service.PeliculaService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/peliculas")
@Api(value = "/peliculas", tags = "PeliculaController")
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;

	@Autowired
	private Consumer consumer;

	@Autowired
	ResponseEntityUtil responseEntityUtil;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaController.class);

	@ApiOperation(value = "Proceso para guardar pelicula")
	@PostMapping("/")
	public ResponseEntity<?> savePelicula(@RequestHeader(name = "Authorization") String token,
			@RequestBody Pelicula pelicula, @RequestParam(required = false) MultipartFile imagen) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			// Valido si no es null
			if (imagen != null) {
				// Procesar la imagen y convertirla en un arreglo de bytes
				byte[] imageBytes = imagen.getBytes();
				pelicula.setImagen(imageBytes);
			}

			response = responseEntityUtil.createOkResponse(peliculaService.savePelicula(pelicula),
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

	@ApiOperation(value = "Proceso para actualizar pelicula")
	@PutMapping("/")
	public ResponseEntity<?> updatePelicula(@RequestHeader(name = "Authorization") String token,
			@RequestBody Pelicula peliculaUpdate, @RequestParam(required = false) MultipartFile imagen) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			/// Valido si no es null
			if (imagen != null) {
				// Procesar la imagen y convertirla en un arreglo de bytes
				byte[] imageBytes = imagen.getBytes();
				peliculaUpdate.setImagen(imageBytes);
			}

			response = responseEntityUtil.createOkResponse(peliculaService.updatePelicula(peliculaUpdate),
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

	@ApiOperation(value = "Proceso para obtener pelicula por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			response = responseEntityUtil.createOkResponse(peliculaService.findById(id), AppConstants.CODE_SUCCESS,
					AppConstants.DESCRIPTION_SUCCESS);
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

	@ApiOperation(value = "Proceso para obtener pelicula por titulo")
	@GetMapping("/{titulo}")
	public ResponseEntity<?> findByTitulo(@RequestHeader(name = "Authorization") String token,
			@PathVariable String titulo) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			response = responseEntityUtil.createOkResponse(peliculaService.findByTitulo(titulo),
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

	@ApiOperation(value = "Proceso para obtener pelicula por disponibilidad y paginacion")
	@GetMapping("/disponibilidad")
	public ResponseEntity<?> findByDisponibilidad(@RequestParam boolean disponibilidad, Pageable pageable) {
		ResponseEntity<?> response = null;
		try {
			
			response = responseEntityUtil.createOkResponse(
					peliculaService.findByDisponibilidad(disponibilidad, pageable), AppConstants.CODE_SUCCESS,
					AppConstants.DESCRIPTION_SUCCESS);
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

	@ApiOperation(value = "Proceso para obtener imagen de pelicula")
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getPeliculaImage(@PathVariable Long id) {
		try {
			
			Pelicula pelicula = peliculaService.findById(id);
			byte[] imageBytes = pelicula.getImagen();
			if (imageBytes == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		} catch (AppServiceException e) {
			logger.error(AppConstants.APP_EXCEPTION, e);
			return responseEntityUtil.createFailResponse(null, e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION, e);
			return responseEntityUtil.createFailResponse(null, AppConstants.CODE_ERROR,
					AppConstants.DEFAULT_ERROR_DESCRIPTION);
		}
	}

	@ApiOperation(value = "Proceso para eliminar pelicula por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePelicula(@RequestHeader(name = "Authorization") String token,
			@PathVariable("id") Long id) {
		ResponseEntity<?> response = null;
		try {
			// Valido token
			if (!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null, AppConstants.CODE_UNAUTHORIZED,
						AppConstants.DESCRIPTION_UNAUTHORIZED);
			}

			peliculaService.deletePelicula(id);

			response = responseEntityUtil.createOkResponse(null, AppConstants.CODE_SUCCESS,
					"Pelicula eliminada existosamente");
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
