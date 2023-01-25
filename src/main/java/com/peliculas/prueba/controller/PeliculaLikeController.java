package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.dto.PeliculaLikeRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.PeliculaLike;
import com.peliculas.prueba.service.PeliculaLikeService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/like")
public class PeliculaLikeController {

	@Autowired
	private PeliculaLikeService peliculaLikeService;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaLikeController.class);

	@PostMapping("/")
	public ResponseEntity<ApiResponse<PeliculaLike>> savePeliculaLike(
			@RequestBody PeliculaLikeRequest peliculaLikeRequest) {
		try {
			PeliculaLike response = peliculaLikeService.savePeliculaLike(peliculaLikeRequest);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Like guardado exitosamente", response), HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar like", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
