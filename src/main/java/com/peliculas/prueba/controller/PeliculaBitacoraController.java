package com.peliculas.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.commons.AppConstants;
import com.peliculas.prueba.consumer.Consumer;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.service.PeliculaBitacoraService;
import com.peliculas.prueba.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/peliculaBitacora")
@Api(value = "/peliculaBitacora", tags = "PeliculaBitacoraController")
public class PeliculaBitacoraController {

	@Autowired 
	PeliculaBitacoraService peliculaBitacoraService;
	
	@Autowired
	private Consumer consumer;
	
	@Autowired
	ResponseEntityUtil responseEntityUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(PeliculaBitacoraController.class);
	
	@ApiOperation(value = "Proceso para obtener bitacora de pelicula por id de pelicula")
	@GetMapping("/{id}")
    public ResponseEntity<?> findByIdPelicula(@RequestHeader(name = "Authorization") String token, @PathVariable Long idPelicula) {
		ResponseEntity<?> response = null;
		try {
			//Valido token
			if(!consumer.validateToken(token)) {
				return response = responseEntityUtil.createFailResponse(null , 
						AppConstants.CODE_UNAUTHORIZED, AppConstants.DESCRIPTION_UNAUTHORIZED);
			}
			
            response = responseEntityUtil.createOkResponse(peliculaBitacoraService.findByIdPelicula(idPelicula), 
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

