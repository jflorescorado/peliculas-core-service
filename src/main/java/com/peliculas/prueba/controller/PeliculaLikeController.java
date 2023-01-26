package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.consumer.Consumer;
import com.peliculas.prueba.dto.PeliculaLikeRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.service.PeliculaLikeService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/like")
@Api(value = "/like", tags = "PeliculaLikeController")
public class PeliculaLikeController {

	@Autowired
	private PeliculaLikeService peliculaLikeService;
	
	@Autowired
	private Consumer consumer;
	
	@Autowired
	ResponseEntityUtil responseEntityUtil;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaLikeController.class);

	@ApiOperation(value = "Proceso para guardar like de pelicula")
	@PostMapping("/")
	public ResponseEntity<?> savePeliculaLike(@RequestHeader(name = "Authorization") String token,
			@RequestBody PeliculaLikeRequest peliculaLikeRequest) {
		ResponseEntity<?> response = null;
		try {
			//Valido token
			if(!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null , 
						AppConstants.CODE_UNAUTHORIZED, AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
			response = responseEntityUtil.createOkResponse(peliculaLikeService.savePeliculaLike(peliculaLikeRequest), 
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
}
