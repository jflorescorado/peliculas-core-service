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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.prueba.dto.DetalleFacturaRequest;
import com.peliculas.prueba.exception.AppServiceException;
import com.peliculas.prueba.model.DetalleFactura;
import com.peliculas.prueba.service.DetalleFacturaService;
import com.peliculas.prueba.util.ApiResponse;

@RestController
@RequestMapping("/detalleFactura")
public class DetalleFacturaController {

	@Autowired
	DetalleFacturaService detalleFacturaService;

	private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaController.class);

	@PostMapping("/")
	public ResponseEntity<ApiResponse<DetalleFactura>> saveDetalleFactura(
			@RequestBody DetalleFacturaRequest detalleFacturaRequest) {
		try {
			DetalleFactura response = detalleFacturaService.saveDetalleFactura(detalleFacturaRequest);
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
	
	@PutMapping("/")
	public ResponseEntity<ApiResponse<DetalleFactura>> updateDetalleFactura(
			@RequestBody DetalleFacturaRequest detalleFacturaRequest) {
		try {
			DetalleFactura response = detalleFacturaService.updateDetalleFactura(detalleFacturaRequest);
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
	

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<List<DetalleFactura>>> findById(@PathVariable Long idFactura) {
		try {
			List<DetalleFactura> response = detalleFacturaService.findByIdFactura(idFactura);
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
	public ResponseEntity<ApiResponse<Void>> deleteFactura(@PathVariable Long id) {
		try {
			detalleFacturaService.deleteDetalleFactura(id);
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

