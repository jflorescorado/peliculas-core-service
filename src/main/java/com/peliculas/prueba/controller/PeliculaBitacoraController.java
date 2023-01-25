package com.peliculas.prueba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.PeliculaBitacora;
import com.peliculas.prueba.service.PeliculaBitacoraService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/peliculaBitacora")
public class PeliculaBitacoraController {

	@Autowired 
	PeliculaBitacoraService peliculaBitacoraService;
	
	private static final Logger logger = LoggerFactory.getLogger(PeliculaBitacoraController.class);
	
	@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<PeliculaBitacora>>> findByIdPelicula(@PathVariable Long idPelicula) {
        try {
            List<PeliculaBitacora> response = peliculaBitacoraService.findByIdPelicula(idPelicula);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "", response), HttpStatus.OK);
        } catch (AppServiceException e) {
        	return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
        } catch (Exception e) {
        	logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

