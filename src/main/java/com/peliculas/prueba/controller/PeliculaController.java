package com.peliculas.prueba.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.Pelicula;
import com.peliculas.prueba.service.PeliculaService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;

	private static final Logger logger = LoggerFactory.getLogger(PeliculaController.class);

	@PostMapping("/")
	public ResponseEntity<ApiResponse<Pelicula>> savePelicula(@RequestBody Pelicula pelicula) {
		try {
			Pelicula response = peliculaService.savePelicula(pelicula);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Pelicula guardada exitosamente", response),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(
					new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar pelicula", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	public ResponseEntity<ApiResponse<Pelicula>> updatePelicula(@RequestBody Pelicula peliculaUpdate) {
		try {
			Pelicula response = peliculaService.updatePelicula(peliculaUpdate);
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Pelicula actualizada exitosamente", response),
					HttpStatus.OK);
		} catch (AppServiceException e) {
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null),
					HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(
					new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la pelicula", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Pelicula>> findById(@PathVariable Long id) {
		try {
			Pelicula response = peliculaService.findById(id);
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

	@GetMapping("/{titulo}")
	public ResponseEntity<ApiResponse<List<Pelicula>>> findByTitulo(@PathVariable String titulo) {
		try {
			List<Pelicula> response = peliculaService.findByTitulo(titulo);
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
	
	@GetMapping("/disponibilidad")
    public ResponseEntity<ApiResponse<Page<Pelicula>>> findByDisponibilidad(@RequestParam boolean disponibilidad, Pageable pageable) {
        try {
            Page<Pelicula> response = peliculaService.findByDisponibilidad(disponibilidad, pageable);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "", response), HttpStatus.OK);
        } catch (AppServiceException e) {
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CONFLICT, e.getMessage(), null), HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePelicula(@PathVariable("id") Long id) {
        try {
            peliculaService.deletePelicula(id);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Pelicula eliminada con éxito", null), HttpStatus.OK);
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
