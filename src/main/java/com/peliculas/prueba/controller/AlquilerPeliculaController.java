package com.peliculas.prueba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.AlquilerPelicula;
import com.peliculas.prueba.service.AlquilerPeliculaService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/alquilerPelicula")
public class AlquilerPeliculaController {

	@Autowired
	AlquilerPeliculaService alquilerPeliculaService;
	
	private static final Logger logger = LoggerFactory.getLogger(AlquilerPeliculaController.class);
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse<AlquilerPelicula>> updateAlquilerPelicula(@RequestBody AlquilerPelicula alquilerPelicula) {
		try {
			AlquilerPelicula response = alquilerPeliculaService.updateAlquilerBitacora(alquilerPelicula);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Se guardo factura exitosamente", response),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{usuario}")
	public ResponseEntity<ApiResponse<List<AlquilerPelicula>>> findByUsuario(@PathVariable String usuario) {
		try {
			List<AlquilerPelicula> response = alquilerPeliculaService.findByUsuario(usuario);
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
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse<List<AlquilerPelicula>>> findAllFactura() {
		try {
			List<AlquilerPelicula> response = alquilerPeliculaService.findAllAlquilerBitacora();
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteAlquilerPelicula(@PathVariable Long id) {
		try {
			alquilerPeliculaService.deletefindAlquilerBitacora(id);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Factura eliminada con éxito", null),
					HttpStatus.OK);
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
